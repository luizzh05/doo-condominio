package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.consulta.TelaConsultaProprietario;

public class ConsultaProprietarioController implements ActionListener {

    private final TelaConsultaProprietario tela;

    public ConsultaProprietarioController(TelaConsultaProprietario tela) {
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
                case "Nome Fantasia":
                    break;
                case "CPF":
                    break;
                case "CNPJ":
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
