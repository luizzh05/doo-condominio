package controller.cadastro;

import controller.consulta.ConsultaAreaCompartilhadaController;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.AreaCompartilhada;
import service.AreaCompartilhadaService;
import utils.Utils;
import view.cadastro.TelaCadastroAreaCompartilhada;
import view.consulta.TelaConsultaAreaCompartilhada;

public class CadastroAreaCompartilhadaController implements ActionListener {

    private final TelaCadastroAreaCompartilhada tela;
    private final AreaCompartilhadaService service = new AreaCompartilhadaService();
    private int idEmEdicao;
    private boolean gravando;

    public CadastroAreaCompartilhadaController(TelaCadastroAreaCompartilhada telaCadastro) {
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
            gravarAreaCompartilhada();
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaAreaCompartilhada consulta = new TelaConsultaAreaCompartilhada(null, true);
            ConsultaAreaCompartilhadaController controller = new ConsultaAreaCompartilhadaController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            AreaCompartilhada selecionada = controller.getAreaSelecionada();
            if (selecionada != null) carregarArea(selecionada);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private void carregarArea(AreaCompartilhada area) {
        Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        idEmEdicao = area.getId();
        tela.getjTextFieldId().setText(Integer.toString(idEmEdicao));
        tela.getjTextFieldDescricao().setText(area.getDescricao());
        tela.getjTextAreaObservacao().setText(area.getObservacao());
        tela.getjComboBoxStatus().setSelectedIndex("A".equals(area.getStatus()) ? 0
                : "I".equals(area.getStatus()) ? 1 : -1);
        tela.getjTextFieldDescricao().requestFocusInWindow();
    }

    private void encerrarEdicao() {
        idEmEdicao = 0;
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
    }

    private void gravarAreaCompartilhada() {
        String descricao = tela.getDescricao().trim();
        String observacao = tela.getObservacao().trim();
        final AreaCompartilhada area;
        try {
            area = new AreaCompartilhada(idEmEdicao, descricao,
                    observacao.isEmpty() ? null : observacao,
                    Utils.status(tela.getjComboBoxStatus()));
            service.validar(area);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        gravando = true;
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                service.salvar(area, area.getId());
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    encerrarEdicao();
                    JOptionPane.showMessageDialog(tela, "Área compartilhada gravada com sucesso.");
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
        tela.getjComboBoxStatus().setEnabled(!ocupado && tela.getjButtonGravar().isEnabled());
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao gravar área compartilhada", erro);
        Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
        JOptionPane.showMessageDialog(tela,
                "Não foi possível gravar. Verifique a conexão com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
