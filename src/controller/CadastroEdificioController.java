package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroEdificio;
import view.consulta.TelaConsultaEdificio;

public class CadastroEdificioController implements ActionListener {

    private final TelaCadastroEdificio tela;

    public CadastroEdificioController(TelaCadastroEdificio telaCadastroEdificio) {
        this.tela = telaCadastroEdificio;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);

        Utils.ativaDesativaBtn(this.tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(this.tela.getjPanelDados(), false, this.tela.getBtnGroupCalculo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjButtonNovo()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, tela.getBtnGroupCalculo());
            tela.getjTextFieldId().setEnabled(false);

        } else if (e.getSource() == tela.getjButtonCancelar()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, tela.getBtnGroupCalculo());

        } else if (e.getSource() == tela.getjButtonGravar()) {
            
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, tela.getBtnGroupCalculo());
            

        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaEdificio telaConsulta = new TelaConsultaEdificio(null, true);
            ConsultaEdificioController consultaController = new ConsultaEdificioController(telaConsulta);
            telaConsulta.setVisible(true);

        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }
}
