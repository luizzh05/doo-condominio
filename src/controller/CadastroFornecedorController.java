package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Fornecedor;
import utils.DataStore;
import utils.Utils;
import view.cadastro.TelaCadastroFornecedor;
import view.consulta.TelaConsultaFornecedor;

public class CadastroFornecedorController extends CadastroControllerBase {

    private final TelaCadastroFornecedor tela;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CadastroFornecedorController(TelaCadastroFornecedor telaCadastro) {
        super(telaCadastro);
        this.tela = telaCadastro;
    }

    @Override
    protected void aoNovo() {
        tela.getjTextFieldId().setEnabled(false);
    }

    @Override
    protected boolean gravar() {
        String nomeFantasia = tela.getjTextFieldNomeFantasia().getText().trim();
        if (nomeFantasia.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nome Fantasia e obrigatorio!", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
            tela.getjTextFieldNomeFantasia().requestFocus();
            return false;
        }

        int id = parseIntSafe(tela.getjTextFieldId().getText());
        String razaoSocial = tela.getjTextFieldRazaoSocial().getText().trim();
        String cpf = tela.getjTextFieldCpf().getText().trim();
        String rg = tela.getjTextFieldRg().getText().trim();
        String cnpj = tela.getjTextFieldCnpj().getText().trim();
        String inscricaoEstadual = tela.getjTextFieldInscricaoEstadual().getText().trim();
        String fone1 = tela.getjTextFieldFone1().getText().trim();
        String fone2 = tela.getjTextFieldFone2().getText().trim();
        String email = tela.getjTextFieldEmail().getText().trim();
        String dataNascStr = tela.getjTextFieldDataNascimento().getText().trim();
        String dataCadStr = tela.getjTextFieldDataCadastro().getText().trim();
        String estadoCivil = (String) tela.getjComboBoxEstadoCivil().getSelectedItem();
        String cep = tela.getjTextFieldCep().getText().trim();
        String logradouro = tela.getjTextFieldLogradouro().getText().trim();
        String cidade = tela.getjTextFieldCidade().getText().trim();
        String bairro = tela.getjTextFieldBairro().getText().trim();
        String complemento = tela.getjTextFieldComplemento().getText().trim();
        String obs = tela.getjTextAreaObservacao().getText().trim();

        LocalDate dataNasc = parseDataSafe(dataNascStr);
        LocalDate dataCad = parseDataSafe(dataCadStr);
        if (dataCad == null) dataCad = LocalDate.now();

        Fornecedor fornecedor = new Fornecedor(id, nomeFantasia, razaoSocial, cpf, rg, cnpj,
                inscricaoEstadual, fone1, fone2, email, dataNasc, dataCad,
                estadoCivil, cep, logradouro, cidade, bairro, complemento, obs, "J", "A");

        DataStore.getInstance().salvarFornecedor(fornecedor);

        JOptionPane.showMessageDialog(tela,
                "Fornecedor '" + nomeFantasia + "' salvo com sucesso! (ID: " + fornecedor.getId() + ")",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaFornecedor telaConsulta = new TelaConsultaFornecedor(null, true);
        telaConsulta.setVisible(true);
        Fornecedor selecionado = telaConsulta.getFornecedorSelecionado();
        if (selecionado != null) {
            carregarFornecedor(selecionado);
        }
    }

    private void carregarFornecedor(Fornecedor f) {
        Utils.ativaDesativaBtn(getPainelBotoes(), true);
        Utils.LimpaComponentes(getPainelDados(), true, getGrupoRadio());
        tela.getjTextFieldId().setEnabled(false);

        tela.getjTextFieldId().setText(String.valueOf(f.getId()));
        tela.getjTextFieldNomeFantasia().setText(nvl(f.getNomeFantasia()));
        tela.getjTextFieldRazaoSocial().setText(nvl(f.getRazaoSocial()));
        tela.getjTextFieldCpf().setText(nvl(f.getCpf()));
        tela.getjTextFieldRg().setText(nvl(f.getRg()));
        tela.getjTextFieldCnpj().setText(nvl(f.getCnpj()));
        tela.getjTextFieldInscricaoEstadual().setText(nvl(f.getInscricaoEstadual()));
        tela.getjTextFieldFone1().setText(nvl(f.getFone1()));
        tela.getjTextFieldFone2().setText(nvl(f.getFone2()));
        tela.getjTextFieldEmail().setText(nvl(f.getEmail()));
        tela.getjTextFieldDataNascimento().setText(f.getDataNascimento() != null ? f.getDataNascimento().format(FMT) : "");
        tela.getjTextFieldDataCadastro().setText(f.getDataCadastro() != null ? f.getDataCadastro().format(FMT) : "");
        if (f.getEstadoCivil() != null) {
            tela.getjComboBoxEstadoCivil().setSelectedItem(f.getEstadoCivil());
        }
        tela.getjTextFieldCep().setText(nvl(f.getCep()));
        tela.getjTextFieldLogradouro().setText(nvl(f.getLogradouro()));
        tela.getjTextFieldCidade().setText(nvl(f.getCidade()));
        tela.getjTextFieldBairro().setText(nvl(f.getBairro()));
        tela.getjTextFieldComplemento().setText(nvl(f.getComplemento()));
        tela.getjTextAreaObservacao().setText(nvl(f.getObservacao()));
    }

    private int parseIntSafe(String s) {
        try {
            String clean = s.replaceAll("[^0-9]", "").trim();
            return clean.isEmpty() ? 0 : Integer.parseInt(clean);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private LocalDate parseDataSafe(String s) {
        if (s == null || s.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(s.trim(), FMT);
        } catch (Exception ex) {
            return null;
        }
    }

    private String nvl(String s) {
        return s != null ? s : "";
    }
}
