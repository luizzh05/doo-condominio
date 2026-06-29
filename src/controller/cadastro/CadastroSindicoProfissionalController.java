package controller.cadastro;

import controller.consulta.ConsultaSindicoProfissionalController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroSindicoProfissional;
import view.consulta.TelaConsultaSindicoProfissional;

public class CadastroSindicoProfissionalController implements ActionListener {

    private final TelaCadastroSindicoProfissional tela;

    public CadastroSindicoProfissionalController(TelaCadastroSindicoProfissional telaCadastro) {
        this.tela = telaCadastro;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
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
            if (gravarSindicoProfissional()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
            }
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaSindicoProfissional telaConsulta = new TelaConsultaSindicoProfissional(null, true);
            ConsultaSindicoProfissionalController consultaController = new ConsultaSindicoProfissionalController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarSindicoProfissional() {
        return true;
    }
}
