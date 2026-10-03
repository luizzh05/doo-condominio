package controller.cadastro;

import controller.consulta.ConsultaUnidadeController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Unidade;
import model.DAO.UnidadeDAO;
import utils.Utils;
import view.cadastro.TelaCadastroUnidade;
import view.consulta.TelaConsultaUnidade;
import model.Edificio;
import controller.consulta.ConsultaEdificioController;
import view.consulta.TelaConsultaEdificio;

public class CadastroUnidadeController implements ActionListener {
    private final TelaCadastroUnidade tela;
    private final UnidadeDAO dao = new UnidadeDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private Edificio edificioSelecionado;

    public CadastroUnidadeController(TelaCadastroUnidade tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarEdificio().addActionListener(this);
        tela.getjTextFieldId().setEditable(false);
        encerrarEdicao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ocupado) return;
        if (e.getSource() == tela.getjButtonNovo()) {
            limparRegistro();
            editando = true;
            definirOcupado(false);

        } else if (e.getSource() == tela.getjButtonCancelar()) {
            encerrarEdicao();
        } else if (e.getSource() == tela.getjButtonGravar()) {
            gravar();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaUnidade consulta = new TelaConsultaUnidade(null, true);
            ConsultaUnidadeController controller = new ConsultaUnidadeController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Unidade registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio consulta = new TelaConsultaEdificio(null, true);
            ConsultaEdificioController controller = new ConsultaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Edificio selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirEdificio(selecionado);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(Unidade registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldDescricao(), registro.getDescricao());
        Utils.preencherCampo(tela.getjTextFieldMetragemTotal(), registro.getMetragemTotal());
        Utils.preencherCampo(tela.getjTextFieldMetragemIndividual(), registro.getMetragemIndividual());
        Utils.selecionarItem(tela.getjComboBoxTipoUnidade(), registro.getTipoUnidade());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirEdificio(registro.getEdificio());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirEdificio(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final Unidade registro = new Unidade();
        try {
            registro.setId(idEmEdicao);
            registro.setDescricao(Utils.texto(tela.getjTextFieldDescricao(), "descricao", 45, true));
            registro.setMetragemTotal(Utils.numero(tela.getjTextFieldMetragemTotal(), "metragem total", true));
            registro.setMetragemIndividual(Utils.numero(tela.getjTextFieldMetragemIndividual(), "metragem individual", true));
            registro.setTipoUnidade(Utils.escolha(tela.getjComboBoxTipoUnidade(), "tipo unidade", true));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (edificioSelecionado == null || edificioSelecionado.getId() <= 0 || tela.getjComboBoxTipoUnidade1().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Edificio.");
            registro.setEdificio(edificioSelecionado);
            if (registro.getMetragemIndividual() > registro.getMetragemTotal()) throw new IllegalArgumentException("A metragem individual nao pode exceder a total.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if (registro.getId() == 0) dao.create(registro);
                else dao.update(registro);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    encerrarEdicao();
                    JOptionPane.showMessageDialog(tela, "Registro gravado com sucesso.");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarErro(ex);
                } catch (ExecutionException ex) {
                    mostrarErro(ex.getCause());
                } finally {
                    definirOcupado(false);
                }
            }
        }.execute();
    }

    private void definirOcupado(boolean estado) {
        ocupado = estado;
        tela.setCursor(Cursor.getPredefinedCursor(estado ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        tela.setDefaultCloseOperation(estado ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), editando);
        if (estado) Utils.habilitarComponentes(tela.getjPanelbotoes(), false);
        Utils.habilitarComponentes(tela.getjPanelDados(), editando && !estado);
        tela.getjTextFieldId().setEnabled(false);
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Unidade", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirEdificio(Edificio registro) {
        edificioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxTipoUnidade1(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNome());
    }
}
