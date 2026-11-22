package controller.cadastro;

import controller.consulta.ConsultaEdificioController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Edificio;
import service.EdificioService;
import utils.Utils;
import view.cadastro.TelaCadastroEdificio;
import view.consulta.TelaConsultaEdificio;


public class CadastroEdificioController implements ActionListener {
    private final TelaCadastroEdificio tela;
    private final EdificioService service = new EdificioService();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;


    public CadastroEdificioController(TelaCadastroEdificio tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);

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
            TelaConsultaEdificio consulta = new TelaConsultaEdificio(null, true);
            ConsultaEdificioController controller = new ConsultaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Edificio registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);

        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(Edificio registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldNome(), registro.getNome());
        Utils.preencherCampo(tela.getjTextFieldQuantidadeAndares(), registro.getQuantidadeAndares());
        Utils.preencherCampo(tela.getjTextFieldQuantidadeUnidades(), registro.getQuantidadeUnidades());
        Utils.preencherCampo(tela.getjTextFieldCnpj(), registro.getCnpj());
        Utils.preencherCampo(tela.getjTextFieldAnoLancamento(), registro.getAnoLancamento());
        Utils.preencherCampo(tela.getjTextFieldAreaTotal(), registro.getAreaTotal());
        Utils.preencherCampo(tela.getjTextFieldCep(), registro.getCep());
        Utils.preencherCampo(tela.getjTextFieldLogradouro(), registro.getLogradouro());
        Utils.preencherCampo(tela.getjTextFieldCidade(), registro.getCidade());
        Utils.preencherCampo(tela.getjTextFieldBairro(), registro.getBairro());
        Utils.preencherCampo(tela.getjTextFieldComplemento(), registro.getComplemento());
        Utils.preencherCampo(tela.getjTextFieldNumeroUnidadeAgua(), registro.getNumeroUnidadeAgua());
        Utils.preencherCampo(tela.getjTextFieldNumeroUnidadeGas(), registro.getNumeroUnidadeGas());
        Utils.preencherCampo(tela.getjTextFieldPrecoM3Agua(), registro.getPrecoM3Agua());
        Utils.preencherCampo(tela.getjTextFieldPrecoM3Gas(), registro.getPrecoM3Gas());
        tela.getjRadioButton1().setSelected(tela.getjRadioButton1().getText().equals(registro.getFormulaCalculo()));
        tela.getjRadioButton2().setSelected(tela.getjRadioButton2().getText().equals(registro.getFormulaCalculo()));
        tela.getjTextAreaObservacao().setText(Utils.formatar(registro.getObservacao()));
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, tela.getBtnGroupCalculo());

    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final Edificio registro = new Edificio();
        try {
            registro.setId(idEmEdicao);
            registro.setNome(Utils.texto(tela.getjTextFieldNome(), "nome", 100, true));
            registro.setQuantidadeAndares(Utils.inteiro(tela.getjTextFieldQuantidadeAndares(), "quantidade de andares", 0, 2147483647));
            registro.setQuantidadeUnidades(Utils.inteiro(tela.getjTextFieldQuantidadeUnidades(), "quantidade de unidades", 0, 2147483647));
            registro.setCnpj(Utils.documento(tela.getjTextFieldCnpj(), "CNPJ", 14, 14, true));
            registro.setAnoLancamento(Utils.inteiro(tela.getjTextFieldAnoLancamento(), "ano de lançamento", 1, 9999));
            registro.setAreaTotal(Utils.numero(tela.getjTextFieldAreaTotal(), "área total", true));
            registro.setCep(Utils.documento(tela.getjTextFieldCep(), "CEP", 8, 8, true));
            registro.setLogradouro(Utils.texto(tela.getjTextFieldLogradouro(), "logradouro", 100, true));
            registro.setCidade(Utils.texto(tela.getjTextFieldCidade(), "cidade", 45, true));
            registro.setBairro(Utils.texto(tela.getjTextFieldBairro(), "bairro", 45, true));
            registro.setComplemento(Utils.texto(tela.getjTextFieldComplemento(), "complemento", 45, true));
            registro.setNumeroUnidadeAgua(Utils.texto(tela.getjTextFieldNumeroUnidadeAgua(), "número da unidade de água", 45, true));
            registro.setNumeroUnidadeGas(Utils.texto(tela.getjTextFieldNumeroUnidadeGas(), "número da unidade de gás", 45, true));
            registro.setPrecoM3Agua(Utils.preco(tela.getjTextFieldPrecoM3Agua(), "o m³ de água"));
            registro.setPrecoM3Gas(Utils.preco(tela.getjTextFieldPrecoM3Gas(), "o m³ de gás"));
            registro.setFormulaCalculo(tela.getjRadioButton1().isSelected() ? tela.getjRadioButton1().getText() : tela.getjRadioButton2().isSelected() ? tela.getjRadioButton2().getText() : null);
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observação", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Edifício", erro);
        JOptionPane.showMessageDialog(tela, "Não foi possível gravar. Verifique a conexão e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }


}
