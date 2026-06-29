/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.cadastro;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author aluno
 */
public class TelaCadastroMovimentoCaixa extends javax.swing.JDialog {

    /** Creates new form TelaCadastroMovimentoCaixa */
    public TelaCadastroMovimentoCaixa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitulo = new javax.swing.JPanel();
        jLabeltitulo = new javax.swing.JLabel();
        jPanelDados = new javax.swing.JPanel();
        jLabelSecaoIdentificacao = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelEdificio = new javax.swing.JLabel();
        jComboBoxEdificio = new javax.swing.JComboBox<>();
        jLabelFornecedor = new javax.swing.JLabel();
        jComboBoxFornecedor = new javax.swing.JComboBox<>();
        jLabelCustoNivel1 = new javax.swing.JLabel();
        jComboBoxCustoNivel1 = new javax.swing.JComboBox<>();
        jLabelCustoNivel2 = new javax.swing.JLabel();
        jComboBoxCustoNivel2 = new javax.swing.JComboBox<>();
        jLabelSecaoDatas = new javax.swing.JLabel();
        jLabelDataEmissao = new javax.swing.JLabel();
        jTextFieldDataEmissao = new javax.swing.JFormattedTextField();
        jLabelDataVencimento = new javax.swing.JLabel();
        jTextFieldDataVencimento = new javax.swing.JFormattedTextField();
        jLabelDataPagamento = new javax.swing.JLabel();
        jTextFieldDataPagamento = new javax.swing.JFormattedTextField();
        jLabelTipo = new javax.swing.JLabel();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabelSecaoValores = new javax.swing.JLabel();
        jLabelValorEmitido = new javax.swing.JLabel();
        jTextFieldValorEmitido = new javax.swing.JFormattedTextField();
        jLabelValorPagoRec = new javax.swing.JLabel();
        jTextFieldValorPagoRec = new javax.swing.JFormattedTextField();
        jLabelMultas = new javax.swing.JLabel();
        jTextFieldMultas = new javax.swing.JFormattedTextField();
        jLabelCorrecaoMonetaria = new javax.swing.JLabel();
        jTextFieldCorrecaoMonetaria = new javax.swing.JFormattedTextField();
        jLabelJuros = new javax.swing.JLabel();
        jTextFieldJuros = new javax.swing.JFormattedTextField();
        jLabelFlagFormula = new javax.swing.JLabel();
        jTextFieldFlagFormula = new javax.swing.JTextField();
        jLabelFlagRateio = new javax.swing.JLabel();
        jCheckBoxFlagRateio = new javax.swing.JCheckBox();
        jLabelStatus = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabelSecaoObservacoes = new javax.swing.JLabel();
        jScrollPaneObservacao = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jPanelbotoes = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGravar = new javax.swing.JButton();
        jButtonSelecionarEdificio = new javax.swing.JButton();
        jButtonSelecionarFornecedor = new javax.swing.JButton();
        jButtonSelecionarCustoNivel1 = new javax.swing.JButton();
        jButtonSelecionarCustoNivel2 = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Movimento de Caixa");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(83, 0, 30));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabeltitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabeltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabeltitulo.setText("Cadastro de Movimento de Caixa");
        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanelTituloLayout.setVerticalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE));

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelSecaoIdentificacao.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoIdentificacao.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoIdentificacao.setText("Lancamento");

        jLabelSecaoDatas.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoDatas.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoDatas.setText("Datas");

        jLabelSecaoValores.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoValores.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoValores.setText("Valores");

        jLabelSecaoObservacoes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoObservacoes.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoObservacoes.setText("Observacoes");

        jLabelId.setText("ID");

        jTextFieldId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldId.setPreferredSize(new java.awt.Dimension(70, 28));

        jLabelEdificio.setText("Edificio");

        jComboBoxEdificio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxEdificio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxEdificio.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelFornecedor.setText("Fornecedor");

        jComboBoxFornecedor.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxFornecedor.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCustoNivel1.setText("Custo nivel 1");

        jComboBoxCustoNivel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxCustoNivel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxCustoNivel1.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCustoNivel2.setText("Custo nivel 2");

        jComboBoxCustoNivel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxCustoNivel2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxCustoNivel2.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataEmissao.setText("Data de emissao");

        try {
            jTextFieldDataEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldDataEmissao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataEmissao.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataVencimento.setText("Data de vencimento");

        try {
            jTextFieldDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldDataVencimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataVencimento.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataPagamento.setText("Data de pagamento");

        try {
            jTextFieldDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldDataPagamento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataPagamento.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelTipo.setText("Tipo");

        jComboBoxTipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Receita", "Despesa" }));
        jComboBoxTipo.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelValorEmitido.setText("Valor emitido");

        jTextFieldValorEmitido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldValorEmitido.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelValorPagoRec.setText("Valor pago/recebido");

        jTextFieldValorPagoRec.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldValorPagoRec.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelMultas.setText("Multas");

        jTextFieldMultas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMultas.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCorrecaoMonetaria.setText("Correcao monetaria");

        jTextFieldCorrecaoMonetaria.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCorrecaoMonetaria.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelJuros.setText("Juros");

        jTextFieldJuros.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldJuros.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelFlagFormula.setText("Formula");

        jTextFieldFlagFormula.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldFlagFormula.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelFlagRateio.setText("Rateio por unidade");

        jCheckBoxFlagRateio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jCheckBoxFlagRateio.setText("Sim");

        jLabelStatus.setText("Status");

        jComboBoxStatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        jComboBoxStatus.setPreferredSize(new java.awt.Dimension(170, 28));

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPaneObservacao.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelDadosLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSecaoIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, 70))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCustoNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCustoNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCustoNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCustoNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoDatas, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoValores, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelValorPagoRec, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorPagoRec, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCorrecaoMonetaria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCorrecaoMonetaria, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFlagFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFlagFormula, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelFlagRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxFlagRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addComponent(jScrollPaneObservacao)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanelDadosLayout.setVerticalGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelDadosLayout.createSequentialGroup().addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoIdentificacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEdificio)
                    .addComponent(jComboBoxEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFornecedor)
                    .addComponent(jComboBoxFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCustoNivel1)
                    .addComponent(jComboBoxCustoNivel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCustoNivel2)
                    .addComponent(jComboBoxCustoNivel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoDatas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataEmissao)
                    .addComponent(jTextFieldDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataVencimento)
                    .addComponent(jTextFieldDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataPagamento)
                    .addComponent(jTextFieldDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTipo)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoValores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorEmitido)
                    .addComponent(jTextFieldValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorPagoRec)
                    .addComponent(jTextFieldValorPagoRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMultas)
                    .addComponent(jTextFieldMultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCorrecaoMonetaria)
                    .addComponent(jTextFieldCorrecaoMonetaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelJuros)
                    .addComponent(jTextFieldJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFlagFormula)
                    .addComponent(jTextFieldFlagFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFlagRateio)
                    .addComponent(jCheckBoxFlagRateio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoObservacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanelbotoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Create.png"))); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonNovo.setPreferredSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonNovo);
        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Cancel.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonCancelar.setPreferredSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonCancelar);
        jButtonGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/OK.png"))); // NOI18N
        jButtonGravar.setText("Gravar");
        jButtonGravar.setEnabled(false);
        jButtonGravar.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonGravar.setPreferredSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonGravar);
        jButtonSelecionarEdificio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Find.png"))); // NOI18N
        jButtonSelecionarEdificio.setText("Selecionar Edificio");
        jButtonSelecionarEdificio.setMinimumSize(new java.awt.Dimension(150, 30));
        jButtonSelecionarEdificio.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanelbotoes.add(jButtonSelecionarEdificio);

        jButtonSelecionarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Find.png"))); // NOI18N
        jButtonSelecionarFornecedor.setText("Selecionar Fornecedor");
        jButtonSelecionarFornecedor.setMinimumSize(new java.awt.Dimension(150, 30));
        jButtonSelecionarFornecedor.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanelbotoes.add(jButtonSelecionarFornecedor);

        jButtonSelecionarCustoNivel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Find.png"))); // NOI18N
        jButtonSelecionarCustoNivel1.setText("Selecionar Nivel 1");
        jButtonSelecionarCustoNivel1.setMinimumSize(new java.awt.Dimension(150, 30));
        jButtonSelecionarCustoNivel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanelbotoes.add(jButtonSelecionarCustoNivel1);

        jButtonSelecionarCustoNivel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Find.png"))); // NOI18N
        jButtonSelecionarCustoNivel2.setText("Selecionar Nivel 2");
        jButtonSelecionarCustoNivel2.setMinimumSize(new java.awt.Dimension(150, 30));
        jButtonSelecionarCustoNivel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanelbotoes.add(jButtonSelecionarCustoNivel2);

        jButtonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Find.png"))); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonBuscar.setPreferredSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonBuscar);
        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Exit.png"))); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.setMaximumSize(new java.awt.Dimension(112, 30));
        jButtonSair.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonSair.setPreferredSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonSair);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanelbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanelbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new TelaCadastroMovimentoCaixa(new javax.swing.JFrame(), true).setVisible(true); } }); }

    public JButton getjButtonBuscar() { return jButtonBuscar; }
    public JButton getjButtonCancelar() { return jButtonCancelar; }
    public JButton getjButtonGravar() { return jButtonGravar; }
    public JButton getjButtonNovo() { return jButtonNovo; }
    public JButton getjButtonSelecionarEdificio() { return jButtonSelecionarEdificio; }
    public JButton getjButtonSelecionarFornecedor() { return jButtonSelecionarFornecedor; }
    public JButton getjButtonSelecionarCustoNivel1() { return jButtonSelecionarCustoNivel1; }
    public JButton getjButtonSelecionarCustoNivel2() { return jButtonSelecionarCustoNivel2; }
    public JButton getjButtonSair() { return jButtonSair; }
    public JPanel getjPanelDados() { return jPanelDados; }
    public JPanel getjPanelbotoes() { return jPanelbotoes; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelecionarEdificio;
    private javax.swing.JButton jButtonSelecionarFornecedor;
    private javax.swing.JButton jButtonSelecionarCustoNivel1;
    private javax.swing.JButton jButtonSelecionarCustoNivel2;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxEdificio;
    private javax.swing.JComboBox<String> jComboBoxFornecedor;
    private javax.swing.JComboBox<String> jComboBoxCustoNivel1;
    private javax.swing.JComboBox<String> jComboBoxCustoNivel2;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JCheckBox jCheckBoxFlagRateio;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabelSecaoIdentificacao;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelEdificio;
    private javax.swing.JLabel jLabelFornecedor;
    private javax.swing.JLabel jLabelCustoNivel1;
    private javax.swing.JLabel jLabelCustoNivel2;
    private javax.swing.JLabel jLabelSecaoDatas;
    private javax.swing.JLabel jLabelDataEmissao;
    private javax.swing.JLabel jLabelDataVencimento;
    private javax.swing.JLabel jLabelDataPagamento;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelSecaoValores;
    private javax.swing.JLabel jLabelValorEmitido;
    private javax.swing.JLabel jLabelValorPagoRec;
    private javax.swing.JLabel jLabelMultas;
    private javax.swing.JLabel jLabelCorrecaoMonetaria;
    private javax.swing.JLabel jLabelJuros;
    private javax.swing.JLabel jLabelFlagFormula;
    private javax.swing.JLabel jLabelFlagRateio;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSecaoObservacoes;
    private javax.swing.JLabel jLabeltitulo;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelbotoes;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JFormattedTextField jTextFieldDataEmissao;
    private javax.swing.JFormattedTextField jTextFieldDataVencimento;
    private javax.swing.JFormattedTextField jTextFieldDataPagamento;
    private javax.swing.JFormattedTextField jTextFieldValorEmitido;
    private javax.swing.JFormattedTextField jTextFieldValorPagoRec;
    private javax.swing.JFormattedTextField jTextFieldMultas;
    private javax.swing.JFormattedTextField jTextFieldCorrecaoMonetaria;
    private javax.swing.JFormattedTextField jTextFieldJuros;
    private javax.swing.JTextField jTextFieldFlagFormula;
    // End of variables declaration//GEN-END:variables
}
