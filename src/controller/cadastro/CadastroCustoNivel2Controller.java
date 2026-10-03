package controller.cadastro;

import controller.consulta.ConsultaCustoNivel2Controller;
import controller.consulta.ConsultaCustoNivel1Controller;
import view.consulta.TelaConsultaCustoNivel1;
import model.CustoNivel1;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.CustoNivel2;
import model.DAO.CustoNivel2DAO;
import utils.Utils;
import view.cadastro.TelaCadastroCustoNivel2;
import view.consulta.TelaConsultaCustoNivel2;

public class CadastroCustoNivel2Controller implements ActionListener {

    private final TelaCadastroCustoNivel2 tela;
    private final CustoNivel2DAO dao = new CustoNivel2DAO();
    private CustoNivel1 custoNivel1Selecionado;
    private int idEmEdicao;
    private boolean gravando;

    public CadastroCustoNivel2Controller(TelaCadastroCustoNivel2 telaCadastro) {
        this.tela = telaCadastro;
        tela.getjButtonNovo().addActionListener(this);
        tela.getjButtonCancelar().addActionListener(this);
        tela.getjButtonGravar().addActionListener(this);
        tela.getjButtonBuscar().addActionListener(this);
        tela.getjButtonSelecionarCustoNivel1().addActionListener(this);
        tela.getjButtonSair().addActionListener(this);
        tela.getjTextFieldId().setEditable(false);
        encerrarEdicao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gravando) return;
        if (e.getSource() == tela.getjButtonNovo()) {
            idEmEdicao = 0;
            definirCustoNivel1(null);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            tela.getjTextFieldDescricao().requestFocusInWindow();
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            encerrarEdicao();
        } else if (e.getSource() == tela.getjButtonGravar()) {
            gravarCustoNivel2();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaCustoNivel2 consulta = new TelaConsultaCustoNivel2(null, true);
            ConsultaCustoNivel2Controller controller = new ConsultaCustoNivel2Controller(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            CustoNivel2 selecionada = controller.getCustoSelecionado();
            if (selecionada != null) carregarCusto(selecionada);
        } else if (e.getSource() == tela.getjButtonSelecionarCustoNivel1()) {
            TelaConsultaCustoNivel1 consulta = new TelaConsultaCustoNivel1(null, true);
            ConsultaCustoNivel1Controller controller = new ConsultaCustoNivel1Controller(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            CustoNivel1 selecionado = controller.getCustoSelecionado();
            if (selecionado != null) definirCustoNivel1(selecionado);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregarCusto(CustoNivel2 custo) {
        Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        idEmEdicao = custo.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        tela.getjTextFieldDescricao().setText(custo.getDescricao());
        tela.getjTextAreaObservacao().setText(custo.getObservacao());
        definirCustoNivel1(custo.getCustoNivel1());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(custo.getStatus()) ? 0
                : "I".equals(custo.getStatus()) ? 1 : -1);
        tela.getjTextFieldDescricao().requestFocusInWindow();
    }

    private void definirCustoNivel1(CustoNivel1 custo) {
        custoNivel1Selecionado = custo;
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(new String[]{"Selecione"});
        if (custo != null) {
            modelo.addElement(custo.getId() + " - " + custo.getDescricao());
        }
        tela.getjComboBoxCustoNivel1().setModel(modelo);
        tela.getjComboBoxCustoNivel1().setSelectedIndex(custo == null ? 0 : 1);
    }

    private void encerrarEdicao() {
        idEmEdicao = 0;
        definirCustoNivel1(null);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
    }

    private void gravarCustoNivel2() {
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
        if (custoNivel1Selecionado == null || custoNivel1Selecionado.getId() <= 0
                || tela.getjComboBoxCustoNivel1().getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(tela, "Busque e selecione um custo de nivel 1.",
                    "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CustoNivel2 custo = new CustoNivel2(idEmEdicao, descricao,
                observacao.isEmpty() ? null : observacao,
                tela.getjComboBoxStatus().getSelectedIndex() == 0 ? "A" : "I", custoNivel1Selecionado);
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
                    JOptionPane.showMessageDialog(tela, "Custo nivel 2 gravado com sucesso.");
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
        tela.getjComboBoxCustoNivel1().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
        tela.getjButtonSelecionarCustoNivel1().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
        tela.getjComboBoxStatus().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar custo nivel 2", erro);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        JOptionPane.showMessageDialog(tela,
                "Nao foi possivel gravar. Verifique a conexao com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
