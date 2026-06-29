package controller;

import view.cadastro.TelaCadastroAreaCompartilhadaEdificio;
import view.consulta.TelaConsultaAreaCompartilhadaEdificio;

public class CadastroAreaCompartilhadaEdificioController extends CadastroControllerBase {

    public CadastroAreaCompartilhadaEdificioController(TelaCadastroAreaCompartilhadaEdificio telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaAreaCompartilhadaEdificio telaConsulta = new TelaConsultaAreaCompartilhadaEdificio(null, true);
        telaConsulta.setVisible(true);
    }
}

