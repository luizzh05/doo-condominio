package controller.cadastro;

import controller.consulta.ConsultaReservaController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Reserva;
import service.ReservaService;
import utils.Utils;
import view.cadastro.TelaCadastroReserva;
import view.consulta.TelaConsultaReserva;
import model.AreaCompartilhadaEdificio;
import controller.consulta.ConsultaAreaCompartilhadaEdificioController;
import view.consulta.TelaConsultaAreaCompartilhadaEdificio;
import model.UnidadeCondomino;
import controller.consulta.ConsultaUnidadeCondominoController;
import view.consulta.TelaConsultaUnidadeCondomino;

public class CadastroReservaController implements ActionListener {
    private final TelaCadastroReserva tela;
    private final ReservaService service = new ReservaService();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private AreaCompartilhadaEdificio areaCompartilhadaEdificioSelecionado;
    private UnidadeCondomino unidadeCondominoSelecionado;

    public CadastroReservaController(TelaCadastroReserva tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarAreaCompartilhadaEdificio().addActionListener(this);
        tela.getjButtonSelecionarUnidadeCondomino().addActionListener(this);
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
            TelaConsultaReserva consulta = new TelaConsultaReserva(null, true);
            ConsultaReservaController controller = new ConsultaReservaController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Reserva registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarAreaCompartilhadaEdificio()) {
            TelaConsultaAreaCompartilhadaEdificio consulta = new TelaConsultaAreaCompartilhadaEdificio(null, true);
            ConsultaAreaCompartilhadaEdificioController controller = new ConsultaAreaCompartilhadaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            AreaCompartilhadaEdificio selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirAreaCompartilhadaEdificio(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarUnidadeCondomino()) {
            TelaConsultaUnidadeCondomino consulta = new TelaConsultaUnidadeCondomino(null, true);
            ConsultaUnidadeCondominoController controller = new ConsultaUnidadeCondominoController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            UnidadeCondomino selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirUnidadeCondomino(selecionado);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(Reserva registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldDataHoraInicio(), registro.getDataHoraInicio());
        Utils.preencherCampo(tela.getjTextFieldDataHoraFim(), registro.getDataHoraFim());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirAreaCompartilhadaEdificio(registro.getAreaCompartilhadaEdificio());
        definirUnidadeCondomino(registro.getUnidadeCondomino());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirAreaCompartilhadaEdificio(null);
        definirUnidadeCondomino(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final Reserva registro = new Reserva();
        try {
            registro.setId(idEmEdicao);
            registro.setDataHoraInicio(Utils.dataHora(tela.getjTextFieldDataHoraInicio(), "data e hora de início"));
            registro.setDataHoraFim(Utils.dataHora(tela.getjTextFieldDataHoraFim(), "data e hora de fim"));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observação", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            registro.setAreaCompartilhadaEdificio(tela.getjComboBoxAreaCompartilhadaEdificio().getSelectedIndex() > 0 ? areaCompartilhadaEdificioSelecionado : null);
            registro.setUnidadeCondomino(tela.getjComboBoxUnidadeCondomino().getSelectedIndex() > 0 ? unidadeCondominoSelecionado : null);
            service.validar(registro);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                service.salvar(registro, registro.getId());
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Reserva", erro);
        JOptionPane.showMessageDialog(tela, "Não foi possível gravar. Verifique a conexão e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirAreaCompartilhadaEdificio(AreaCompartilhadaEdificio registro) {
        areaCompartilhadaEdificioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxAreaCompartilhadaEdificio(), registro == null ? 0 : registro.getId(),
                registro == null ? null : (registro.getAreaCompartilhada() == null ? "" : registro.getAreaCompartilhada().getDescricao()));
    }
    private void definirUnidadeCondomino(UnidadeCondomino registro) {
        unidadeCondominoSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxUnidadeCondomino(), registro == null ? 0 : registro.getId(),
                registro == null ? null : (registro.getUnidade() == null ? "" : registro.getUnidade().getDescricao()));
    }
}
