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
public class TelaCadastroSindicoProfissional extends javax.swing.JDialog {

    /** Creates new form TelaCadastroSindicoProfissional */
    public TelaCadastroSindicoProfissional(java.awt.Frame parent, boolean modal) {
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
        jLabelNomeFantasia = new javax.swing.JLabel();
        jTextFieldNomeFantasia = new javax.swing.JTextField();
        jLabelRazaoSocial = new javax.swing.JLabel();
        jTextFieldRazaoSocial = new javax.swing.JTextField();
        jLabelCra = new javax.swing.JLabel();
        jTextFieldCra = new javax.swing.JTextField();
        jLabelSecaoDocumentos = new javax.swing.JLabel();
        jLabelCpf = new javax.swing.JLabel();
        jTextFieldCpf = new javax.swing.JFormattedTextField();
        jLabelRg = new javax.swing.JLabel();
        jTextFieldRg = new javax.swing.JTextField();
        jLabelCnpj = new javax.swing.JLabel();
        jTextFieldCnpj = new javax.swing.JFormattedTextField();
        jLabelInscricaoEstadual = new javax.swing.JLabel();
        jTextFieldInscricaoEstadual = new javax.swing.JTextField();
        jLabelSecaoContato = new javax.swing.JLabel();
        jLabelFone1 = new javax.swing.JLabel();
        jTextFieldFone1 = new javax.swing.JFormattedTextField();
        jLabelFone2 = new javax.swing.JLabel();
        jTextFieldFone2 = new javax.swing.JFormattedTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelSecaoDadosComplementares = new javax.swing.JLabel();
        jLabelDataNascimento = new javax.swing.JLabel();
        jTextFieldDataNascimento = new javax.swing.JFormattedTextField();
        jLabelDataCadastro = new javax.swing.JLabel();
        jTextFieldDataCadastro = new javax.swing.JFormattedTextField();
        jLabelEstadoCivil = new javax.swing.JLabel();
        jComboBoxEstadoCivil = new javax.swing.JComboBox<>();
        jLabelSecaoEndereco = new javax.swing.JLabel();
        jLabelCep = new javax.swing.JLabel();
        jTextFieldCep = new javax.swing.JFormattedTextField();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabelLogradouro = new javax.swing.JLabel();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabelComplemento = new javax.swing.JLabel();
        jTextFieldComplemento = new javax.swing.JTextField();
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
        setTitle("Cadastro de Sindico Profissional");
        setResizable(false);

        jPanelTitulo.setBackground(new java.awt.Color(83, 0, 30));
        jPanelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabeltitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabeltitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabeltitulo.setText("Cadastro de Sindico Profissional");

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabeltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );

        jPanelDados.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelSecaoIdentificacao.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoIdentificacao.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoIdentificacao.setText("Identificacao");

        jLabelId.setText("ID");

        jTextFieldId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldId.setPreferredSize(new java.awt.Dimension(70, 28));

        jLabelNomeFantasia.setText("Nome Fantasia");

        jTextFieldNomeFantasia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldNomeFantasia.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelRazaoSocial.setText("Razao Social");

        jTextFieldRazaoSocial.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldRazaoSocial.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCra.setText("CRA");

        jTextFieldCra.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCra.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelSecaoDocumentos.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoDocumentos.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoDocumentos.setText("Documentos");

        jLabelCpf.setText("CPF");

