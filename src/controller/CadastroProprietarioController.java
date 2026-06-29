package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Proprietario;
import utils.DataStore;
import utils.Utils;
import view.cadastro.TelaCadastroProprietario;
import view.consulta.TelaConsultaProprietario;

public class CadastroProprietarioController extends CadastroControllerBase {

    private final TelaCadastroProprietario tela;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CadastroProprietarioController(TelaCadastroProprietario telaCadastro) {
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
            JOptionPane.showMessageDialog(tela, "Nome e obrigatorio!", "Erro de Validacao", JOptionPane.ERROR_MESSAGE);
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
        String tipoPessoa = (String) tela.getjComboBoxTipoPessoa().getSelectedItem();
        String cep = tela.getjTextFieldCep().getText().trim();
        String logradouro = tela.getjTextFieldLogradouro().getText().trim();
        String cidade = tela.getjTextFieldCidade().getText().trim();
        String bairro = tela.getjTextFieldBairro().getText().trim();
        String complemento = tela.getjTextFieldComplemento().getText().trim();
        String obs = tela.getjTextAreaObservacao().getText().trim();

        LocalDate dataNasc = parseDataSafe(dataNascStr);
        LocalDate dataCad = parseDataSafe(dataCadStr);
        if (dataCad == null) dataCad = LocalDate.now();

        String tipo = "Juridica".equals(tipoPessoa) ? "J" : "F";

        Proprietario proprietario = new Proprietario(id, nomeFantasia, razaoSocial, cpf, rg, cnpj,
                inscricaoEstadual, fone1, fone2, email, dataNasc, dataCad,
                estadoCivil, cep, logradouro, cidade, bairro, complemento, obs, tipo, "A");

        DataStore.getInstance().salvarProprietario(proprietario);

        JOptionPane.showMessageDialog(tela,
                "Proprietario '" + nomeFantasia + "' salvo com sucesso! (ID: " + proprietario.getId() + ")",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaProprietario telaConsulta = new TelaConsultaProprietario(null, true);
        telaConsulta.setVisible(true);
        Proprietario selecionado = telaConsulta.getProprietarioSelecionado();
        if (selecionado != null) {
            carregarProprietario(selecionado);
        }
    }

    private void carregarProprietario(Proprietario p) {
        Utils.ativaDesativaBtn(getPainelBotoes(), true);
        Utils.LimpaComponentes(getPainelDados(), true, getGrupoRadio());
        tela.getjTextFieldId().setEnabled(false);

        tela.getjTextFieldId().setText(String.valueOf(p.getId()));
        tela.getjTextFieldNomeFantasia().setText(nvl(p.getNomeFantasia()));
        tela.getjTextFieldRazaoSocial().setText(nvl(p.getRazaoSocial()));
        tela.getjTextFieldCpf().setText(nvl(p.getCpf()));
        tela.getjTextFieldRg().setText(nvl(p.getRg()));
        tela.getjTextFieldCnpj().setText(nvl(p.getCnpj()));
        tela.getjTextFieldInscricaoEstadual().setText(nvl(p.getInscricaoEstadual()));
        tela.getjTextFieldFone1().setText(nvl(p.getFone1()));
        tela.getjTextFieldFone2().setText(nvl(p.getFone2()));
        tela.getjTextFieldEmail().setText(nvl(p.getEmail()));
        tela.getjTextFieldDataNascimento().setText(p.getDataNascimento() != null ? p.getDataNascimento().format(FMT) : "");
        tela.getjTextFieldDataCadastro().setText(p.getDataCadastro() != null ? p.getDataCadastro().format(FMT) : "");
        if (p.getEstadoCivil() != null) {
            tela.getjComboBoxEstadoCivil().setSelectedItem(p.getEstadoCivil());
        }
        String tipoPessoa = "J".equals(p.getTipoPessoa()) ? "Juridica" : "Fisica";
        tela.getjComboBoxTipoPessoa().setSelectedItem(tipoPessoa);
        tela.getjTextFieldCep().setText(nvl(p.getCep()));
        tela.getjTextFieldLogradouro().setText(nvl(p.getLogradouro()));
        tela.getjTextFieldCidade().setText(nvl(p.getCidade()));
        tela.getjTextFieldBairro().setText(nvl(p.getBairro()));
        tela.getjTextFieldComplemento().setText(nvl(p.getComplemento()));
        tela.getjTextAreaObservacao().setText(nvl(p.getObservacao()));
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
