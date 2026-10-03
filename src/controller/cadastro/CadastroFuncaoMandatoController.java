package controller.cadastro;

import controller.consulta.ConsultaFuncaoMandatoController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.FuncaoMandato;
import model.DAO.FuncaoMandatoDAO;
import utils.Utils;
import view.cadastro.TelaCadastroFuncaoMandato;
import view.consulta.TelaConsultaFuncaoMandato;
import model.SindicoProfissional;
import controller.consulta.ConsultaSindicoProfissionalController;
import view.consulta.TelaConsultaSindicoProfissional;
import model.Proprietario;
import controller.consulta.ConsultaProprietarioController;
import view.consulta.TelaConsultaProprietario;
import model.Edificio;
import controller.consulta.ConsultaEdificioController;
import view.consulta.TelaConsultaEdificio;

public class CadastroFuncaoMandatoController implements ActionListener {
    private final TelaCadastroFuncaoMandato tela;
    private final FuncaoMandatoDAO dao = new FuncaoMandatoDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private SindicoProfissional sindicoProfissionalSelecionado;
    private Proprietario proprietarioSelecionado;
    private Edificio edificioSelecionado;

    public CadastroFuncaoMandatoController(TelaCadastroFuncaoMandato tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarSindicoProfissional().addActionListener(this);
        tela.getjButtonSelecionarProprietario().addActionListener(this);
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
            TelaConsultaFuncaoMandato consulta = new TelaConsultaFuncaoMandato(null, true);
            ConsultaFuncaoMandatoController controller = new ConsultaFuncaoMandatoController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            FuncaoMandato registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarSindicoProfissional()) {
            TelaConsultaSindicoProfissional consulta = new TelaConsultaSindicoProfissional(null, true);
            ConsultaSindicoProfissionalController controller = new ConsultaSindicoProfissionalController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            SindicoProfissional selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirSindicoProfissional(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarProprietario()) {
            TelaConsultaProprietario consulta = new TelaConsultaProprietario(null, true);
            ConsultaProprietarioController controller = new ConsultaProprietarioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Proprietario selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirProprietario(selecionado);
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

    private void carregar(FuncaoMandato registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.selecionarItem(tela.getjComboBoxFuncao(), registro.getFuncao());
        Utils.preencherCampo(tela.getjTextFieldDataInicio(), registro.getDataInicio());
        Utils.preencherCampo(tela.getjTextFieldDataFim(), registro.getDataFim());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirSindicoProfissional(registro.getSindicoProfissional());
        definirProprietario(registro.getProprietario());
        definirEdificio(registro.getEdificio());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirSindicoProfissional(null);
        definirProprietario(null);
        definirEdificio(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final FuncaoMandato registro = new FuncaoMandato();
        try {
            registro.setId(idEmEdicao);
            registro.setFuncao(Utils.escolha(tela.getjComboBoxFuncao(), "funcao", true));
            registro.setDataInicio(Utils.data(tela.getjTextFieldDataInicio(), "data inicio", true));
            registro.setDataFim(Utils.data(tela.getjTextFieldDataFim(), "data fim", false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            registro.setSindicoProfissional(tela.getjComboBoxSindicoProfissional().getSelectedIndex() > 0 ? sindicoProfissionalSelecionado : null);
            registro.setProprietario(tela.getjComboBoxProprietario().getSelectedIndex() > 0 ? proprietarioSelecionado : null);
            if (edificioSelecionado == null || edificioSelecionado.getId() <= 0 || tela.getjComboBoxEdificio().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Edificio.");
            registro.setEdificio(edificioSelecionado);
            if (registro.getDataFim() != null && registro.getDataFim().isBefore(registro.getDataInicio())) throw new IllegalArgumentException("DataFim nao pode ser anterior a DataInicio.");
            if (registro.getSindicoProfissional() == null && registro.getProprietario() == null) throw new IllegalArgumentException("Selecione um sindico profissional ou proprietario para o mandato.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar FuncaoMandato", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirSindicoProfissional(SindicoProfissional registro) {
        sindicoProfissionalSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxSindicoProfissional(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNomeFantasia());
    }
    private void definirProprietario(Proprietario registro) {
        proprietarioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxProprietario(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNomeFantasia());
    }
    private void definirEdificio(Edificio registro) {
        edificioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxEdificio(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNome());
    }
}
