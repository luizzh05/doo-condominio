package controller;

import view.cadastro.TelaCadastroAreaCompartilhada;
import view.consulta.TelaConsultaAreaCompartilhada;

public class CadastroAreaCompartilhadaController extends CadastroControllerBase {

    public CadastroAreaCompartilhadaController(TelaCadastroAreaCompartilhada telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaAreaCompartilhada telaConsulta = new TelaConsultaAreaCompartilhada(null, true);
        telaConsulta.setVisible(true);
    }
}

