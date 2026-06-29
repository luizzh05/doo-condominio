package controller;

import view.cadastro.TelaCadastroCustoNivel2;
import view.consulta.TelaConsultaCustoNivel2;

public class CadastroCustoNivel2Controller extends CadastroControllerBase {

    public CadastroCustoNivel2Controller(TelaCadastroCustoNivel2 telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaCustoNivel2 telaConsulta = new TelaConsultaCustoNivel2(null, true);
        telaConsulta.setVisible(true);
    }
}

