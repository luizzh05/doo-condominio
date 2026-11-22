package controller.cadastro;

import controller.consulta.ConsultaCustoNivel1Controller;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.CustoNivel1;
import service.CustoNivel1Service;
import utils.Utils;
import view.cadastro.TelaCadastroCustoNivel1;
import view.consulta.TelaConsultaCustoNivel1;

public class CadastroCustoNivel1Controller implements ActionListener {

    private final TelaCadastroCustoNivel1 tela;
    private final CustoNivel1Service service = new CustoNivel1Service();
    private int idEmEdicao;
    private boolean gravando;

    public CadastroCustoNivel1Controller(TelaCadastroCustoNivel1 telaCadastro) {
        this.tela = telaCadastro;
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
        if (gravando) return;
        if (e.getSource() == tela.getjButtonNovo()) {
            idEmEdicao = 0;
            Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            tela.getjTextFieldDescricao().requestFocusInWindow();
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            encerrarEdicao();
        } else if (e.getSource() == tela.getjButtonGravar()) {
            gravarCustoNivel1();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaCustoNivel1 consulta = new TelaConsultaCustoNivel1(null, true);
            ConsultaCustoNivel1Controller controller = new ConsultaCustoNivel1Controller(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            CustoNivel1 selecionada = controller.getCustoSelecionado();
            if (selecionada != null) carregarCusto(selecionada);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregarCusto(CustoNivel1 custo) {
        Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        idEmEdicao = custo.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        tela.getjTextFieldDescricao().setText(custo.getDescricao());
        tela.getjTextAreaObservacao().setText(custo.getObservacao());
        tela.getjComboBoxTipoCc().setSelectedItem(custo.getTipoCc());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(custo.getStatus()) ? 0
                : "I".equals(custo.getStatus()) ? 1 : -1);
        tela.getjTextFieldDescricao().requestFocusInWindow();
    }

    private void encerrarEdicao() {
        idEmEdicao = 0;
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
    }

    private void gravarCustoNivel1() {
        String descricao = tela.getjTextFieldDescricao().getText().trim();
        String observacao = tela.getjTextAreaObservacao().getText().trim();
        final CustoNivel1 custo;
        try {
            custo = new CustoNivel1(idEmEdicao, descricao,
                    Utils.escolha(tela.getjComboBoxTipoCc(), "tipo CC (Crédito ou Débito)", true),
                    observacao.isEmpty() ? null : observacao,
                    Utils.status(tela.getjComboBoxStatus()));
            service.validar(custo);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gravando = true;
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                service.salvar(custo, custo.getId());
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    encerrarEdicao();
                    JOptionPane.showMessageDialog(tela, "Custo nível 1 gravado com sucesso.");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarErro(ex);
                } catch (ExecutionException ex) {
                    mostrarErro(ex.getCause());
                } finally {
                    gravando = false;
                    definirOcupado(false);
                }
            }
        }.execute();
    }

    private void definirOcupado(boolean ocupado) {
        tela.setCursor(Cursor.getPredefinedCursor(ocupado ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        tela.setDefaultCloseOperation(ocupado ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
                : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        for (Component componente : tela.getjPanelbotoes().getComponents()) {
            if (ocupado) componente.setEnabled(false);
        }
        tela.getjTextFieldDescricao().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
        tela.getjTextAreaObservacao().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
        tela.getjComboBoxTipoCc().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
        tela.getjComboBoxStatus().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar custo nível 1", erro);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        JOptionPane.showMessageDialog(tela,
                "Não foi possível gravar. Verifique a conexão com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
