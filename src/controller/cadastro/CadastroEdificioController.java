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
import model.DAO.EdificioDAO;
import utils.Utils;
import view.cadastro.TelaCadastroEdificio;
import view.consulta.TelaConsultaEdificio;


public class CadastroEdificioController implements ActionListener {
    private final TelaCadastroEdificio tela;
    private final EdificioDAO dao = new EdificioDAO();
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
        tela.getjTextFieldNome().setText(Utils.formatar(registro.getNome()));
        tela.getjTextFieldQuantidadeAndares().setText(Utils.formatar(registro.getQuantidadeAndares()));
        tela.getjTextFieldQuantidadeUnidades().setText(Utils.formatar(registro.getQuantidadeUnidades()));
        tela.getjTextFieldCnpj().setText(Utils.formatar(registro.getCnpj()));
        tela.getjTextFieldAnoLancamento().setText(Utils.formatar(registro.getAnoLancamento()));
        tela.getjTextFieldAreaTotal().setText(Utils.formatar(registro.getAreaTotal()));
        tela.getjTextFieldCep().setText(Utils.formatar(registro.getCep()));
        tela.getjTextFieldLogradouro().setText(Utils.formatar(registro.getLogradouro()));
        tela.getjTextFieldCidade().setText(Utils.formatar(registro.getCidade()));
        tela.getjTextFieldBairro().setText(Utils.formatar(registro.getBairro()));
        tela.getjTextFieldComplemento().setText(Utils.formatar(registro.getComplemento()));
        tela.getjTextFieldNumeroUnidadeAgua().setText(Utils.formatar(registro.getNumeroUnidadeAgua()));
        tela.getjTextFieldNumeroUnidadeGas().setText(Utils.formatar(registro.getNumeroUnidadeGas()));
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
            registro.setQuantidadeAndares(Utils.inteiro(tela.getjTextFieldQuantidadeAndares(), "quantidade andares", 0, 2147483647));
            registro.setQuantidadeUnidades(Utils.inteiro(tela.getjTextFieldQuantidadeUnidades(), "quantidade unidades", 0, 2147483647));
            registro.setCnpj(Utils.documento(tela.getjTextFieldCnpj(), "cnpj", 14, 14, true));
            registro.setAnoLancamento(Utils.inteiro(tela.getjTextFieldAnoLancamento(), "ano lancamento", 1, 9999));
            registro.setAreaTotal(Utils.numero(tela.getjTextFieldAreaTotal(), "area total", true));
            registro.setCep(Utils.documento(tela.getjTextFieldCep(), "cep", 8, 8, true));
            registro.setLogradouro(Utils.texto(tela.getjTextFieldLogradouro(), "logradouro", 100, true));
            registro.setCidade(Utils.texto(tela.getjTextFieldCidade(), "cidade", 45, true));
            registro.setBairro(Utils.texto(tela.getjTextFieldBairro(), "bairro", 45, true));
            registro.setComplemento(Utils.texto(tela.getjTextFieldComplemento(), "complemento", 45, true));
            registro.setNumeroUnidadeAgua(Utils.texto(tela.getjTextFieldNumeroUnidadeAgua(), "numero unidade agua", 45, true));
            registro.setNumeroUnidadeGas(Utils.texto(tela.getjTextFieldNumeroUnidadeGas(), "numero unidade gas", 45, true));
            registro.setFormulaCalculo(tela.getjRadioButton1().isSelected() ? tela.getjRadioButton1().getText() : tela.getjRadioButton2().isSelected() ? tela.getjRadioButton2().getText() : null);
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Edificio", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }


}
