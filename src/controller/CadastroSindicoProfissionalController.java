package controller;

import view.cadastro.TelaCadastroSindicoProfissional;
import view.consulta.TelaConsultaSindicoProfissional;

public class CadastroSindicoProfissionalController extends CadastroControllerBase {

    public CadastroSindicoProfissionalController(TelaCadastroSindicoProfissional telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaSindicoProfissional telaConsulta = new TelaConsultaSindicoProfissional(null, true);
        telaConsulta.setVisible(true);
    }
}

