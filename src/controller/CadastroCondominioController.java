package controller;

import view.cadastro.TelaCadastroCondominio;
import view.consulta.TelaConsultaCondominio;

public class CadastroCondominioController extends CadastroControllerBase {

    public CadastroCondominioController(TelaCadastroCondominio telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaCondominio telaConsulta = new TelaConsultaCondominio(null, true);
        telaConsulta.setVisible(true);
    }
}

