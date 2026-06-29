package controller;

import view.cadastro.TelaCadastroFuncaoMandato;
import view.consulta.TelaConsultaFuncaoMandato;

public class CadastroFuncaoMandatoController extends CadastroControllerBase {

    public CadastroFuncaoMandatoController(TelaCadastroFuncaoMandato telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaFuncaoMandato telaConsulta = new TelaConsultaFuncaoMandato(null, true);
        telaConsulta.setVisible(true);
    }
}

