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


public class Utils {
    
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
