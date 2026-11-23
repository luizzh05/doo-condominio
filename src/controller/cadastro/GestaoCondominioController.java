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
import service.CalculadoraCondominio;
import service.GestaoCondominioService;
import service.PreviaGeracaoCondominio;
import utils.Utils;
import view.cadastro.TelaCadastroCondominio;
import view.cadastro.TelaGestaoCondominio;
import view.consulta.TelaConsultaEdificio;

public class GestaoCondominioController implements ActionListener {

    private final TelaGestaoCondominio tela;
    private final GestaoCondominioService service = new GestaoCondominioService();
    private final DefaultTableModel modelo;
    private final TableRowSorter<DefaultTableModel> sorter;
    private static final java.time.format.DateTimeFormatter DATA = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String VALOR_MONETARIO = "(-?[0-9]+\\.[0-9]{2})";
    private static final Pattern DETALHAMENTO = Pattern.compile("Gerado: Und " + VALOR_MONETARIO
            + "; m² " + VALOR_MONETARIO + "; Água " + VALOR_MONETARIO + "; Gás " + VALOR_MONETARIO);
    private Edificio edificioSelecionado;
    private boolean ocupado;
    private List<Condominio> registros = List.of();
    /** Edifício e referência da grade atual, para recarregar depois de editar/pagar/processar. */
    private int edificioGrade;
    private YearMonth referenciaGrade;

