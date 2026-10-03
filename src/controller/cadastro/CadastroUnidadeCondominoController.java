package controller.cadastro;

import controller.consulta.ConsultaUnidadeCondominoController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.UnidadeCondomino;
import model.DAO.UnidadeCondominoDAO;
import utils.Utils;
import view.cadastro.TelaCadastroUnidadeCondomino;
import view.consulta.TelaConsultaUnidadeCondomino;
import model.Unidade;
import controller.consulta.ConsultaUnidadeController;
import view.consulta.TelaConsultaUnidade;
import model.Proprietario;
import controller.consulta.ConsultaProprietarioController;
import view.consulta.TelaConsultaProprietario;

public class CadastroUnidadeCondominoController implements ActionListener {
    private final TelaCadastroUnidadeCondomino tela;
    private final UnidadeCondominoDAO dao = new UnidadeCondominoDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private Unidade unidadeSelecionado;
    private Proprietario proprietarioSelecionado;

    public CadastroUnidadeCondominoController(TelaCadastroUnidadeCondomino tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarUnidade().addActionListener(this);
        tela.getjButtonSelecionarProprietario().addActionListener(this);
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
            TelaConsultaUnidadeCondomino consulta = new TelaConsultaUnidadeCondomino(null, true);
            ConsultaUnidadeCondominoController controller = new ConsultaUnidadeCondominoController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            UnidadeCondomino registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarUnidade()) {
            TelaConsultaUnidade consulta = new TelaConsultaUnidade(null, true);
            ConsultaUnidadeController controller = new ConsultaUnidadeController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Unidade selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirUnidade(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarProprietario()) {
            TelaConsultaProprietario consulta = new TelaConsultaProprietario(null, true);
            ConsultaProprietarioController controller = new ConsultaProprietarioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Proprietario selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirProprietario(selecionado);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(UnidadeCondomino registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldDataAquisicao(), registro.getDataAquisicao());
        Utils.preencherCampo(tela.getjTextFieldDataVenda(), registro.getDataVenda());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirUnidade(registro.getUnidade());
        definirProprietario(registro.getProprietario());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirUnidade(null);
        definirProprietario(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final UnidadeCondomino registro = new UnidadeCondomino();
        try {
            registro.setId(idEmEdicao);
            registro.setDataAquisicao(Utils.data(tela.getjTextFieldDataAquisicao(), "data aquisicao", true));
            registro.setDataVenda(Utils.data(tela.getjTextFieldDataVenda(), "data venda", false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (unidadeSelecionado == null || unidadeSelecionado.getId() <= 0 || tela.getjComboBoxUnidade().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Unidade.");
            registro.setUnidade(unidadeSelecionado);
            if (proprietarioSelecionado == null || proprietarioSelecionado.getId() <= 0 || tela.getjComboBoxProprietario().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Proprietario.");
            registro.setProprietario(proprietarioSelecionado);
            if (registro.getDataVenda() != null && registro.getDataVenda().isBefore(registro.getDataAquisicao())) throw new IllegalArgumentException("DataVenda nao pode ser anterior a DataAquisicao.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar UnidadeCondomino", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirUnidade(Unidade registro) {
        unidadeSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxUnidade(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getDescricao());
    }
    private void definirProprietario(Proprietario registro) {
        proprietarioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxProprietario(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNomeFantasia());
    }
}
