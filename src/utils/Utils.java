package utils;

import java.awt.Component;
import java.awt.Container;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.JTextComponent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeParseException;


public class Utils {
    private static final DateTimeFormatter DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    public static String texto(JTextComponent campo, String nome, int limite, boolean obrigatorio) {
        String valor = campo.getText().trim();
        if (obrigatorio && valor.isEmpty()) throw new IllegalArgumentException("Informe " + nome + ".");
        if (valor.length() > limite) throw new IllegalArgumentException(nome + ": limite de " + limite + " caracteres.");
        return valor.isEmpty() ? null : valor;
    }

    public static String documento(JTextComponent campo, String nome, int minimo, int maximo, boolean obrigatorio) {
        String valor = campo.getText().replaceAll("[^0-9]", "");
        if (valor.isEmpty() && !obrigatorio) return null;
        if (valor.length() < minimo || valor.length() > maximo) {
            throw new IllegalArgumentException("Informe " + nome + " completo.");
        }
        return valor;
    }

    public static int inteiro(JTextComponent campo, String nome, int minimo, int maximo) {
        try {
            int valor = Integer.parseInt(campo.getText().trim());
            if (valor < minimo || valor > maximo) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(nome + " deve ser um inteiro entre " + minimo + " e " + maximo + ".");
        }
    }

    public static double numero(JTextComponent campo, String nome, boolean obrigatorio) {
        String texto = campo.getText().trim();
        if (texto.isEmpty() && !obrigatorio) return 0;
        try {
            if (texto.contains(",")) {
                if (!texto.matches("[0-9]+(,[0-9]+)?|[0-9]{1,3}(\\.[0-9]{3})+(,[0-9]+)?")) {
                    throw new NumberFormatException();
                }
                texto = texto.replace(".", "").replace(',', '.');
            }
            double valor = Double.parseDouble(texto);
            if (!Double.isFinite(valor) || valor < 0) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Informe um numero valido, maior ou igual a zero, para " + nome + ".");
        }
    }

    public static LocalDate data(JTextComponent campo, String nome, boolean obrigatorio) {
        String texto = campo.getText().trim();
        if (!texto.matches(".*[0-9].*") && !obrigatorio) return null;
        try {
            return LocalDate.parse(texto, DATA);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Informe " + nome + " no formato dd/mm/aaaa, com uma data valida.");
        }
    }

    public static LocalDateTime dataHora(JTextComponent campo, String nome) {
        try {
            return LocalDateTime.parse(campo.getText().trim(), DATA_HORA);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Informe " + nome + " no formato dd/mm/aaaa hh:mm.");
        }
    }

    public static String escolha(JComboBox<String> campo, String nome, boolean obrigatorio) {
        if (campo.getSelectedIndex() <= 0) {
            if (obrigatorio) throw new IllegalArgumentException("Selecione " + nome + ".");
            return null;
        }
        return String.valueOf(campo.getSelectedItem());
    }

    public static String status(JComboBox<String> campo) {
        if (campo.getSelectedIndex() < 0) throw new IllegalArgumentException("Selecione o status.");
        return campo.getSelectedIndex() == 0 ? "A" : "I";
    }

    public static void selecionarItem(JComboBox<String> campo, String valor) {
        if (valor == null || valor.isEmpty()) { campo.setSelectedIndex(0); return; }
        boolean encontrado = false;
        for (int i = 0; i < campo.getItemCount(); i++) encontrado |= valor.equals(campo.getItemAt(i));
        if (!encontrado) campo.addItem(valor);
        campo.setSelectedItem(valor);
    }

