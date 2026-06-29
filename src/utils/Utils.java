package utils;

import java.awt.Component;
import java.awt.Container;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;


public class Utils {

    public static void aplicarMascara(JFormattedTextField campo, String mascara) {
        try {
            MaskFormatter fmt = new MaskFormatter(mascara);
            fmt.setPlaceholderCharacter('_');
            fmt.setAllowsInvalid(true);
            campo.setFormatterFactory(new DefaultFormatterFactory(fmt));
            // sem PERSIST, o campo reverte ao valor anterior ao perder o foco
            campo.setFocusLostBehavior(JFormattedTextField.PERSIST);
        } catch (ParseException e) {
            // mascara invalida, ignora
        }
    }

    /**
     * Aplica mascara de RG (##.###.###-#) a um JTextField comum.
     * Aceita ate 9 digitos; o ultimo pode ser X/x.
     */
    public static void aplicarMascaraRg(JTextField campo) {
        ((javax.swing.text.AbstractDocument) campo.getDocument())
                .setDocumentFilter(new javax.swing.text.DocumentFilter() {

            @Override
            public void insertString(FilterBypass fb, int offset, String text,
                    javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                String atual = fb.getDocument().getText(0, fb.getDocument().getLength());
                processar(fb, atual.substring(0, offset) + text + atual.substring(offset));
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text,
                    javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
                String atual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String t = (text != null) ? text : "";
                processar(fb, atual.substring(0, offset) + t + atual.substring(offset + length));
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length)
                    throws javax.swing.text.BadLocationException {
                String atual = fb.getDocument().getText(0, fb.getDocument().getLength());
                processar(fb, atual.substring(0, offset) + atual.substring(offset + length));
            }

            private void processar(FilterBypass fb, String txt)
                    throws javax.swing.text.BadLocationException {
                String d = txt.replaceAll("[^0-9Xx]", "");
                if (d.length() > 9) d = d.substring(0, 9);
                fb.remove(0, fb.getDocument().getLength());
                fb.insertString(0, formatar(d), null);
            }

            private String formatar(String d) {
                int n = d.length();
                if (n == 0) return "";
                if (n <= 2) return d;
                if (n <= 5) return d.substring(0, 2) + "." + d.substring(2);
                if (n <= 8) return d.substring(0, 2) + "." + d.substring(2, 5) + "." + d.substring(5);
                return d.substring(0, 2) + "." + d.substring(2, 5) + "."
                        + d.substring(5, 8) + "-" + d.substring(8).toUpperCase();
            }
        });
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
                ((JComboBox) componenteAtual).setSelectedIndex(-1);
                componenteAtual.setEnabled(estadoComponentes);
            }else if(componenteAtual instanceof JRadioButton){
               componenteAtual.setEnabled(estadoComponentes);
               ((JRadioButton) componenteAtual).setSelected(false);
            }else if (componenteAtual instanceof JTextArea){
                ((JTextArea) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            }else if (componenteAtual instanceof Container){
                limpaComponentes((Container) componenteAtual, estadoComponentes);
            }
        }
    }
    
}
