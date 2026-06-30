package controller.consulta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mock.DemoTableData;
import view.consulta.TelaConsultaCustoNivel1;

public class ConsultaCustoNivel1Controller implements ActionListener {

    private final TelaConsultaCustoNivel1 tela;

    public ConsultaCustoNivel1Controller(TelaConsultaCustoNivel1 tela) {
        this.tela = tela;
        this.tela.getjButtonSelecionar().addActionListener(this);
        this.tela.getjButtonPesquisar().addActionListener(this);
        this.tela.getjButtonLimpar().addActionListener(this);
        this.tela.getjButtonFechar().addActionListener(this);
        DemoTableData.carregarCustosNivel1(this.tela.getjTableResultado());
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
                case "Descricao":
                    break;
                case "Tipo CC":
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
