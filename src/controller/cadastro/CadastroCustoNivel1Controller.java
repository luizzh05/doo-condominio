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
import model.DAO.CustoNivel1DAO;
import utils.Utils;
import view.cadastro.TelaCadastroCustoNivel1;
import view.consulta.TelaConsultaCustoNivel1;

public class CadastroCustoNivel1Controller implements ActionListener {

    private final TelaCadastroCustoNivel1 tela;
    private final CustoNivel1DAO dao = new CustoNivel1DAO();
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
        if (descricao.isEmpty() || descricao.length() > 100) {
            JOptionPane.showMessageDialog(tela, "Informe uma descricao de ate 100 caracteres.",
                    "Validacao", JOptionPane.WARNING_MESSAGE);
            tela.getjTextFieldDescricao().requestFocusInWindow();
            return;
        }
        if (observacao.length() > 100) {
            JOptionPane.showMessageDialog(tela, "A observacao deve ter ate 100 caracteres.",
                    "Validacao", JOptionPane.WARNING_MESSAGE);
            tela.getjTextAreaObservacao().requestFocusInWindow();
            return;
        }
        if (tela.getjComboBoxStatus().getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(tela, "Selecione o status.", "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tela.getjComboBoxTipoCc().getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(tela, "Selecione o tipo CC (Credito ou Debito).",
                    "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CustoNivel1 custo = new CustoNivel1(idEmEdicao, descricao,
                String.valueOf(tela.getjComboBoxTipoCc().getSelectedItem()),
                observacao.isEmpty() ? null : observacao,
                tela.getjComboBoxStatus().getSelectedIndex() == 0 ? "A" : "I");
        gravando = true;
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if (custo.getId() == 0) dao.create(custo);
                else dao.update(custo);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    encerrarEdicao();
                    JOptionPane.showMessageDialog(tela, "Custo nivel 1 gravado com sucesso.");
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
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar custo nivel 1", erro);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        JOptionPane.showMessageDialog(tela,
                "Nao foi possivel gravar. Verifique a conexao com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
