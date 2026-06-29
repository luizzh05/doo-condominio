package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.consulta.TelaConsultaLeitura;

public class ConsultaLeituraController implements ActionListener {

    private final TelaConsultaLeitura tela;

    public ConsultaLeituraController(TelaConsultaLeitura tela) {
        this.tela = tela;
        this.tela.getjButtonSelecionar().addActionListener(this);
        this.tela.getjButtonPesquisar().addActionListener(this);
        this.tela.getjButtonLimpar().addActionListener(this);
        this.tela.getjButtonFechar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == this.tela.getjButtonSelecionar()) {
            this.tela.dispose();
        } else if (action.getSource() == this.tela.getjButtonPesquisar()) {
            String selecionado = this.tela.getjComboBoxCampoFiltro().getSelectedItem().toString();
            switch (selecionado) {
                case "ID":
                    break;
                case "Unidade":
                    break;
                case "Tipo":
                    break;
                case "Mes":
                    break;
                case "Ano":
                    break;
                case "Status":
                    break;
                default:
                    break;
            }
        } else if (action.getSource() == this.tela.getjButtonLimpar()) {
            this.tela.getjTextFieldFiltro().setText("");
            this.tela.getjComboBoxCampoFiltro().setSelectedIndex(0);
        } else if (action.getSource() == this.tela.getjButtonFechar()) {
            this.tela.dispose();
        }
    }
}
