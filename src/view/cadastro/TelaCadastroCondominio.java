/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.cadastro;

/**
 *
 * @author aluno
 */
public class TelaCadastroCondominio extends javax.swing.JDialog {

    /** Creates new form TelaCadastroCondominio */
    public TelaCadastroCondominio(java.awt.Frame parent, boolean modal) {
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
        jLabelUnidadeCondomino = new javax.swing.JLabel();
        jComboBoxUnidadeCondomino = new javax.swing.JComboBox<>();
        jLabelSecaoReferencia = new javax.swing.JLabel();
        jLabelMesReferencia = new javax.swing.JLabel();
        jTextFieldMesReferencia = new javax.swing.JFormattedTextField();
        jLabelAnoReferencia = new javax.swing.JLabel();
        jTextFieldAnoReferencia = new javax.swing.JFormattedTextField();
        jLabelDataEmissao = new javax.swing.JLabel();
        jTextFieldDataEmissao = new javax.swing.JFormattedTextField();
        jLabelDataVencimento = new javax.swing.JLabel();
        jTextFieldDataVencimento = new javax.swing.JFormattedTextField();
        jLabelSecaoValores = new javax.swing.JLabel();
        jLabelValorEmitido = new javax.swing.JLabel();
        jTextFieldValorEmitido = new javax.swing.JFormattedTextField();
        jLabelValorPago = new javax.swing.JLabel();
        jTextFieldValorPago = new javax.swing.JFormattedTextField();
        jLabelJuros = new javax.swing.JLabel();
        jTextFieldJuros = new javax.swing.JFormattedTextField();
        jLabelMultas = new javax.swing.JLabel();
        jTextFieldMultas = new javax.swing.JFormattedTextField();
        jLabelCorrecao = new javax.swing.JLabel();
        jTextFieldCorrecao = new javax.swing.JFormattedTextField();
        jLabelStatus = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabelSecaoObservacoes = new javax.swing.JLabel();
        jScrollPaneObservacao = new javax.swing.JScrollPane();
        jTextAreaObservacao = new javax.swing.JTextArea();
        jPanelbotoes = new javax.swing.JPanel();
        jButtonNovo = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGravar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Condominio");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(83, 0, 30));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabeltitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabeltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabeltitulo.setText("Cadastro de Condominio");

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanelTituloLayout.setVerticalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE));

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelSecaoIdentificacao.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoIdentificacao.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoIdentificacao.setText("Identificacao");

        jLabelSecaoReferencia.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoReferencia.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoReferencia.setText("Referencia");

        jLabelSecaoValores.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoValores.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoValores.setText("Valores");

        jLabelSecaoObservacoes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoObservacoes.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoObservacoes.setText("Observacoes");

        jLabelId.setText("ID");

        jTextFieldId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldId.setPreferredSize(new java.awt.Dimension(70, 28));

        jLabelUnidadeCondomino.setText("Unidade Condomino");

        jComboBoxUnidadeCondomino.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxUnidadeCondomino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxUnidadeCondomino.setPreferredSize(new java.awt.Dimension(520, 28));

        jLabelMesReferencia.setText("Mes Referencia");

        jTextFieldMesReferencia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMesReferencia.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelAnoReferencia.setText("Ano Referencia");

        jTextFieldAnoReferencia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldAnoReferencia.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataEmissao.setText("Data Emissao");

        jTextFieldDataEmissao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataEmissao.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataVencimento.setText("Data Vencimento");

        jTextFieldDataVencimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataVencimento.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelValorEmitido.setText("Valor Emitido");

        jTextFieldValorEmitido.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldValorEmitido.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelValorPago.setText("Valor Pago");

        jTextFieldValorPago.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldValorPago.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelJuros.setText("Juros");

        jTextFieldJuros.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldJuros.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelMultas.setText("Multas");

        jTextFieldMultas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldMultas.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCorrecao.setText("Correcao");

        jTextFieldCorrecao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCorrecao.setPreferredSize(new java.awt.Dimension(170, 28));

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
                        .addComponent(jLabelUnidadeCondomino, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxUnidadeCondomino, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520))
                    .addComponent(jLabelSecaoReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelMesReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMesReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelAnoReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAnoReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoValores, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJuros, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
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
                    .addComponent(jLabelUnidadeCondomino)
                    .addComponent(jComboBoxUnidadeCondomino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoReferencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMesReferencia)
                    .addComponent(jTextFieldMesReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAnoReferencia)
                    .addComponent(jTextFieldAnoReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataEmissao)
                    .addComponent(jTextFieldDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataVencimento)
                    .addComponent(jTextFieldDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoValores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelValorEmitido)
                    .addComponent(jTextFieldValorEmitido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorPago)
                    .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelJuros)
                    .addComponent(jTextFieldJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMultas)
                    .addComponent(jTextFieldMultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorrecao)
                    .addComponent(jTextFieldCorrecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new TelaCadastroCondominio(new javax.swing.JFrame(), true).setVisible(true); } });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxUnidadeCondomino;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabelSecaoIdentificacao;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelUnidadeCondomino;
    private javax.swing.JLabel jLabelSecaoReferencia;
    private javax.swing.JLabel jLabelMesReferencia;
    private javax.swing.JLabel jLabelAnoReferencia;
    private javax.swing.JLabel jLabelDataEmissao;
    private javax.swing.JLabel jLabelDataVencimento;
    private javax.swing.JLabel jLabelSecaoValores;
    private javax.swing.JLabel jLabelValorEmitido;
    private javax.swing.JLabel jLabelValorPago;
    private javax.swing.JLabel jLabelJuros;
    private javax.swing.JLabel jLabelMultas;
    private javax.swing.JLabel jLabelCorrecao;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSecaoObservacoes;
    private javax.swing.JLabel jLabeltitulo;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelbotoes;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JFormattedTextField jTextFieldMesReferencia;
    private javax.swing.JFormattedTextField jTextFieldAnoReferencia;
    private javax.swing.JFormattedTextField jTextFieldDataEmissao;
    private javax.swing.JFormattedTextField jTextFieldDataVencimento;
    private javax.swing.JFormattedTextField jTextFieldValorEmitido;
    private javax.swing.JFormattedTextField jTextFieldValorPago;
    private javax.swing.JFormattedTextField jTextFieldJuros;
    private javax.swing.JFormattedTextField jTextFieldMultas;
    private javax.swing.JFormattedTextField jTextFieldCorrecao;
    // End of variables declaration//GEN-END:variables
}
