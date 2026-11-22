package controller.cadastro;

import controller.consulta.ConsultaEdificioController;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Condominio;
import model.Edificio;
import service.GestaoCondominioService;
import service.PreviaGeracaoCondominio;
import utils.Utils;
import view.cadastro.TelaGestaoCondominio;
import view.consulta.TelaConsultaEdificio;

public class GestaoCondominioController implements ActionListener {

    private final TelaGestaoCondominio tela;
    private final GestaoCondominioService service = new GestaoCondominioService();
    private final DefaultTableModel modelo;
    private final TableRowSorter<DefaultTableModel> sorter;
    private Edificio edificioSelecionado;
    private boolean ocupado;

    public GestaoCondominioController(TelaGestaoCondominio tela) {
        this.tela = tela;
        tela.getjButtonSelecionarEdificio().addActionListener(this);
        tela.getjButtonGerar().addActionListener(this);
        tela.getjButtonPesquisar().addActionListener(this);
        tela.getjButtonLimpar().addActionListener(this);
        tela.getjButtonFechar().addActionListener(this);
        modelo = new DefaultTableModel(new Object[]{"Mes", "Ano", "Unidade", "Valor Emitido", "Proprietario", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0, 1 -> Integer.class;
                    case 3 -> Double.class;
                    default -> String.class;
                };
            }
        };
        tela.getjTableResultado().setModel(modelo);
        NumberFormat moedaTabela = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        tela.getjTableResultado().getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.RIGHT);
            }
            @Override
            protected void setValue(Object value) {
                setText(value instanceof Number n ? moedaTabela.format(n.doubleValue()) : "");
            }
        });
        tela.getjTableResultado().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "none");
        tela.getjTableResultado().getInputMap(JComponent.WHEN_FOCUSED)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "none");
        sorter = new TableRowSorter<>(modelo);
        tela.getjTableResultado().setRowSorter(sorter);
        tela.getjTextFieldFiltroUnidade().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { aplicarFiltro(); }
            @Override
            public void removeUpdate(DocumentEvent e) { aplicarFiltro(); }
            @Override
            public void changedUpdate(DocumentEvent e) { aplicarFiltro(); }
        });
        tela.getjComboBoxStatus().addActionListener(this);
        LocalDate hoje = LocalDate.now();
        tela.getjTextFieldMes().setText(Integer.toString(hoje.getMonthValue()));
        tela.getjTextFieldAno().setText(Integer.toString(hoje.getYear()));
        definirEdificio(null);
        registrarAtalho(KeyStroke.getKeyStroke("F5"), "pesquisar", tela.getjButtonPesquisar());
        registrarAtalho(KeyStroke.getKeyStroke("F8"), "gerar", tela.getjButtonGerar());
        registrarAtalho(KeyStroke.getKeyStroke("ESCAPE"), "fechar", tela.getjButtonFechar());
    }

    private void registrarAtalho(KeyStroke tecla, String nome, javax.swing.JButton botao) {
        tela.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(tecla, nome);
        tela.getRootPane().getActionMap().put(nome, new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { botao.doClick(); }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjComboBoxStatus()) {
            aplicarFiltro();
            return;
        }
        if (ocupado) return;
        if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio consulta = new TelaConsultaEdificio(null, true);
            ConsultaEdificioController controller = new ConsultaEdificioController(consulta);
            consulta.setLocationRelativeTo(tela);
            consulta.setVisible(true);
            Edificio selecionado = controller.getRegistroSelecionado();
            if (selecionado != null) definirEdificio(selecionado);
        } else if (e.getSource() == tela.getjButtonGerar()) {
            gerar();
        } else if (e.getSource() == tela.getjButtonPesquisar()) {
            pesquisar();
        } else if (e.getSource() == tela.getjButtonLimpar()) {
            limpar();
        } else if (e.getSource() == tela.getjButtonFechar()) {
            tela.dispose();
        }
    }

    private void definirEdificio(Edificio edificio) {
        edificioSelecionado = edificio;
        Utils.definirVinculo(tela.getjComboBoxEdificio(), edificio == null ? 0 : edificio.getId(),
                edificio == null ? null : edificio.getNome());
    }

    private record Parametros(Edificio edificio, int mes, int ano, YearMonth referencia) {}

    private Parametros lerParametros() {
        try {
            Edificio edificio = tela.getjComboBoxEdificio().getSelectedIndex() > 0 ? edificioSelecionado : null;
            if (edificio == null) throw new IllegalArgumentException("Busque e selecione o Edificio.");
            int mes = Utils.inteiro(tela.getjTextFieldMes(), "mes", 1, 12);
            int ano = Utils.inteiro(tela.getjTextFieldAno(), "ano", 2000, 2100);
            YearMonth ref = GestaoCondominioService.referenciaDe(mes, ano);
            tela.getjLabelReferenciaValor().setText(String.format("%02d/%04d", ref.getMonthValue(), ref.getYear()));
            return new Parametros(edificio, mes, ano, ref);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validacao", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    private void pesquisar() {
        Parametros p = lerParametros();
        if (p == null) return;
        definirOcupado(true);
        new SwingWorker<List<Condominio>, Void>() {
            @Override
            protected List<Condominio> doInBackground() {
                return service.listar(p.edificio().getId(), p.referencia());
            }

            @Override
            protected void done() {
                try {
                    preencherTabela(get());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarErro(ex);
                } catch (ExecutionException ex) {
                    tratarErro(ex.getCause());
                } finally {
                    definirOcupado(false);
                }
            }
        }.execute();
    }

    private void gerar() {
        Parametros p = lerParametros();
        if (p == null) return;
        definirOcupado(true);
        new SwingWorker<PreviaGeracaoCondominio, Void>() {
            @Override
            protected PreviaGeracaoCondominio doInBackground() {
                return service.calcular(p.edificio(), p.mes(), p.ano());
            }

            @Override
            protected void done() {
                PreviaGeracaoCondominio previa = null;
                try {
                    previa = get();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarErro(ex);
                } catch (ExecutionException ex) {
                    tratarErro(ex.getCause());
                } finally {
                    definirOcupado(false);
                }
                if (previa != null) confirmarEGravar(previa);
            }
        }.execute();
    }

    private void confirmarEGravar(PreviaGeracaoCondominio previa) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        YearMonth ref = previa.getReferencia();
        StringBuilder msg = new StringBuilder(String.format("Gerar %d condominios de %02d/%04d, total %s?",
                previa.getCondominios().size(), ref.getMonthValue(), ref.getYear(), moeda.format(previa.getValorTotal())));
        for (String aviso : previa.getAvisos()) msg.append("\n- ").append(aviso);
        Object mensagem = msg.toString();
        if (!previa.getAvisos().isEmpty()) {
            JTextArea area = new JTextArea(msg.toString(), 10, 60);
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setCaretPosition(0);
            mensagem = new JScrollPane(area);
        }
        int opcao = JOptionPane.showConfirmDialog(tela, mensagem, "Confirmar geracao",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao != JOptionPane.YES_OPTION) return;
        definirOcupado(true);
        new SwingWorker<Integer, Void>() {
            @Override
            protected Integer doInBackground() {
                return service.gravar(previa);
            }

            @Override
            protected void done() {
                int gravados;
                try {
                    gravados = get();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarErro(ex);
                    definirOcupado(false);
                    return;
                } catch (ExecutionException ex) {
                    tratarErro(ex.getCause());
                    definirOcupado(false);
                    return;
                }
                JOptionPane.showMessageDialog(tela, String.format("%d condominios gerados para %02d/%04d.",
                        gravados, ref.getMonthValue(), ref.getYear()), "Geracao",
                        JOptionPane.INFORMATION_MESSAGE);
                recarregarAposGravar(previa);
            }
        }.execute();
    }

    private void recarregarAposGravar(PreviaGeracaoCondominio previa) {
        new SwingWorker<List<Condominio>, Void>() {
            @Override
            protected List<Condominio> doInBackground() {
                return service.listar(previa.getEdificio().getId(), previa.getReferencia());
            }

            @Override
            protected void done() {
                try {
                    preencherTabela(get());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    avisarFalhaRecarga(ex);
                } catch (ExecutionException ex) {
                    avisarFalhaRecarga(ex.getCause());
                } finally {
                    definirOcupado(false);
                }
            }
        }.execute();
    }

    private void avisarFalhaRecarga(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro ao recarregar grade apos geracao", erro);
        JOptionPane.showMessageDialog(tela,
                "Condominios gerados, mas nao foi possivel recarregar a grade. Use Pesquisar.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void tratarErro(Throwable causa) {
        if (causa instanceof IllegalArgumentException) {
            JOptionPane.showMessageDialog(tela, causa.getMessage(), "Validacao", JOptionPane.WARNING_MESSAGE);
        } else {
            mostrarErro(causa);
        }
    }

    private void preencherTabela(List<Condominio> registros) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        modelo.setRowCount(0);
        BigDecimal total = BigDecimal.ZERO;
        for (Condominio c : registros) {
            var uc = c.getUnidadeCondomino();
            String unidade = uc == null || uc.getUnidade() == null ? "" : uc.getUnidade().getDescricao();
            String prop = uc == null || uc.getProprietario() == null || uc.getProprietario().getNomeFantasia() == null
                    ? "" : uc.getProprietario().getNomeFantasia();
            String status = "A".equals(c.getStatus()) ? "Ativo" : "I".equals(c.getStatus()) ? "Inativo" : c.getStatus();
            modelo.addRow(new Object[]{c.getMesReferencia(), c.getAnoReferencia(), unidade,
                c.getValorEmitido(), prop, status});
            total = total.add(BigDecimal.valueOf(c.getValorEmitido()));
        }
        tela.getjLabelTotal().setText(registros.size() + " condominios | Total emitido: "
                + moeda.format(total.setScale(2, java.math.RoundingMode.HALF_UP)));
        aplicarFiltro();
    }

    private void aplicarFiltro() {
        List<RowFilter<DefaultTableModel, Integer>> filtros = new ArrayList<>();
        String txt = tela.getjTextFieldFiltroUnidade().getText().trim();
        if (!txt.isEmpty()) filtros.add(RowFilter.regexFilter("(?i)" + Pattern.quote(txt), 2));
        String status = String.valueOf(tela.getjComboBoxStatus().getSelectedItem());
        if ("Ativo".equals(status)) filtros.add(RowFilter.regexFilter("^Ativo$", 5));
        else if ("Inativo".equals(status)) filtros.add(RowFilter.regexFilter("^Inativo$", 5));
        sorter.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
    }

    private void limpar() {
        definirEdificio(null);
        LocalDate hoje = LocalDate.now();
        tela.getjTextFieldMes().setText(Integer.toString(hoje.getMonthValue()));
        tela.getjTextFieldAno().setText(Integer.toString(hoje.getYear()));
        tela.getjTextFieldFiltroUnidade().setText("");
        tela.getjComboBoxStatus().setSelectedIndex(0);
        modelo.setRowCount(0);
        tela.getjLabelTotal().setText("");
        tela.getjLabelReferenciaValor().setText("--/----");
    }

    private void definirOcupado(boolean estado) {
        ocupado = estado;
        tela.setCursor(Cursor.getPredefinedCursor(estado ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        tela.setDefaultCloseOperation(estado ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
                : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Utils.habilitarComponentes(tela.getjPanelbotoes(), !estado);
        Utils.habilitarComponentes(tela.getjPanelParametros(), !estado);
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro na gestao de Condominio", erro);
        JOptionPane.showMessageDialog(tela,
                "Nao foi possivel concluir a operacao. Verifique a conexao com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
