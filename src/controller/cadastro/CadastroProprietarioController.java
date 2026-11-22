package controller.cadastro;

import controller.consulta.ConsultaProprietarioController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Proprietario;
import service.ProprietarioService;
import utils.Utils;
import view.cadastro.TelaCadastroProprietario;
import view.consulta.TelaConsultaProprietario;


public class CadastroProprietarioController implements ActionListener {
    private final TelaCadastroProprietario tela;
    private final ProprietarioService service = new ProprietarioService();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;


    public CadastroProprietarioController(TelaCadastroProprietario tela) {
        this.tela = tela;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjComboBoxTipoPessoa().addActionListener(e -> tela.alternarCamposPessoa());

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
            tela.getjTextFieldDataCadastro().setText(Utils.formatar(java.time.LocalDate.now()));
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            encerrarEdicao();
        } else if (e.getSource() == tela.getjButtonGravar()) {
            gravar();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaProprietario consulta = new TelaConsultaProprietario(null, true);
            ConsultaProprietarioController controller = new ConsultaProprietarioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Proprietario registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);

        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(Proprietario registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldNomeFantasia(), registro.getNomeFantasia());
        Utils.preencherCampo(tela.getjTextFieldRazaoSocial(), registro.getRazaoSocial());
        Utils.preencherCampo(tela.getjTextFieldCpf(), registro.getCpf());
        Utils.preencherCampo(tela.getjTextFieldRg(), registro.getRg());
        Utils.preencherCampo(tela.getjTextFieldCnpj(), registro.getCnpj());
        Utils.preencherCampo(tela.getjTextFieldInscricaoEstadual(), registro.getInscricaoEstadual());
        Utils.preencherCampo(tela.getjTextFieldFone1(), registro.getFone1());
        Utils.preencherCampo(tela.getjTextFieldFone2(), registro.getFone2());
        Utils.preencherCampo(tela.getjTextFieldEmail(), registro.getEmail());
        Utils.preencherCampo(tela.getjTextFieldDataNascimento(), registro.getDataNascimento());
        Utils.preencherCampo(tela.getjTextFieldDataCadastro(), registro.getDataCadastro());
        Utils.selecionarItem(tela.getjComboBoxEstadoCivil(), registro.getEstadoCivil());
        Utils.preencherCampo(tela.getjTextFieldCep(), registro.getCep());
        Utils.preencherCampo(tela.getjTextFieldLogradouro(), registro.getLogradouro());
        Utils.preencherCampo(tela.getjTextFieldCidade(), registro.getCidade());
        Utils.preencherCampo(tela.getjTextFieldBairro(), registro.getBairro());
        Utils.preencherCampo(tela.getjTextFieldComplemento(), registro.getComplemento());
        Utils.preencherCampo(tela.getjTextAreaObservacao(), registro.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(registro.getStatus()) ? 0 : "I".equals(registro.getStatus()) ? 1 : -1);
        Utils.selecionarItem(tela.getjComboBoxTipoPessoa(), registro.getCnpj() == null ? "Física" : "Jurídica");
        editando = true;
        definirOcupado(false);
    }

    private void limparRegistro() {
        idEmEdicao = 0;
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);

    }

    private void encerrarEdicao() {
        limparRegistro();
        editando = false;
        definirOcupado(false);
    }

    private void gravar() {
        final Proprietario registro = new Proprietario();
        try {
            registro.setId(idEmEdicao);
            String tipoPessoa = Utils.escolha(tela.getjComboBoxTipoPessoa(), "tipo de pessoa", true);
            registro.setNomeFantasia(Utils.texto(tela.getjTextFieldNomeFantasia(), "nome fantasia", 100, true));
            registro.setRazaoSocial("Jurídica".equals(tipoPessoa) ? Utils.texto(tela.getjTextFieldRazaoSocial(), "razão social", 100, false) : null);
            registro.setCpf("Física".equals(tipoPessoa) ? Utils.documento(tela.getjTextFieldCpf(), "CPF", 11, 11, false) : null);
            registro.setRg("Física".equals(tipoPessoa) ? Utils.texto(tela.getjTextFieldRg(), "RG", 10, false) : null);
            registro.setCnpj("Jurídica".equals(tipoPessoa) ? Utils.documento(tela.getjTextFieldCnpj(), "CNPJ", 14, 14, false) : null);
            registro.setInscricaoEstadual("Jurídica".equals(tipoPessoa) ? Utils.texto(tela.getjTextFieldInscricaoEstadual(), "inscrição estadual", 45, false) : null);
            registro.setFone1(Utils.documento(tela.getjTextFieldFone1(), "telefone 1", 10, 11, true));
            registro.setFone2(Utils.documento(tela.getjTextFieldFone2(), "telefone 2", 10, 11, false));
            registro.setEmail(Utils.texto(tela.getjTextFieldEmail(), "e-mail", 100, true));
            registro.setDataNascimento("Física".equals(tipoPessoa) ? Utils.data(tela.getjTextFieldDataNascimento(), "data de nascimento", false) : null);
            registro.setDataCadastro(Utils.data(tela.getjTextFieldDataCadastro(), "data de cadastro", true));
            registro.setEstadoCivil("Física".equals(tipoPessoa) ? Utils.escolha(tela.getjComboBoxEstadoCivil(), "estado civil", false) : null);
            registro.setCep(Utils.documento(tela.getjTextFieldCep(), "CEP", 8, 8, true));
            registro.setLogradouro(Utils.texto(tela.getjTextFieldLogradouro(), "logradouro", 45, true));
            registro.setCidade(Utils.texto(tela.getjTextFieldCidade(), "cidade", 45, true));
            registro.setBairro(Utils.texto(tela.getjTextFieldBairro(), "bairro", 45, true));
            registro.setComplemento(Utils.texto(tela.getjTextFieldComplemento(), "complemento", 100, false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observação", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            registro.setTipoPessoa(tipoPessoa);
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
        tela.alternarCamposPessoa();
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar Proprietário", erro);
        JOptionPane.showMessageDialog(tela, "Não foi possível gravar. Verifique a conexão e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }


}
