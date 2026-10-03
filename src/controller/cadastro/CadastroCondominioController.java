package controller.cadastro;

import controller.consulta.ConsultaCondominioController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Condominio;
import model.DAO.CondominioDAO;
import utils.Utils;
import view.cadastro.TelaCadastroCondominio;
import view.consulta.TelaConsultaCondominio;
import model.UnidadeCondomino;
import controller.consulta.ConsultaUnidadeCondominoController;
import view.consulta.TelaConsultaUnidadeCondomino;

public class CadastroCondominioController implements ActionListener {
    private final TelaCadastroCondominio tela;
    private final CondominioDAO dao = new CondominioDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private UnidadeCondomino unidadeCondominoSelecionado;

    public CadastroCondominioController(TelaCadastroCondominio tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
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
            TelaConsultaCondominio consulta = new TelaConsultaCondominio(null, true);
            ConsultaCondominioController controller = new ConsultaCondominioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Condominio registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
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

    private void carregar(Condominio registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldMesReferencia(), registro.getMesReferencia());
        Utils.preencherCampo(tela.getjTextFieldAnoReferencia(), registro.getAnoReferencia());
        Utils.preencherCampo(tela.getjTextFieldDataEmissao(), registro.getDataEmissao());
        Utils.preencherCampo(tela.getjTextFieldDataVencimento(), registro.getDataVencimento());
        Utils.preencherCampo(tela.getjTextFieldDataPagamento(), registro.getDataPagamento());
        Utils.preencherCampo(tela.getjTextFieldJuros(), registro.getJuros());
        Utils.preencherCampo(tela.getjTextFieldMultas(), registro.getMultas());
        Utils.preencherCampo(tela.getjTextFieldCorrecao(), registro.getCorrecao());
        Utils.preencherCampo(tela.getjTextFieldValorEmitido(), registro.getValorEmitido());
        Utils.preencherCampo(tela.getjTextFieldValorPago(), registro.getValorPago());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirUnidadeCondomino(registro.getUnidadeCondomino());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirUnidadeCondomino(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final Condominio registro = new Condominio();
        try {
            registro.setId(idEmEdicao);
            registro.setMesReferencia(Utils.inteiro(tela.getjTextFieldMesReferencia(), "mes referencia", 1, 12));
            registro.setAnoReferencia(Utils.inteiro(tela.getjTextFieldAnoReferencia(), "ano referencia", 1, 9999));
            registro.setDataEmissao(Utils.data(tela.getjTextFieldDataEmissao(), "data emissao", true));
            registro.setDataVencimento(Utils.data(tela.getjTextFieldDataVencimento(), "data vencimento", true));
            registro.setDataPagamento(Utils.data(tela.getjTextFieldDataPagamento(), "data pagamento", false));
            registro.setJuros(Utils.numero(tela.getjTextFieldJuros(), "juros", false));
            registro.setMultas(Utils.numero(tela.getjTextFieldMultas(), "multas", false));
            registro.setCorrecao(Utils.numero(tela.getjTextFieldCorrecao(), "correcao", false));
            registro.setValorEmitido(Utils.numero(tela.getjTextFieldValorEmitido(), "valor emitido", true));
            registro.setValorPago(Utils.numero(tela.getjTextFieldValorPago(), "valor pago", false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (unidadeCondominoSelecionado == null || unidadeCondominoSelecionado.getId() <= 0 || tela.getjComboBoxUnidadeCondomino().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione UnidadeCondomino.");
            registro.setUnidadeCondomino(unidadeCondominoSelecionado);
            if (registro.getDataVencimento() != null && registro.getDataVencimento().isBefore(registro.getDataEmissao())) throw new IllegalArgumentException("DataVencimento nao pode ser anterior a DataEmissao.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Condominio", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirUnidadeCondomino(UnidadeCondomino registro) {
        unidadeCondominoSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxUnidadeCondomino(), registro == null ? 0 : registro.getId(),
                registro == null ? null : (registro.getUnidade() == null ? "" : registro.getUnidade().getDescricao()));
    }
}
