package utils;

import java.awt.Component;
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
                if (((JButton) component).getActionCommand().equals("0")) {
                    component.setEnabled(estadoBtn);
                } else {
                    component.setEnabled(!estadoBtn);
                }
            }
        }
    }
    
    public static void LimpaComponentes(JPanel painel, boolean estadoComponentes, ButtonGroup grupoRadio) {
        Component[] listaComponentes = painel.getComponents();
        
        if(grupoRadio != null){
            grupoRadio.clearSelection();
        }
        for (Component componenteAtual : listaComponentes) {
            if (componenteAtual instanceof JTextField) {
                ((JTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            }else if(componenteAtual instanceof JFormattedTextField){
                ((JFormattedTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            }else if (componenteAtual instanceof JComboBox){
                ((JComboBox) componenteAtual).setSelectedIndex(-1);
                componenteAtual.setEnabled(estadoComponentes);
            }else if(componenteAtual instanceof JRadioButton){
               componenteAtual.setEnabled(estadoComponentes);
               ((JRadioButton) componenteAtual).setSelected(false);
            }else if (componenteAtual instanceof JTextArea){
                ((JTextArea) componenteAtual).setText("");
                componenteAtual.setEnabled(estadoComponentes);
            }  
        }
    }
    
}
