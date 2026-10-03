package controller.cadastro;

import controller.consulta.ConsultaSindicoProfissionalController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.SindicoProfissional;
import model.DAO.SindicoProfissionalDAO;
import utils.Utils;
import view.cadastro.TelaCadastroSindicoProfissional;
import view.consulta.TelaConsultaSindicoProfissional;


public class CadastroSindicoProfissionalController implements ActionListener {
    private final TelaCadastroSindicoProfissional tela;
    private final SindicoProfissionalDAO dao = new SindicoProfissionalDAO();
    private int idEmEdicao;
    private boolean editando;
    private boolean ocupado;


    public CadastroSindicoProfissionalController(TelaCadastroSindicoProfissional tela) {
        this.tela = tela;
        tela.getjComboBoxEstadoCivil().setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Selecione", "Solteiro", "Casado", "Divorciado", "Viuvo", "Uniao Estavel"}));
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
            tela.getjTextFieldDataCadastro().setText(Utils.formatar(java.time.LocalDate.now()));
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            encerrarEdicao();
        } else if (e.getSource() == tela.getjButtonGravar()) {
            gravar();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaSindicoProfissional consulta = new TelaConsultaSindicoProfissional(null, true);
            ConsultaSindicoProfissionalController controller = new ConsultaSindicoProfissionalController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            SindicoProfissional registro = controller.getRegistroSelecionado();
            if (registro != null) carregar(registro);

        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregar(SindicoProfissional registro) {
        limparRegistro();
        idEmEdicao = registro.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        Utils.preencherCampo(tela.getjTextFieldCra(), registro.getCra());
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
        final SindicoProfissional registro = new SindicoProfissional();
        try {
            registro.setId(idEmEdicao);
            registro.setCra(Utils.texto(tela.getjTextFieldCra(), "cra", 45, true));
            registro.setNomeFantasia(Utils.texto(tela.getjTextFieldNomeFantasia(), "nome fantasia", 100, true));
            registro.setRazaoSocial(Utils.texto(tela.getjTextFieldRazaoSocial(), "razao social", 100, false));
            registro.setCpf(Utils.documento(tela.getjTextFieldCpf(), "cpf", 11, 11, false));
            registro.setRg(Utils.texto(tela.getjTextFieldRg(), "rg", 10, false));
            registro.setCnpj(Utils.documento(tela.getjTextFieldCnpj(), "cnpj", 14, 14, false));
            registro.setInscricaoEstadual(Utils.texto(tela.getjTextFieldInscricaoEstadual(), "inscricao estadual", 45, false));
            registro.setFone1(Utils.documento(tela.getjTextFieldFone1(), "fone1", 10, 11, true));
            registro.setFone2(Utils.documento(tela.getjTextFieldFone2(), "fone2", 10, 11, false));
            registro.setEmail(Utils.texto(tela.getjTextFieldEmail(), "email", 100, true));
            registro.setDataNascimento(Utils.data(tela.getjTextFieldDataNascimento(), "data nascimento", false));
            registro.setDataCadastro(Utils.data(tela.getjTextFieldDataCadastro(), "data cadastro", true));
            registro.setEstadoCivil(Utils.escolha(tela.getjComboBoxEstadoCivil(), "estado civil", false));
            registro.setCep(Utils.documento(tela.getjTextFieldCep(), "cep", 8, 8, true));
            registro.setLogradouro(Utils.texto(tela.getjTextFieldLogradouro(), "logradouro", 45, true));
            registro.setCidade(Utils.texto(tela.getjTextFieldCidade(), "cidade", 45, true));
            registro.setBairro(Utils.texto(tela.getjTextFieldBairro(), "bairro", 45, true));
            registro.setComplemento(Utils.texto(tela.getjTextFieldComplemento(), "complemento", 100, false));
            registro.setObservacao(Utils.texto(tela.getjTextAreaObservacao(), "observacao", 100, false));
            registro.setStatus(Utils.status(tela.getjComboBoxStatus()));
            if (!registro.getEmail().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) throw new IllegalArgumentException("Informe um email valido.");
            if (registro.getDataNascimento() != null && registro.getDataNascimento().isAfter(java.time.LocalDate.now())) throw new IllegalArgumentException("A data de nascimento nao pode estar no futuro.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar SindicoProfissional", erro);
        JOptionPane.showMessageDialog(tela, "Nao foi possivel gravar. Verifique a conexao e os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }


}
