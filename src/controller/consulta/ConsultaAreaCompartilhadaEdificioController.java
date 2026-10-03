package controller.consulta;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import model.AreaCompartilhadaEdificio;
import utils.Utils;
import model.DAO.AreaCompartilhadaEdificioDAO;
import view.consulta.TelaConsultaAreaCompartilhadaEdificio;

public class ConsultaAreaCompartilhadaEdificioController implements ActionListener {

    private final TelaConsultaAreaCompartilhadaEdificio tela;
    private final AreaCompartilhadaEdificioDAO dao = new AreaCompartilhadaEdificioDAO();
    private List<AreaCompartilhadaEdificio> resultados = List.of();
    private AreaCompartilhadaEdificio registroSelecionado;
    private boolean pesquisando;

    public ConsultaAreaCompartilhadaEdificioController(TelaConsultaAreaCompartilhadaEdificio tela) {
        this.tela = tela;
        tela.getjButtonSelecionar().addActionListener(this);
        tela.getjButtonPesquisar().addActionListener(this);
        tela.getjButtonLimpar().addActionListener(this);
        tela.getjButtonFechar().addActionListener(this);
        tela.getjTextFieldFiltro().addActionListener(this);
        tela.getjButtonSelecionar().setEnabled(false);
        tela.getjTableResultado().setModel(new DefaultTableModel(new Object[]{"ID", "Area Compartilhada", "Edificio", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) { pesquisar(); }
        });
    }

    public AreaCompartilhadaEdificio getRegistroSelecionado() { return registroSelecionado; }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (pesquisando) return;
        if (action.getSource() == tela.getjButtonSelecionar()) {
            int linha = tela.getjTableResultado().getSelectedRow();
            if (linha < 0) {
                JOptionPane.showMessageDialog(tela, "Selecione um registro na tabela.",
                        "Selecao", JOptionPane.WARNING_MESSAGE);
                return;
            }
            registroSelecionado = resultados.get(tela.getjTableResultado().convertRowIndexToModel(linha));
            tela.dispose();
        } else if (action.getSource() == tela.getjButtonPesquisar()
                || action.getSource() == tela.getjTextFieldFiltro()) {
            pesquisar();
        } else if (action.getSource() == tela.getjButtonLimpar()) {
            tela.getjTextFieldFiltro().setText("");
            tela.getjComboBoxCampoFiltro().setSelectedIndex(0);
            pesquisar();
        } else if (action.getSource() == tela.getjButtonFechar()) {
            tela.dispose();
        }
    }

    private void pesquisar() {
        String campo = String.valueOf(tela.getjComboBoxCampoFiltro().getSelectedItem());
        String filtro = tela.getjTextFieldFiltro().getText().trim();
        String parametro = switch (campo) {
            case "ID" -> "id";
            case "Area Compartilhada", "Selecione" -> "area_compartilhada";
            case "Edificio" -> "edificio";
            case "Status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca invalido");
        };
        try {
            if ("id".equals(parametro)) {
                if (Integer.parseInt(filtro) <= 0) throw new NumberFormatException();
            }
            if ("mes_referencia".equals(parametro) || "ano_referencia".equals(parametro)) {
                int numero = Integer.parseInt(filtro);
                if (numero < 1 || ("mes_referencia".equals(parametro) && numero > 12)) throw new NumberFormatException();
            }
            if ("status".equals(parametro) && !filtro.isEmpty()) {
                filtro = switch (filtro.toUpperCase(Locale.ROOT)) {
                    case "A", "ATIVO", "A - ATIVO" -> "A";
                    case "I", "INATIVO", "I - INATIVO" -> "I";
                    default -> throw new IllegalArgumentException("Informe Ativo ou Inativo no filtro de status.");
                };
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(tela, "Informe um numero valido para o filtro (ID/ano positivo, mes de 1 a 12).",
                    "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validacao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ("cpf".equals(parametro) || "cnpj".equals(parametro)) filtro = filtro.replaceAll("[^0-9]", "");
        String valor = filtro;
        definirOcupado(true);
        // Limpa resultados anteriores para nao selecionar registros de um filtro antigo.
        preencherTabela(List.of());
        new SwingWorker<List<AreaCompartilhadaEdificio>, Void>() {
            @Override
            protected List<AreaCompartilhadaEdificio> doInBackground() { return dao.retrieve(parametro, valor); }

            @Override
            protected void done() {
                try {
                    preencherTabela(get());
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

    private void preencherTabela(List<AreaCompartilhadaEdificio> registros) {
        resultados = registros;
        DefaultTableModel modelo = (DefaultTableModel) tela.getjTableResultado().getModel();
        modelo.setRowCount(0);
        for (AreaCompartilhadaEdificio registro : registros) {
            String status = "A".equals(registro.getStatus()) ? "Ativo"
                    : "I".equals(registro.getStatus()) ? "Inativo" : registro.getStatus();
            modelo.addRow(new Object[]{registro.getId(), (registro.getAreaCompartilhada() == null ? "" : registro.getAreaCompartilhada().getDescricao()), (registro.getEdificio() == null ? "" : registro.getEdificio().getNome()), status});
        }
    }

    private void definirOcupado(boolean ocupado) {
        pesquisando = ocupado;
        tela.setCursor(Cursor.getPredefinedCursor(ocupado ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        tela.setDefaultCloseOperation(ocupado ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
                : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        tela.getjButtonPesquisar().setEnabled(!ocupado);
        tela.getjButtonLimpar().setEnabled(!ocupado);
        tela.getjButtonSelecionar().setEnabled(!ocupado && !resultados.isEmpty());
        tela.getjButtonFechar().setEnabled(!ocupado);
        tela.getjComboBoxCampoFiltro().setEnabled(!ocupado);
        tela.getjTextFieldFiltro().setEnabled(!ocupado);
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao consultar AreaCompartilhadaEdificio", erro);
        JOptionPane.showMessageDialog(tela,
                "Nao foi possivel consultar. Verifique a conexao com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
