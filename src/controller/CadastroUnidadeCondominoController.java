package controller;

import view.cadastro.TelaCadastroUnidadeCondomino;
import view.consulta.TelaConsultaUnidadeCondomino;

public class CadastroUnidadeCondominoController extends CadastroControllerBase {

    public CadastroUnidadeCondominoController(TelaCadastroUnidadeCondomino telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaUnidadeCondomino telaConsulta = new TelaConsultaUnidadeCondomino(null, true);
        telaConsulta.setVisible(true);
    }
}

