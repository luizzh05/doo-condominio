package controller.cadastro;

import controller.consulta.ConsultaMovimentoCaixaController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.MovimentoCaixa;
import model.DAO.MovimentoCaixaDAO;
import utils.Utils;
import view.cadastro.TelaCadastroMovimentoCaixa;
import view.consulta.TelaConsultaMovimentoCaixa;
import model.Edificio;
import controller.consulta.ConsultaEdificioController;
import view.consulta.TelaConsultaEdificio;
import model.Fornecedor;
import controller.consulta.ConsultaFornecedorController;
import view.consulta.TelaConsultaFornecedor;
import model.CustoNivel1;
import controller.consulta.ConsultaCustoNivel1Controller;
import view.consulta.TelaConsultaCustoNivel1;
import model.CustoNivel2;
import controller.consulta.ConsultaCustoNivel2Controller;
import view.consulta.TelaConsultaCustoNivel2;

public class CadastroMovimentoCaixaController implements ActionListener {
    private final TelaCadastroMovimentoCaixa tela;
    private final MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;
    private Edificio edificioSelecionado;
    private Fornecedor fornecedorSelecionado;
    private CustoNivel1 custoNivel1Selecionado;
    private CustoNivel2 custoNivel2Selecionado;

    public CadastroMovimentoCaixaController(TelaCadastroMovimentoCaixa tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjButtonSelecionarEdificio().addActionListener(this);
        tela.getjButtonSelecionarFornecedor().addActionListener(this);
        tela.getjButtonSelecionarCustoNivel1().addActionListener(this);
        tela.getjButtonSelecionarCustoNivel2().addActionListener(this);
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
            TelaConsultaMovimentoCaixa consulta = new TelaConsultaMovimentoCaixa(null, true);
            ConsultaMovimentoCaixaController controller = new ConsultaMovimentoCaixaController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            MovimentoCaixa registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);
        } else if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio consulta = new TelaConsultaEdificio(null, true);
            ConsultaEdificioController controller = new ConsultaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Edificio selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirEdificio(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarFornecedor()) {
            TelaConsultaFornecedor consulta = new TelaConsultaFornecedor(null, true);
            ConsultaFornecedorController controller = new ConsultaFornecedorController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Fornecedor selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirFornecedor(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarCustoNivel1()) {
            TelaConsultaCustoNivel1 consulta = new TelaConsultaCustoNivel1(null, true);
            ConsultaCustoNivel1Controller controller = new ConsultaCustoNivel1Controller(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            CustoNivel1 selecionado = controller.getCustoSelecionado();
            if (selecionado != null) definirCustoNivel1(selecionado);
        } else if (e.getSource() == tela.getjButtonSelecionarCustoNivel2()) {
            TelaConsultaCustoNivel2 consulta = new TelaConsultaCustoNivel2(null, true);
            ConsultaCustoNivel2Controller controller = new ConsultaCustoNivel2Controller(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            CustoNivel2 selecionado = controller.getCustoSelecionado();
            if (selecionado != null) definirCustoNivel2(selecionado);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(MovimentoCaixa registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldDataEmissao(), registro.getDataEmissao());
        Utils.preencherCampo(tela.getjTextFieldDataVencimento(), registro.getDataVencimento());
        Utils.preencherCampo(tela.getjTextFieldDataPagamento(), registro.getDataPagamento());
        Utils.preencherCampo(tela.getjTextFieldValorEmitido(), registro.getValorEmitido());
        Utils.preencherCampo(tela.getjTextFieldMultas(), registro.getMultas());
        Utils.preencherCampo(tela.getjTextFieldCorrecaoMonetaria(), registro.getCorrecaoMonetaria());
        Utils.preencherCampo(tela.getjTextFieldJuros(), registro.getJuros());
        Utils.preencherCampo(tela.getjTextFieldValorPagoRec(), registro.getValorPagoRec());
        Utils.selecionarItem(tela.getjComboBoxTipo(), registro.getTipo());
        tela.getjCheckBoxFlagRateio().setSelected(registro.isFlagRateio());
        Utils.preencherCampo(tela.getjTextFieldFlagFormula(), registro.getFlagFormula());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        definirEdificio(registro.getEdificio());
        definirFornecedor(registro.getFornecedor());
        definirCustoNivel1(registro.getCustoNivel1());
        definirCustoNivel2(registro.getCustoNivel2());
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        definirEdificio(null);
        definirFornecedor(null);
        definirCustoNivel1(null);
        definirCustoNivel2(null);
    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final MovimentoCaixa registro = new MovimentoCaixa();
        try {
            registro.setId(idEmEdicao);
            registro.setDataEmissao(Utils.data(tela.getjTextFieldDataEmissao(), "data emissao", true));
            registro.setDataVencimento(Utils.data(tela.getjTextFieldDataVencimento(), "data vencimento", true));
            registro.setDataPagamento(Utils.data(tela.getjTextFieldDataPagamento(), "data pagamento", false));
            registro.setValorEmitido(Utils.numero(tela.getjTextFieldValorEmitido(), "valor emitido", true));
            registro.setMultas(Utils.numero(tela.getjTextFieldMultas(), "multas", false));
            registro.setCorrecaoMonetaria(Utils.numero(tela.getjTextFieldCorrecaoMonetaria(), "correcao monetaria", false));
            registro.setJuros(Utils.numero(tela.getjTextFieldJuros(), "juros", false));
            registro.setValorPagoRec(Utils.numero(tela.getjTextFieldValorPagoRec(), "valor pagamento", false));
            registro.setTipo(Utils.escolha(tela.getjComboBoxTipo(), "tipo", true));
            registro.setFlagRateio(tela.getjCheckBoxFlagRateio().isSelected());
            registro.setFlagFormula(Utils.texto(tela.getjTextFieldFlagFormula(), "flag formula", 45, false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (edificioSelecionado == null || edificioSelecionado.getId() <= 0 || tela.getjComboBoxEdificio().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Edificio.");
            registro.setEdificio(edificioSelecionado);
            if (fornecedorSelecionado == null || fornecedorSelecionado.getId() <= 0 || tela.getjComboBoxFornecedor().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione Fornecedor.");
            registro.setFornecedor(fornecedorSelecionado);
            if (custoNivel1Selecionado == null || custoNivel1Selecionado.getId() <= 0 || tela.getjComboBoxCustoNivel1().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione CustoNivel1.");
            registro.setCustoNivel1(custoNivel1Selecionado);
            if (custoNivel2Selecionado == null || custoNivel2Selecionado.getId() <= 0 || tela.getjComboBoxCustoNivel2().getSelectedIndex() <= 0) throw new IllegalArgumentException("Busque e selecione CustoNivel2.");
            registro.setCustoNivel2(custoNivel2Selecionado);
            if (registro.getDataVencimento() != null && registro.getDataVencimento().isBefore(registro.getDataEmissao())) throw new IllegalArgumentException("DataVencimento nao pode ser anterior a DataEmissao.");
            if (custoNivel2Selecionado.getCustoNivel1() == null || custoNivel2Selecionado.getCustoNivel1().getId() != custoNivel1Selecionado.getId()) throw new IllegalArgumentException("Os custos de nivel 1 e 2 devem pertencer ao mesmo grupo.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar MovimentoCaixa", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void definirEdificio(Edificio registro) {
        edificioSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxEdificio(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNome());
    }
    private void definirFornecedor(Fornecedor registro) {
        fornecedorSelecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxFornecedor(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getNomeFantasia());
    }
    private void definirCustoNivel1(CustoNivel1 registro) {
        if (registro != null && custoNivel2Selecionado != null && (custoNivel2Selecionado.getCustoNivel1() == null || custoNivel2Selecionado.getCustoNivel1().getId() != registro.getId())) definirCustoNivel2(null);
        custoNivel1Selecionado = registro;
        Utils.definirVinculo(tela.getjComboBoxCustoNivel1(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getDescricao());
    }
    private void definirCustoNivel2(CustoNivel2 registro) {
        custoNivel2Selecionado = registro;
        if (registro != null) definirCustoNivel1(registro.getCustoNivel1());
        Utils.definirVinculo(tela.getjComboBoxCustoNivel2(), registro == null ? 0 : registro.getId(),
                registro == null ? null : registro.getDescricao());
    }
}
