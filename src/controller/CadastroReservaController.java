package controller;

import view.cadastro.TelaCadastroReserva;
import view.consulta.TelaConsultaReserva;

public class CadastroReservaController extends CadastroControllerBase {

    public CadastroReservaController(TelaCadastroReserva telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaReserva telaConsulta = new TelaConsultaReserva(null, true);
        telaConsulta.setVisible(true);
    }
}

