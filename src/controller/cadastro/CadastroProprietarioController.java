package controller.cadastro;

import controller.consulta.ConsultaProprietarioController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Utils;
import view.cadastro.TelaCadastroProprietario;
import view.consulta.TelaConsultaProprietario;

public class CadastroProprietarioController implements ActionListener {

    private final TelaCadastroProprietario tela;

    public CadastroProprietarioController(TelaCadastroProprietario telaCadastro) {
        this.tela = telaCadastro;

        this.tela.getjButtonNovo().addActionListener(this);
        this.tela.getjButtonCancelar().addActionListener(this);
        this.tela.getjButtonGravar().addActionListener(this);
        this.tela.getjButtonBuscar().addActionListener(this);
        this.tela.getjButtonSair().addActionListener(this);

        Utils.ativaDesativaBtn(this.tela.getjPanelbotoes(), false);
        Utils.LimpaComponentes(this.tela.getjPanelDados(), false, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tela.getjButtonNovo()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), true);
            Utils.LimpaComponentes(tela.getjPanelDados(), true, null);
            tela.getjTextFieldId().setEnabled(false);
        } else if (e.getSource() == tela.getjButtonCancelar()) {
            Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
            Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
        } else if (e.getSource() == tela.getjButtonGravar()) {
            if (gravarProprietario()) {
                Utils.ativaDesativaBtn(tela.getjPanelbotoes(), false);
                Utils.LimpaComponentes(tela.getjPanelDados(), false, null);
            }
        } else if (e.getSource() == tela.getjButtonBuscar()) {
            TelaConsultaProprietario telaConsulta = new TelaConsultaProprietario(null, true);
            ConsultaProprietarioController consultaController = new ConsultaProprietarioController(telaConsulta);
            telaConsulta.setVisible(true);
        } else if (e.getSource() == tela.getjButtonSair()) {
            tela.dispose();
        }
    }

    private boolean gravarProprietario() {
        return true;
    }
}
