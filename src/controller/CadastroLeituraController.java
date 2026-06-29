package controller;

import view.cadastro.TelaCadastroLeitura;
import view.consulta.TelaConsultaLeitura;

public class CadastroLeituraController extends CadastroControllerBase {

    public CadastroLeituraController(TelaCadastroLeitura telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaLeitura telaConsulta = new TelaConsultaLeitura(null, true);
        telaConsulta.setVisible(true);
    }
}

