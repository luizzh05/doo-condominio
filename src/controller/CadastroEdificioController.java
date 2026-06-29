package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Edificio;
import utils.DataStore;
import utils.Utils;
import view.cadastro.TelaCadastroEdificio;
import view.consulta.TelaConsultaEdificio;

public class CadastroEdificioController implements ActionListener {

    private final TelaCadastroEdificio tela;

    public CadastroEdificioController(TelaCadastroEdificio telaCadastroEdificio) {
        this.tela = telaCadastroEdificio;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);

        Utils.ativaDesativaBtn(this.tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(this.tela.getjPanelDados(), false, this.tela.getBtnGroupCalculo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjButtonNovo()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, tela.getBtnGroupCalculo());
            tela.getjTextFieldId().setEnabled(false);

        } else if (e.getSource() == tela.getjButtonCancelar()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, tela.getBtnGroupCalculo());

        } else if (e.getSource() == tela.getjButtonGravar()) {
            if (gravarEdificio()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, tela.getBtnGroupCalculo());
            }

        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaEdificio telaConsulta = new TelaConsultaEdificio(null, true);
            telaConsulta.setVisible(true);
            Edificio selecionado = telaConsulta.getEdificioSelecionado();
            if (selecionado != null) {
                carregarEdificio(selecionado);
            }

        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarEdificio() {
        String nome = tela.getjTextFieldNome().getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nome do edificio e obrigatorio!", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
            tela.getjTextFieldNome().requestFocus();
            return false;
        }

        int id = parseIntSafe(tela.getjTextFieldId().getText());
        String cnpj = tela.getjTextFieldCnpj().getText().trim();
        int anoLancamento = parseIntSafe(tela.getjTextFieldAnoLancamento().getText());
        float areaTotal = parseFloatSafe(tela.getjTextFieldAreaTotal().getText());
        int qtdAndares = parseIntSafe(tela.getjTextFieldQuantidadeAndares().getText());
        int qtdUnidades = parseIntSafe(tela.getjTextFieldQuantidadeUnidades().getText());
        String cep = tela.getjTextFieldCep().getText().trim();
        String logradouro = tela.getjTextFieldLogradouro().getText().trim();
        String cidade = tela.getjTextFieldCidade().getText().trim();
        String bairro = tela.getjTextFieldBairro().getText().trim();
        String complemento = tela.getjTextFieldComplemento().getText().trim();
        String numAgua = tela.getjTextFieldNumeroUnidadeAgua().getText().trim();
        String numGas = tela.getjTextFieldNumeroUnidadeGas().getText().trim();
        String obs = tela.getjTextAreaObservacao().getText().trim();

        String formula = "";
        if (tela.getjRadioButton2().isSelected()) formula = "Por m2";
        else if (tela.getjRadioButton1().isSelected()) formula = "Por Und.";

        Edificio edificio = new Edificio(id, nome, qtdAndares, qtdUnidades, cnpj, anoLancamento,
                areaTotal, cep, logradouro, cidade, bairro, complemento, numAgua, numGas,
                formula, obs, "A");

        DataStore.getInstance().salvarEdificio(edificio);

        JOptionPane.showMessageDialog(tela,
                "Edificio '" + nome + "' salvo com sucesso! (ID: " + edificio.getId() + ")",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    private void carregarEdificio(Edificio e) {
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        Utils.LimpaComponentes(tela.getjPanelDados(), true, tela.getBtnGroupCalculo());
        tela.getjTextFieldId().setEnabled(false);

        tela.getjTextFieldId().setText(String.valueOf(e.getId()));
        tela.getjTextFieldNome().setText(nvl(e.getNome()));
        tela.getjTextFieldCnpj().setText(nvl(e.getCnpj()));
        tela.getjTextFieldAnoLancamento().setText(e.getAnoLancamento() > 0 ? String.valueOf(e.getAnoLancamento()) : "");
        tela.getjTextFieldAreaTotal().setText(e.getAreaTotal() > 0 ? String.valueOf(e.getAreaTotal()) : "");
        tela.getjTextFieldQuantidadeAndares().setText(e.getQuantidadeAndares() > 0 ? String.valueOf(e.getQuantidadeAndares()) : "");
        tela.getjTextFieldQuantidadeUnidades().setText(e.getQuantidadeUnidades() > 0 ? String.valueOf(e.getQuantidadeUnidades()) : "");
        tela.getjTextFieldCep().setText(nvl(e.getCep()));
        tela.getjTextFieldLogradouro().setText(nvl(e.getLogradouro()));
        tela.getjTextFieldCidade().setText(nvl(e.getCidade()));
        tela.getjTextFieldBairro().setText(nvl(e.getBairro()));
        tela.getjTextFieldComplemento().setText(nvl(e.getComplemento()));
        tela.getjTextFieldNumeroUnidadeAgua().setText(nvl(e.getNumeroUnidadeAgua()));
        tela.getjTextFieldNumeroUnidadeGas().setText(nvl(e.getNumeroUnidadeGas()));
        tela.getjTextAreaObservacao().setText(nvl(e.getObservacao()));

        if ("Por m2".equals(e.getFormulaCalculo())) {
            tela.getjRadioButton2().setSelected(true);
        } else if ("Por Und.".equals(e.getFormulaCalculo())) {
            tela.getjRadioButton1().setSelected(true);
        }
    }

    private int parseIntSafe(String s) {
        try {
            String clean = s.replaceAll("[^0-9]", "").trim();
            return clean.isEmpty() ? 0 : Integer.parseInt(clean);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private float parseFloatSafe(String s) {
        try {
            String clean = s.replace(",", ".").replaceAll("[^0-9.]", "").trim();
            return clean.isEmpty() ? 0f : Float.parseFloat(clean);
        } catch (NumberFormatException ex) {
            return 0f;
        }
    }

    private String nvl(String s) {
        return s != null ? s : "";
    }
}