    public static void definirVinculo(JComboBox<String> campo, int id, String descricao) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>(new String[]{"Selecione"});
        if (id > 0) modelo.addElement(id + " - " + (descricao == null ? "" : descricao));
        campo.setModel(modelo);
        campo.setSelectedIndex(id > 0 ? 1 : 0);
    }

    public static String formatar(Object valor) {
        if (valor == null) return "";
        if (valor instanceof LocalDate) return ((LocalDate) valor).format(DATA);
        if (valor instanceof LocalDateTime) return ((LocalDateTime) valor).format(DATA_HORA);
        if (valor instanceof Double) return java.math.BigDecimal.valueOf((Double) valor)
                .stripTrailingZeros().toPlainString().replace('.', ',');
        return valor.toString();
    }

    public static void preencherCampo(JTextComponent campo, Object valor) {
        String texto = formatar(valor);
        if (campo instanceof JFormattedTextField && texto.matches("[0-9]+")) {
            JFormattedTextField formatado = (JFormattedTextField) campo;
            if (formatado.getFormatter() instanceof javax.swing.text.MaskFormatter) {
                String mascara = ((javax.swing.text.MaskFormatter) formatado.getFormatter()).getMask();
                StringBuilder preenchido = new StringBuilder();
                int indice = 0;
                for (char caractere : mascara.toCharArray()) {
                    if (caractere == '#') preenchido.append(indice < texto.length() ? texto.charAt(indice++) : ' ');
                    else preenchido.append(caractere);
                }
                texto = preenchido.toString();
            }
        }
        campo.setText(texto);
    }

    public static void habilitarComponentes(Container painel, boolean estado) {
        for (Component componente : painel.getComponents()) {
            if (componente instanceof JTextComponent || componente instanceof JComboBox
                    || componente instanceof javax.swing.AbstractButton) componente.setEnabled(estado);
            else if (componente instanceof Container) habilitarComponentes((Container) componente, estado);
        }
    }
    
    public static void ativaDesativaBtn (JPanel painelBtn, boolean estadoBtn) {
        Component[] components = painelBtn.getComponents();
        
        for(Component component : components) {
            if (component instanceof JButton) {
                JButton botao = (JButton) component;
                String actionCommand = botao.getActionCommand();
                String texto = botao.getText();
                boolean botaoDeEdicao = "0".equals(actionCommand)
                        || "Cancelar".equalsIgnoreCase(texto)
                        || "Gravar".equalsIgnoreCase(texto);
                
                if (botaoDeEdicao) {
                    component.setEnabled(estadoBtn);
                } else {
                    component.setEnabled(!estadoBtn);
                }
            }
        }
    }
    
    public static void LimpaComponentes(JPanel painel, boolean estadoComponentes, ButtonGroup grupoRadio) {
        if(grupoRadio != null){
            grupoRadio.clearSelection();
        }
        
        limpaComponentes(painel, estadoComponentes);
    }
    
    private static void limpaComponentes(Container painel, boolean estadoComponentes) {
        Component[] listaComponentes = painel.getComponents();
        
        for (Component componenteAtual : listaComponentes) {
            if (componenteAtual instanceof JFormattedTextField) {
                ((JFormattedTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            } else if (componenteAtual instanceof JTextField) {
                ((JTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            } else if (componenteAtual instanceof JComboBox){
                JComboBox comboBox = (JComboBox) componenteAtual;
                
                comboBox.setSelectedIndex(comboBox.getItemCount() > 0 ? 0 : -1);
                componenteAtual.setEnabled(estadoComponentes);
            }else if(componenteAtual instanceof JCheckBox){
                componenteAtual.setEnabled(estadoComponentes);
                ((JCheckBox) componenteAtual).setSelected(false);
            }else if(componenteAtual instanceof JRadioButton){
               componenteAtual.setEnabled(estadoComponentes);
               ((JRadioButton) componenteAtual).setSelected(false);
            }else if (componenteAtual instanceof JTextArea){
                ((JTextArea) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            }else if (componenteAtual instanceof JButton){
                componenteAtual.setEnabled(estadoComponentes);
            }else if (componenteAtual instanceof Container){
                limpaComponentes((Container) componenteAtual, estadoComponentes);
            }
        }
    }
    
}
