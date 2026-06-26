package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Edificio;
import utils.Utils;
import view.cadastro.TelaCadastroEdificio;
import view.consulta.TelaConsultaEdificio;


public class CadastroEdificioController implements ActionListener {
    
    private TelaCadastroEdificio telaCadastroEdificio;
    private List<Edificio> edificios = new ArrayList<>();
    
    public CadastroEdificioController(TelaCadastroEdificio telaCadastroEdificio) {
        this.telaCadastroEdificio = telaCadastroEdificio;
        
        this.telaCadastroEdificio.getjButtonNovo().addActionListener(this);
        this.telaCadastroEdificio.getjButtonCancelar().addActionListener(this);
        this.telaCadastroEdificio.getjButtonGravar().addActionListener(this);
        this.telaCadastroEdificio.getjButtonBuscar().addActionListener(this);
        this.telaCadastroEdificio.getjButtonSair().addActionListener(this);
        
        Utils.ativaDesativaBtn(this.telaCadastroEdificio.getjPanelbotoes(), false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.telaCadastroEdificio.getjButtonNovo()) {
            
            Utils.ativaDesativaBtn(this.telaCadastroEdificio.getjPanelbotoes(), true);
            Utils.LimpaComponentes(this.telaCadastroEdificio.getjPanelDados(), true, this.telaCadastroEdificio.getBtnGroupCalculo());
            
        } else if (e.getSource() == this.telaCadastroEdificio.getjButtonCancelar()) {
            
            Utils.ativaDesativaBtn(this.telaCadastroEdificio.getjPanelbotoes(), false);
            Utils.LimpaComponentes(this.telaCadastroEdificio.getjPanelDados(), false, this.telaCadastroEdificio.getBtnGroupCalculo());
            
        } else if (e.getSource() == this.telaCadastroEdificio.getjButtonGravar()) {
            
            // fazer dps
            
            Utils.ativaDesativaBtn(this.telaCadastroEdificio.getjPanelbotoes(), false);
            Utils.LimpaComponentes(this.telaCadastroEdificio.getjPanelDados(), false, this.telaCadastroEdificio.getBtnGroupCalculo());
            
        } else if (e.getSource() == this.telaCadastroEdificio.getjButtonBuscar()) {
            
            TelaConsultaEdificio telaConsultaEdificio = new TelaConsultaEdificio(null, true);
            telaConsultaEdificio.setVisible(true);
            
        } else if (e.getSource() == this.telaCadastroEdificio.getjButtonSair()) {
            this.telaCadastroEdificio.dispose();

        }
        
    }
    
    
    
}