        try {
            jTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldCpf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCpf.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelRg.setText("RG");

        jTextFieldRg.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldRg.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCnpj.setText("CNPJ");

        try {
            jTextFieldCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldCnpj.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCnpj.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelInscricaoEstadual.setText("Inscricao Estadual");

        jTextFieldInscricaoEstadual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldInscricaoEstadual.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelSecaoContato.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoContato.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoContato.setText("Contato");

        jLabelFone1.setText("Fone 1");

        try {
            jTextFieldFone1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldFone1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldFone1.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelFone2.setText("Fone 2");

        try {
            jTextFieldFone2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldFone2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldFone2.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelEmail.setText("Email");

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldEmail.setPreferredSize(new java.awt.Dimension(520, 28));

        jLabelSecaoDadosComplementares.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoDadosComplementares.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoDadosComplementares.setText("Dados complementares");

        jLabelDataNascimento.setText("Data Nascimento");

        try {
            jTextFieldDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldDataNascimento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataNascimento.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelDataCadastro.setText("Data Cadastro");

        try {
            jTextFieldDataCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldDataCadastro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldDataCadastro.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelEstadoCivil.setText("Estado Civil");

        jComboBoxEstadoCivil.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBoxEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        jComboBoxEstadoCivil.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelSecaoEndereco.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoEndereco.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoEndereco.setText("Endereco");

        jLabelCep.setText("CEP");

        try {
            jTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldCep.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCep.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelCidade.setText("Cidade");

        jTextFieldCidade.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldCidade.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelLogradouro.setText("Logradouro");

        jTextFieldLogradouro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldLogradouro.setPreferredSize(new java.awt.Dimension(520, 28));

        jLabelBairro.setText("Bairro");

        jTextFieldBairro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldBairro.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelComplemento.setText("Complemento");

        jTextFieldComplemento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldComplemento.setPreferredSize(new java.awt.Dimension(170, 28));

        jLabelSecaoObservacoes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelSecaoObservacoes.setForeground(new java.awt.Color(83, 0, 30));
        jLabelSecaoObservacoes.setText("Observacoes");

        jTextAreaObservacao.setColumns(20);
        jTextAreaObservacao.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextAreaObservacao.setLineWrap(true);
        jTextAreaObservacao.setRows(5);
        jTextAreaObservacao.setWrapStyleWord(true);
        jScrollPaneObservacao.setViewportView(jTextAreaObservacao);

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSecaoIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, 70))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCra, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelInscricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldInscricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoContato, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFone1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFone2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520))
                    .addComponent(jLabelSecaoDadosComplementares, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCep, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, 150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, 170))
                    .addComponent(jLabelSecaoObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 520, 520)
                    .addComponent(jScrollPaneObservacao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSecaoIdentificacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeFantasia)
                    .addComponent(jTextFieldNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRazaoSocial)
                    .addComponent(jTextFieldRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCra)
                    .addComponent(jTextFieldCra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoDocumentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpf)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRg)
                    .addComponent(jTextFieldRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCnpj)
                    .addComponent(jTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInscricaoEstadual)
                    .addComponent(jTextFieldInscricaoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoContato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFone1)
                    .addComponent(jTextFieldFone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFone2)
                    .addComponent(jTextFieldFone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoDadosComplementares)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataNascimento)
                    .addComponent(jTextFieldDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataCadastro)
                    .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstadoCivil)
                    .addComponent(jComboBoxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCep)
                    .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCidade)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLogradouro)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBairro)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelComplemento)
                    .addComponent(jTextFieldComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSecaoObservacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jButtonSair.setMinimumSize(new java.awt.Dimension(112, 30));
        jButtonSair.setPreferredSize(new java.awt.Dimension(112, 30));
        jButtonSair.setMaximumSize(new java.awt.Dimension(112, 30));
        jPanelbotoes.add(jButtonSair);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelbotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelbotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new TelaCadastroSindicoProfissional(new javax.swing.JFrame(), true).setVisible(true); } }); }

    public JButton getjButtonBuscar() { return jButtonBuscar; }
    public JButton getjButtonCancelar() { return jButtonCancelar; }
    public JButton getjButtonGravar() { return jButtonGravar; }
    public JButton getjButtonNovo() { return jButtonNovo; }
    public JButton getjButtonSair() { return jButtonSair; }
    public JPanel getjPanelDados() { return jPanelDados; }
    public JPanel getjPanelbotoes() { return jPanelbotoes; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<String> jComboBoxEstadoCivil;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCep;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelCnpj;
    private javax.swing.JLabel jLabelComplemento;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelCra;
    private javax.swing.JLabel jLabelDataCadastro;
    private javax.swing.JLabel jLabelDataNascimento;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEstadoCivil;
    private javax.swing.JLabel jLabelFone1;
    private javax.swing.JLabel jLabelFone2;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelInscricaoEstadual;
    private javax.swing.JLabel jLabelLogradouro;
    private javax.swing.JLabel jLabelNomeFantasia;
    private javax.swing.JLabel jLabelRazaoSocial;
    private javax.swing.JLabel jLabelRg;
    private javax.swing.JLabel jLabelSecaoContato;
    private javax.swing.JLabel jLabelSecaoDadosComplementares;
    private javax.swing.JLabel jLabelSecaoDocumentos;
    private javax.swing.JLabel jLabelSecaoEndereco;
    private javax.swing.JLabel jLabelSecaoIdentificacao;
    private javax.swing.JLabel jLabelSecaoObservacoes;
    private javax.swing.JLabel jLabeltitulo;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelbotoes;
    private javax.swing.JScrollPane jScrollPaneObservacao;
    private javax.swing.JTextArea jTextAreaObservacao;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JFormattedTextField jTextFieldCep;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JFormattedTextField jTextFieldCnpj;
    private javax.swing.JTextField jTextFieldComplemento;
    private javax.swing.JFormattedTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldCra;
    private javax.swing.JFormattedTextField jTextFieldDataCadastro;
    private javax.swing.JFormattedTextField jTextFieldDataNascimento;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JFormattedTextField jTextFieldFone1;
    private javax.swing.JFormattedTextField jTextFieldFone2;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldInscricaoEstadual;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNomeFantasia;
    private javax.swing.JTextField jTextFieldRazaoSocial;
    private javax.swing.JTextField jTextFieldRg;
    // End of variables declaration//GEN-END:variables
}
