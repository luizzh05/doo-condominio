package controller.cadastro;

import controller.consulta.ConsultaAreaCompartilhadaEdificioController;
import controller.consulta.ConsultaAreaCompartilhadaController;
import controller.consulta.ConsultaEdificioController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroAreaCompartilhadaEdificio;
import view.consulta.TelaConsultaAreaCompartilhadaEdificio;
import view.consulta.TelaConsultaAreaCompartilhada;
import view.consulta.TelaConsultaEdificio;

public class CadastroAreaCompartilhadaEdificioController implements ActionListener {

    private final TelaCadastroAreaCompartilhadaEdificio tela;

    public CadastroAreaCompartilhadaEdificioController(TelaCadastroAreaCompartilhadaEdificio telaCadastro) {
        this.tela = telaCadastro;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSelecionarAreaCompartilhada().addActionListener(this);
        this.tela.getjButtonSelecionarEdificio().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);

        Utils.ativaDesativaBtn(this.tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(this.tela.getjPanelDados(), false, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjButtonNovo()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        } else if (e.getSource() == tela.getjButtonGravar()) {
            if (gravarAreaCompartilhadaEdificio()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
            }
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaAreaCompartilhadaEdificio telaConsulta = new TelaConsultaAreaCompartilhadaEdificio(null, true);
            ConsultaAreaCompartilhadaEdificioController consultaController = new ConsultaAreaCompartilhadaEdificioController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarAreaCompartilhada()) {
            TelaConsultaAreaCompartilhada telaConsulta = new TelaConsultaAreaCompartilhada(null, true);
            new ConsultaAreaCompartilhadaController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio telaConsulta = new TelaConsultaEdificio(null, true);
            new ConsultaEdificioController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarAreaCompartilhadaEdificio() {
        return true;
    }
}