    public GestaoCondominioController(TelaGestaoCondominio tela) {
        this.tela = tela;
        tela.getjButtonSelecionarEdificio().addActionListener(this);
        tela.getjButtonGerar().addActionListener(this);
        tela.getjButtonPesquisar().addActionListener(this);
        tela.getjButtonLimpar().addActionListener(this);
        tela.getjButtonFechar().addActionListener(this);
        tela.getjButtonEditar().addActionListener(this);
        tela.getjButtonPagar().addActionListener(this);
        tela.getjButtonProcessar().addActionListener(this);
        tela.getjButtonProtestar().addActionListener(this);
        modelo = new DefaultTableModel(new Object[]{"Mês", "Ano", "Unidade", "Vencimento", "Valor Emitido", "Valor Pago", "Proprietário", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0, 1 -> Integer.class;
                    case 3 -> LocalDate.class;
                    case 4 -> BigDecimal.class;
                    case 5 -> Double.class;
                    default -> String.class;
                };
            }
        };
        tela.getjTableResultado().setModel(modelo);
        int[] larguras = {45, 55, 130, 90, 110, 110, 180, 80};
        for (int i = 0; i < larguras.length; i++) {
            tela.getjTableResultado().getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
        }
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        tela.getjTableResultado().getColumnModel().getColumn(0).setCellRenderer(centro);
        tela.getjTableResultado().getColumnModel().getColumn(1).setCellRenderer(centro);
        tela.getjTableResultado().getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            @Override
            protected void setValue(Object value) {
                setText(value instanceof LocalDate d ? d.format(DATA) : "");
            }
        });
        NumberFormat moedaTabela = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        DefaultTableCellRenderer moedaRenderer = new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.RIGHT);
            }
            @Override
            protected void setValue(Object value) {
                setText(value instanceof Number n ? moedaTabela.format(n.doubleValue()) : "");
            }
        };
        tela.getjTableResultado().getColumnModel().getColumn(4).setCellRenderer(moedaRenderer);
        tela.getjTableResultado().getColumnModel().getColumn(5).setCellRenderer(moedaRenderer);
        tela.getjTableResultado().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) atualizarAcoes();
        });
        tela.getjTableResultado().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && !ocupado && tela.getjTableResultado().rowAtPoint(e.getPoint()) >= 0) editar();
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
        tela.getjComboBoxEdificio().addActionListener(e -> atualizarAcoes());
        tela.aoSelecionarConta(e -> atualizarGrade());
        YearMonth competencia = YearMonth.now().minusMonths(1);
        tela.getjTextFieldMes().setText(Integer.toString(competencia.getMonthValue()));
        tela.getjTextFieldAno().setText(Integer.toString(competencia.getYear()));
        DocumentListener periodo = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { atualizarCompetencia(); }
            @Override
            public void removeUpdate(DocumentEvent e) { atualizarCompetencia(); }
            @Override
            public void changedUpdate(DocumentEvent e) { atualizarCompetencia(); }
        };
        tela.getjTextFieldMes().getDocument().addDocumentListener(periodo);
        tela.getjTextFieldAno().getDocument().addDocumentListener(periodo);
        definirEdificio(null);
        atualizarCompetencia();
        registrarAtalho(KeyStroke.getKeyStroke("F5"), "pesquisar", tela.getjButtonPesquisar());
        registrarAtalho(KeyStroke.getKeyStroke("F8"), "gerar", tela.getjButtonGerar());
        registrarAtalho(KeyStroke.getKeyStroke("ESCAPE"), "fechar", tela.getjButtonFechar());
        atualizarAcoes();
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
            if (selecionado != null) {
                definirEdificio(selecionado);
                preencherTabela(List.of(), selecionado.getId(), null);
                pesquisar();
            }
        } else if (e.getSource() == tela.getjButtonGerar()) {
            gerar();
        } else if (e.getSource() == tela.getjButtonPesquisar()) {
            pesquisar();
        } else if (e.getSource() == tela.getjButtonLimpar()) {
            limpar();
        } else if (e.getSource() == tela.getjButtonFechar()) {
            tela.dispose();
        } else if (e.getSource() == tela.getjButtonEditar()) {
            editar();
        } else if (e.getSource() == tela.getjButtonPagar()) {
            pagar();
        } else if (e.getSource() == tela.getjButtonProcessar()) {
            processar();
        } else if (e.getSource() == tela.getjButtonProtestar()) {
            protestar();
        }
    }

    private Condominio selecionado() {
        int linha = tela.getjTableResultado().getSelectedRow();
        if (linha < 0) return null;
        int indice = tela.getjTableResultado().convertRowIndexToModel(linha);
        return indice < registros.size() ? registros.get(indice) : null;
    }

    private Condominio exigirSelecionado() {
        Condominio c = selecionado();
        if (c == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um condomínio na grade.", "Validação", JOptionPane.WARNING_MESSAGE);
        }
        return c;
    }

    private void atualizarAcoes() {
        Condominio c = selecionado();
        boolean livre = !ocupado;
        boolean ativo = edificioSelecionado != null && "A".equals(edificioSelecionado.getStatus());
        tela.getjButtonGerar().setEnabled(livre && ativo && tela.getjComboBoxEdificio().getSelectedIndex() > 0);
        tela.getjButtonGerar().setToolTipText(edificioSelecionado != null && !ativo
                ? "Edifício inativo. Use Pesquisar para consultar cobranças existentes." : null);
        tela.getjButtonEditar().setEnabled(livre && c != null);
        tela.getjButtonPagar().setEnabled(livre && c != null
                && (Condominio.ATIVO.equals(c.getStatus()) || Condominio.PROCESSADO.equals(c.getStatus())));
        tela.getjButtonProcessar().setEnabled(livre && c != null && Condominio.ATIVO.equals(c.getStatus()));
        tela.getjButtonProtestar().setEnabled(livre && c != null && Condominio.PAGO.equals(c.getStatus()));
    }

    private void editar() {
        Condominio c = exigirSelecionado();
        if (c == null) return;
        Condominio atual;
        try {
            atual = service.buscar(c.getId());
        } catch (RuntimeException ex) {
            tratarErro(ex);
            return;
        }
        atual.setUnidadeCondomino(c.getUnidadeCondomino());
        TelaCadastroCondominio cadastro = new TelaCadastroCondominio(null, true);
        CadastroCondominioController controller = new CadastroCondominioController(cadastro);
        controller.editar(atual);
        cadastro.setLocationRelativeTo(tela);
        cadastro.setVisible(true);
        if (controller.isGravou()) recarregar();
    }

    private void pagar() {
        Condominio c = exigirSelecionado();
        if (c == null) return;
        Condominio atual;
        try {
            atual = service.buscar(c.getId());
        } catch (RuntimeException ex) {
            tratarErro(ex);
            return;
        }
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        javax.swing.JTextField campoData = new javax.swing.JTextField(LocalDate.now().format(DATA), 10);
        javax.swing.JTextField campoCorrecao = new javax.swing.JTextField("0,00", 10);
        javax.swing.JLabel lblDias = new javax.swing.JLabel();
        javax.swing.JLabel lblMulta = new javax.swing.JLabel();
        javax.swing.JLabel lblJuros = new javax.swing.JLabel();
        javax.swing.JLabel lblPago = new javax.swing.JLabel();
        lblPago.setFont(lblPago.getFont().deriveFont(java.awt.Font.BOLD, 14f));
        CalculadoraCondominio.Pagamento[] calculo = new CalculadoraCondominio.Pagamento[1];
        Runnable recalcular = () -> {
            try {
                LocalDate data = LocalDate.parse(campoData.getText().trim(), DATA);
                BigDecimal correcao = lerValor(campoCorrecao.getText());
                calculo[0] = service.simularPagamento(atual, data, correcao);
                lblDias.setText(Long.toString(calculo[0].diasAtraso()));
                lblMulta.setText(moeda.format(calculo[0].multa()));
                lblJuros.setText(moeda.format(calculo[0].juros()));
                lblPago.setText(moeda.format(calculo[0].valorPago()));
            } catch (RuntimeException ex) {
                calculo[0] = null;
                lblDias.setText("-");
                lblMulta.setText("-");
                lblJuros.setText("-");
                lblPago.setText("informe data e correção válidas");
            }
        };
        DocumentListener ouvinte = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { recalcular.run(); }
            @Override
            public void removeUpdate(DocumentEvent e) { recalcular.run(); }
            @Override
            public void changedUpdate(DocumentEvent e) { recalcular.run(); }
        };
        campoData.getDocument().addDocumentListener(ouvinte);
        campoCorrecao.getDocument().addDocumentListener(ouvinte);
        recalcular.run();

        javax.swing.JPanel painel = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 12, 6));
        String unidade = c.getUnidadeCondomino() == null || c.getUnidadeCondomino().getUnidade() == null
                ? "" : c.getUnidadeCondomino().getUnidade().getDescricao();
        painel.add(new javax.swing.JLabel("Unidade"));
        painel.add(new javax.swing.JLabel(unidade + String.format("  (%02d/%04d)", atual.getMesReferencia(), atual.getAnoReferencia())));
        painel.add(new javax.swing.JLabel("Valor emitido"));
        painel.add(new javax.swing.JLabel(moeda.format(atual.getValorEmitido())));
        painel.add(new javax.swing.JLabel("Vencimento"));
        painel.add(new javax.swing.JLabel(atual.getDataVencimento() == null ? "" : atual.getDataVencimento().format(DATA)));
        painel.add(new javax.swing.JLabel("Data de pagamento (dd/mm/aaaa)"));
        painel.add(campoData);
        painel.add(new javax.swing.JLabel("Dias de atraso"));
        painel.add(lblDias);
        painel.add(new javax.swing.JLabel("Multa (2%)"));
        painel.add(lblMulta);
        painel.add(new javax.swing.JLabel("Juros (1% a.m. pro rata)"));
        painel.add(lblJuros);
        painel.add(new javax.swing.JLabel("Correção (R$)"));
        painel.add(campoCorrecao);
        painel.add(new javax.swing.JLabel("Valor pago"));
        painel.add(lblPago);

        while (true) {
            int opcao = JOptionPane.showConfirmDialog(tela, painel, "Pagar condomínio",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (opcao != JOptionPane.OK_OPTION) return;
            if (calculo[0] != null) break;
            JOptionPane.showMessageDialog(tela, "Informe a data no formato dd/mm/aaaa e uma correção válida (zero ou mais).",
                    "Validação", JOptionPane.WARNING_MESSAGE);
        }
        CalculadoraCondominio.Pagamento pg = calculo[0];
        executarAcao(() -> service.pagar(atual.getId(), pg.dataPagamento(), pg.correcao()),
                "Pagamento registrado: " + moeda.format(pg.valorPago()) + ".");
    }

    private static BigDecimal lerValor(String texto) {
        String t = texto == null ? "" : texto.trim().replace("R$", "").trim();
        if (t.isEmpty()) return BigDecimal.ZERO;
        t = t.replace(".", "").replace(",", ".");
        return new BigDecimal(t);
    }

    private void processar() {
        Condominio c = exigirSelecionado();
        if (c == null) return;
        executarAcao(() -> service.processar(c.getId()), "Condomínio processado.");
    }

    private void protestar() {
        Condominio c = exigirSelecionado();
        if (c == null) return;
        int opcao = JOptionPane.showConfirmDialog(tela, "Marcar este condomínio como Protestado?",
                "Protestar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao != JOptionPane.YES_OPTION) return;
        executarAcao(() -> service.protestar(c.getId()), "Condomínio protestado.");
    }

    /** Executa uma ação de status/pagamento fora da EDT e recarrega a grade. */
    private void executarAcao(Runnable acao, String mensagemSucesso) {
        definirOcupado(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                acao.run();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
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
                definirOcupado(false);
                JOptionPane.showMessageDialog(tela, mensagemSucesso, "Gestão de Condomínios", JOptionPane.INFORMATION_MESSAGE);
                recarregar();
            }
        }.execute();
    }

    /** Recarrega a grade mantendo o condomínio selecionado. */
    private void recarregar() {
        if (referenciaGrade == null) return;
        Condominio anterior = selecionado();
        int idAnterior = anterior == null ? 0 : anterior.getId();
        int edificioId = edificioGrade;
        YearMonth ref = referenciaGrade;
        definirOcupado(true);
        new SwingWorker<List<Condominio>, Void>() {
            @Override
            protected List<Condominio> doInBackground() {
                return service.listar(edificioId, ref);
            }

            @Override
            protected void done() {
                try {
                    preencherTabela(get(), edificioId, ref);
                    selecionarPorId(idAnterior);
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

    private void selecionarPorId(int id) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == id) {
                int linha = tela.getjTableResultado().convertRowIndexToView(i);
                if (linha >= 0) tela.getjTableResultado().setRowSelectionInterval(linha, linha);
                return;
            }
        }
    }

    private void definirEdificio(Edificio edificio) {
        edificioSelecionado = edificio;
        Utils.definirVinculo(tela.getjComboBoxEdificio(), edificio == null ? 0 : edificio.getId(),
                edificio == null ? null : edificio.getNome());
    }

    private record Parametros(Edificio edificio, int mes, int ano, YearMonth referencia) {}

    private void atualizarCompetencia() {
        YearMonth referencia = null;
        try {
            int mes = Utils.inteiro(tela.getjTextFieldMes(), "mês", 1, 12);
            int ano = Utils.inteiro(tela.getjTextFieldAno(), "ano", 2000, 2100);
            referencia = GestaoCondominioService.referenciaDe(mes, ano);
        } catch (IllegalArgumentException ex) {
            // O período pode ficar incompleto durante a digitação.
        }
        if (referenciaGrade != null && !referenciaGrade.equals(referencia)) {
            preencherTabela(List.of(), edificioGrade, null);
        }
    }

    private Parametros lerParametros() {
        try {
            Edificio edificio = tela.getjComboBoxEdificio().getSelectedIndex() > 0 ? edificioSelecionado : null;
            if (edificio == null) throw new IllegalArgumentException("Busque e selecione o edifício.");
            int mes = Utils.inteiro(tela.getjTextFieldMes(), "mês", 1, 12);
            int ano = Utils.inteiro(tela.getjTextFieldAno(), "ano", 2000, 2100);
            YearMonth ref = GestaoCondominioService.referenciaDe(mes, ano);
            return new Parametros(edificio, mes, ano, ref);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
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
                    preencherTabela(get(), p.edificio().getId(), p.referencia());
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
        StringBuilder msg = new StringBuilder(String.format("Gerar %d %s de %02d/%04d, total %s?",
                previa.getCondominios().size(), previa.getCondominios().size() == 1 ? "condomínio" : "condomínios", ref.getMonthValue(), ref.getYear(), moeda.format(previa.getValorTotal())));
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
        int opcao = JOptionPane.showConfirmDialog(tela, mensagem, "Confirmar geração",
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
                JOptionPane.showMessageDialog(tela, String.format("%d %s para %02d/%04d.",
                        gravados, gravados == 1 ? "condomínio gerado" : "condomínios gerados", ref.getMonthValue(), ref.getYear()), "Geração",
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
                    preencherTabela(get(), previa.getEdificio().getId(), previa.getReferencia());
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
                "Condomínios gerados, mas não foi possível recarregar a grade. Use Pesquisar.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void tratarErro(Throwable causa) {
        if (causa instanceof IllegalArgumentException) {
            JOptionPane.showMessageDialog(tela, causa.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
        } else {
            mostrarErro(causa);
        }
    }

    private void preencherTabela(List<Condominio> registros, int edificioId, YearMonth referencia) {
        this.registros = List.copyOf(registros);
        this.edificioGrade = edificioId;
        this.referenciaGrade = referencia;
        atualizarGrade();
    }

    private void atualizarGrade() {
        int conta = tela.contaSelecionada();
        var tabela = tela.getjTableResultado();
        var colunas = tabela.getColumnModel();
        colunas.getColumn(tabela.convertColumnIndexToView(4))
                .setHeaderValue(conta == 0 ? "Valor Emitido" : tela.nomeContaSelecionada());
        colunas.getColumn(tabela.convertColumnIndexToView(5))
                .setHeaderValue(conta == 0 ? "Valor Pago" : "Pago (todas as contas)");
        tabela.getTableHeader().repaint();
        modelo.setRowCount(0);
        for (Condominio c : registros) {
            var uc = c.getUnidadeCondomino();
            String unidade = uc == null || uc.getUnidade() == null ? "" : uc.getUnidade().getDescricao();
            String prop = uc == null || uc.getProprietario() == null || uc.getProprietario().getNomeFantasia() == null
                    ? "" : uc.getProprietario().getNomeFantasia();
            String status = Condominio.descricaoStatus(c.getStatus());
            Double pago = Condominio.PAGO.equals(c.getStatus()) || Condominio.PROTESTADO.equals(c.getStatus())
                    ? c.getValorPago() : null;
            modelo.addRow(new Object[]{c.getMesReferencia(), c.getAnoReferencia(), unidade,
                c.getDataVencimento(), valorConta(c, conta), pago, prop, status});
        }
        aplicarFiltro();
    }

    private BigDecimal valorConta(Condominio condominio, int conta) {
        if (conta == 0) return BigDecimal.valueOf(condominio.getValorEmitido());
        Condominio.Detalhamento detalhe = condominio.getDetalhamento();
        if (detalhe != null) {
            return switch (conta) {
                case 1 -> detalhe.agua();
                case 2 -> detalhe.gas();
                case 3 -> detalhe.rateioUnidade();
                case 4 -> detalhe.rateioM2();
                default -> throw new IllegalArgumentException("Conta inválida.");
            };
        }
        String observacao = condominio.getObservacao();
        if (observacao == null) return null;
        var detalhamento = DETALHAMENTO.matcher(observacao);
        if (!detalhamento.matches()) return null;
        int grupo = switch (conta) {
            case 1 -> 3; // Água
            case 2 -> 4; // Gás
            case 3 -> 1; // Rateio por unidade
            case 4 -> 2; // Rateio por m²
            default -> throw new IllegalArgumentException("Conta inválida.");
        };
        return new BigDecimal(detalhamento.group(grupo));
    }

    private void aplicarFiltro() {
        List<RowFilter<DefaultTableModel, Integer>> filtros = new ArrayList<>();
        String txt = tela.getjTextFieldFiltroUnidade().getText().trim();
        if (!txt.isEmpty()) filtros.add(RowFilter.regexFilter("(?i)" + Pattern.quote(txt), 2));
        String status = String.valueOf(tela.getjComboBoxStatus().getSelectedItem());
        if (!"Todos".equals(status)) filtros.add(RowFilter.regexFilter("^" + Pattern.quote(status) + "$", 7));
        sorter.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
        atualizarResumo();
        atualizarAcoes();
    }

    private void atualizarResumo() {
        BigDecimal total = BigDecimal.ZERO;
        int semDetalhamento = 0;
        int linhas = sorter.getViewRowCount();
        for (int i = 0; i < linhas; i++) {
            BigDecimal valor = (BigDecimal) modelo.getValueAt(sorter.convertRowIndexToModel(i), 4);
            if (valor == null) {
                semDetalhamento++;
            } else {
                total = total.add(valor);
            }
        }
        String rotulo = tela.contaSelecionada() == 0 ? "Total emitido" : "Subtotal";
        tela.getjLabelResumoTotal().setText(semDetalhamento == 0 ? rotulo : "Subtotal parcial");
        tela.getjLabelResumoTotal().setToolTipText(semDetalhamento == 0 ? null
                : semDetalhamento + " registro(s) sem detalhamento da conta selecionada.");
        tela.getjLabelQuantidadeValor().setText(String.valueOf(linhas));
        NumberFormat moeda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        tela.getjLabelTotalValor().setText(linhas > 0 && semDetalhamento == linhas ? "Indisponível" : moeda.format(total));
    }

    private void limpar() {
        definirEdificio(null);
        YearMonth competencia = YearMonth.now().minusMonths(1);
        tela.getjTextFieldMes().setText(Integer.toString(competencia.getMonthValue()));
        tela.getjTextFieldAno().setText(Integer.toString(competencia.getYear()));
        tela.getjTextFieldFiltroUnidade().setText("");
        tela.getjComboBoxStatus().setSelectedIndex(0);
        registros = List.of();
        referenciaGrade = null;
        tela.selecionarTodasAsContas();
        atualizarGrade();
        atualizarCompetencia();
    }

    private void definirOcupado(boolean estado) {
        ocupado = estado;
        tela.setCursor(Cursor.getPredefinedCursor(estado ? Cursor.WAIT_CURSOR : Cursor.DEFAULT_CURSOR));
        tela.setDefaultCloseOperation(estado ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
                : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Utils.habilitarComponentes(tela.getjPanelbotoes(), !estado);
        Utils.habilitarComponentes(tela.getjPanelParametros(), !estado);
        atualizarAcoes();
    }

    private void mostrarErro(Throwable erro) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erro na gestao de Condominio", erro);
        JOptionPane.showMessageDialog(tela,
                "Não foi possível concluir a operação. Verifique a conexão com o banco e tente novamente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
