/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.cadastro;

/**
 *
 * @author aluno
 */
public class TelaCadastroFuncaoMandato extends javax.swing.JDialog {

    /** Creates new form TelaCadastroFuncaoMandato */
    public TelaCadastroFuncaoMandato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitulo = new javax.swing.JPanel();
        jLabeltitulo = new javax.swing.JLabel();
        jPanelDados = new javax.swing.JPanel();
        jLabelSecaoMandato = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabelFuncao = new javax.swing.JLabel();
        jComboBoxFuncao = new javax.swing.JComboBox<>();
        jLabelStatus = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabelSindicoProfissional = new javax.swing.JLabel();
        jComboBoxSindicoProfissional = new javax.swing.JComboBox<>();
        jLabelEdificio = new javax.swing.JLabel();
        jComboBoxEdificio = new javax.swing.JComboBox<>();
        jLabelDataInicio = new javax.swing.JLabel();
        jTextFieldDataInicio = new javax.swing.JFormattedTextField();
        jLabelDataFim = new javax.swing.JLabel();
        jTextFieldDataFim = new javax.swing.JFormattedTextField();
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
        setTitle("Cadastro de Funcao Mandato");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(83, 0, 30));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabeltitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabeltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabeltitulo.setText("Cadastro de Funcao Mandato");
        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanelTituloLayout.setVerticalGroup(jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE));

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelSecaoMandato.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoMandato.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoMandato.setText("Mandato");

        jLabelSecaoObservacoes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoObservacoes.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoObservacoes.setText("Observacoes");

        jLabelId.setText("ID");

        jTextFieldId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldId.setPreferredSize(new java.awt.Dimension(70, 28));

        jLabelFuncao.setText("Funcao");

        jComboBoxFuncao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Sindico", "Sub sindico", "Conselheiro" }));
        jComboBoxFuncao.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelStatus.setText("Status");

        jComboBoxStatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        jComboBoxStatus.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelSindicoProfissional.setText("Sindico Profissional");

        jComboBoxSindicoProfissional.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxSindicoProfissional.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxSindicoProfissional.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelEdificio.setText("Edificio");

        jComboBoxEdificio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxEdificio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxEdificio.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataInicio.setText("Data Inicio");

        jTextFieldDataInicio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataInicio.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataFim.setText("Data Fim");

        jTextFieldDataFim.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataFim.setPreferredSize(new java.awt.Dimension(170, 28));

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPaneObservacao.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelDadosLayout.createSequentialGroup().addContainerGap().addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSecaoMandato, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, 70))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelSindicoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSindicoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addComponent(jScrollPaneObservacao)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanelDadosLayout.setVerticalGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelDadosLayout.createSequentialGroup().addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoMandato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFuncao)
                    .addComponent(jComboBoxFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSindicoProfissional)
                    .addComponent(jComboBoxSindicoProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEdificio)
                    .addComponent(jComboBoxEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataInicio)
                    .addComponent(jTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataFim)
                    .addComponent(jTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new TelaCadastroFuncaoMandato(new javax.swing.JFrame(), true).setVisible(true); } }); }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxFuncao;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JComboBox<String> jComboBoxSindicoProfissional;
    private javax.swing.JComboBox<String> jComboBoxEdificio;
    private javax.swing.JLabel jLabelSecaoMandato;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelFuncao;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSindicoProfissional;
    private javax.swing.JLabel jLabelEdificio;
    private javax.swing.JLabel jLabelDataInicio;
    private javax.swing.JLabel jLabelDataFim;
    private javax.swing.JLabel jLabelSecaoObservacoes;
    private javax.swing.JLabel jLabeltitulo;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelbotoes;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JFormattedTextField jTextFieldDataInicio;
    private javax.swing.JFormattedTextField jTextFieldDataFim;
    // End of variables declaration//GEN-END:variables
}
