package controller;

import view.cadastro.TelaCadastroMovimentoCaixa;
import view.consulta.TelaConsultaMovimentoCaixa;

public class CadastroMovimentoCaixaController extends CadastroControllerBase {

    public CadastroMovimentoCaixaController(TelaCadastroMovimentoCaixa telaCadastro) {
        super(telaCadastro);
    }

    @Override
    protected void abrirTelaConsulta() {
        TelaConsultaMovimentoCaixa telaConsulta = new TelaConsultaMovimentoCaixa(null, true);
        telaConsulta.setVisible(true);
    }
}

