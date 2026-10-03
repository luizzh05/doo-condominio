package controller.cadastro;

import controller.consulta.ConsultaAreaCompartilhadaEdificioController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.AreaCompartilhadaEdificio;
import model.DAO.AreaCompartilhadaEdificioDAO;
import utils.Utils;
import view.cadastro.TelaCadastroAreaCompartilhadaEdificio;
import view.consulta.TelaConsultaAreaCompartilhadaEdificio;
import model.AreaCompartilhada;
import controller.consulta.ConsultaAreaCompartilhadaController;
import view.consulta.TelaConsultaAreaCompartilhada;
import model.Edificio;
import controller.consulta.ConsultaEdificioController;
import view.consulta.TelaConsultaEdificio;

public class CadastroAreaCompartilhadaEdificioController implements ActionListener {
    private final TelaCadastroAreaCompartilhadaEdificio tela;
    private final AreaCompartilhadaEdificioDAO dao = new AreaCompartilhadaEdificioDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private AreaCompartilhada areaCompartilhadaSelecionado;
    private Edificio edificioSelecionado;

    public CadastroAreaCompartilhadaEdificioController(TelaCadastroAreaCompartilhadaEdificio tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarAreaCompartilhada().addActionListener(this);
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
            TelaConsultaAreaCompartilhadaEdificio consulta = new TelaConsultaAreaCompartilhadaEdificio(null, true);
            ConsultaAreaCompartilhadaEdificioController controller = new ConsultaAreaCompartilhadaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            AreaCompartilhadaEdificio registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarAreaCompartilhada()) {
            TelaConsultaAreaCompartilhada consulta = new TelaConsultaAreaCompartilhada(null, true);
            ConsultaAreaCompartilhadaController controller = new ConsultaAreaCompartilhadaController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            AreaCompartilhada selecionado = controller.getAreaSelecionada();
            if (selecionado != null) definirAreaCompartilhada(selecionado);
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

    private void carregar(AreaCompartilhadaEdificio registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirAreaCompartilhada(registro.getAreaCompartilhada());
        definirEdificio(registro.getEdificio());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirAreaCompartilhada(null);
        definirEdificio(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final AreaCompartilhadaEdificio registro = new AreaCompartilhadaEdificio();
        try {
            registro.setId(idEmEdicao);
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (areaCompartilhadaSelecionado == null || areaCompartilhadaSelecionado.getId() <= 0 || tela.getjComboBoxAreaCompartilhada().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione AreaCompartilhada.");
            registro.setAreaCompartilhada(areaCompartilhadaSelecionado);
            if (edificioSelecionado == null || edificioSelecionado.getId() <= 0 || tela.getjComboBoxEdificio().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Edificio.");
            registro.setEdificio(edificioSelecionado);
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar AreaCompartilhadaEdificio", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirAreaCompartilhada(AreaCompartilhada registro) {
        areaCompartilhadaSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxAreaCompartilhada(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getDescricao());
    }
    private void definirEdificio(Edificio registro) {
        edificioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxEdificio(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNome());
    }
}
