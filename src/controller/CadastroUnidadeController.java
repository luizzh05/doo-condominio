package controller;

import view.cadastro.TelaCadastroUnidade;
import view.consulta.TelaConsultaUnidade;

public class CadastroUnidadeController extends CadastroControllerBase {

    public CadastroUnidadeController(TelaCadastroUnidade telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaUnidade telaConsulta = new TelaConsultaUnidade(null, true);
        telaConsulta.setVisible(true);
    }
}

