package controller;

import view.cadastro.TelaCadastroCustoNivel1;
import view.consulta.TelaConsultaCustoNivel1;

public class CadastroCustoNivel1Controller extends CadastroControllerBase {

    public CadastroCustoNivel1Controller(TelaCadastroCustoNivel1 telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaCustoNivel1 telaConsulta = new TelaConsultaCustoNivel1(null, true);
        telaConsulta.setVisible(true);
    }
}

