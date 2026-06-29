package controller.cadastro;

import controller.consulta.ConsultaFuncaoMandatoController;
import controller.consulta.ConsultaSindicoProfissionalController;
import controller.consulta.ConsultaEdificioController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroFuncaoMandato;
import view.consulta.TelaConsultaFuncaoMandato;
import view.consulta.TelaConsultaSindicoProfissional;
import view.consulta.TelaConsultaEdificio;

public class CadastroFuncaoMandatoController implements ActionListener {

    private final TelaCadastroFuncaoMandato tela;

    public CadastroFuncaoMandatoController(TelaCadastroFuncaoMandato telaCadastro) {
        this.tela = telaCadastro;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSelecionarSindicoProfissional().addActionListener(this);
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
            if (gravarFuncaoMandato()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
            }
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaFuncaoMandato telaConsulta = new TelaConsultaFuncaoMandato(null, true);
            ConsultaFuncaoMandatoController consultaController = new ConsultaFuncaoMandatoController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarSindicoProfissional()) {
            TelaConsultaSindicoProfissional telaConsulta = new TelaConsultaSindicoProfissional(null, true);
            new ConsultaSindicoProfissionalController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio telaConsulta = new TelaConsultaEdificio(null, true);
            new ConsultaEdificioController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarFuncaoMandato() {
        return true;
    }
}
