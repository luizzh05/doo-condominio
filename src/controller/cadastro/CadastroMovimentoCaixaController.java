package controller.cadastro;

import controller.consulta.ConsultaMovimentoCaixaController;
import controller.consulta.ConsultaEdificioController;
import controller.consulta.ConsultaFornecedorController;
import controller.consulta.ConsultaCustoNivel1Controller;
import controller.consulta.ConsultaCustoNivel2Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroMovimentoCaixa;
import view.consulta.TelaConsultaMovimentoCaixa;
import view.consulta.TelaConsultaEdificio;
import view.consulta.TelaConsultaFornecedor;
import view.consulta.TelaConsultaCustoNivel1;
import view.consulta.TelaConsultaCustoNivel2;

public class CadastroMovimentoCaixaController implements ActionListener {

    private final TelaCadastroMovimentoCaixa tela;

    public CadastroMovimentoCaixaController(TelaCadastroMovimentoCaixa telaCadastro) {
        this.tela = telaCadastro;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSelecionarEdificio().addActionListener(this);
        this.tela.getjButtonSelecionarFornecedor().addActionListener(this);
        this.tela.getjButtonSelecionarCustoNivel1().addActionListener(this);
        this.tela.getjButtonSelecionarCustoNivel2().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);

        Utils.ativaDesativaBtn(this.tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(this.tela.getjPanelDados(), false, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjButtonNovo()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        } else if (e.getSource() == tela.getjButtonGravar()) {
            if (gravarMovimentoCaixa()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
            }
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaMovimentoCaixa telaConsulta = new TelaConsultaMovimentoCaixa(null, true);
            ConsultaMovimentoCaixaController consultaController = new ConsultaMovimentoCaixaController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarEdificio()) {
            TelaConsultaEdificio telaConsulta = new TelaConsultaEdificio(null, true);
            new ConsultaEdificioController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarFornecedor()) {
            TelaConsultaFornecedor telaConsulta = new TelaConsultaFornecedor(null, true);
            new ConsultaFornecedorController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarCustoNivel1()) {
            TelaConsultaCustoNivel1 telaConsulta = new TelaConsultaCustoNivel1(null, true);
            new ConsultaCustoNivel1Controller(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSelecionarCustoNivel2()) {
            TelaConsultaCustoNivel2 telaConsulta = new TelaConsultaCustoNivel2(null, true);
            new ConsultaCustoNivel2Controller(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarMovimentoCaixa() {
        return true;
    }
}
