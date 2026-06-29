package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import utils.Utils;

public abstract class CadastroControllerBase implements ActionListener {

    private final JDialog telaCadastro;
    private final JPanel painelDados;
    private final JPanel painelBotoes;
    private final ButtonGroup grupoRadio;
    private final JButton botaoNovo;
    private final JButton botaoCancelar;
    private final JButton botaoGravar;
    private final JButton botaoBuscar;
    private final JButton botaoSair;

    protected CadastroControllerBase(JDialog telaCadastro) {
        this.telaCadastro = telaCadastro;
        this.painelDados = getCampo("jPanelDados", JPanel.class);
        this.painelBotoes = getCampo("jPanelbotoes", JPanel.class);
        this.grupoRadio = getCampoOpcional("btnGroupCalculo", ButtonGroup.class);
        this.botaoNovo = getCampo("jButtonNovo", JButton.class);
        this.botaoCancelar = getCampo("jButtonCancelar", JButton.class);
        this.botaoGravar = getCampo("jButtonGravar", JButton.class);
        this.botaoBuscar = getCampo("jButtonBuscar", JButton.class);
        this.botaoSair = getCampo("jButtonSair", JButton.class);

        this.botaoNovo.addActionListener(this);
        this.botaoCancelar.addActionListener(this);
        this.botaoGravar.addActionListener(this);
        this.botaoBuscar.addActionListener(this);
        this.botaoSair.addActionListener(this);

        Utils.ativaDesativaBtn(this.painelBotoes, false);
        Utils.LimpaComponentes(this.painelDados, false, this.grupoRadio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origem = e.getSource();

        if (origem == this.botaoNovo) {
            Utils.ativaDesativaBtn(this.painelBotoes, true);
            Utils.LimpaComponentes(this.painelDados, true, this.grupoRadio);
            aoNovo();
        } else if (origem == this.botaoCancelar) {
            Utils.ativaDesativaBtn(this.painelBotoes, false);
            Utils.LimpaComponentes(this.painelDados, false, this.grupoRadio);
        } else if (origem == this.botaoGravar) {
            if (gravar()) {
                Utils.ativaDesativaBtn(this.painelBotoes, false);
                Utils.LimpaComponentes(this.painelDados, false, this.grupoRadio);
            }
        } else if (origem == this.botaoBuscar) {
            abrirTelaConsulta();
        } else if (origem == this.botaoSair) {
            this.telaCadastro.dispose();
        }
    }

    protected boolean gravar() { return true; }

    protected void aoNovo() {}

    protected abstract void abrirTelaConsulta();

    protected JPanel getPainelDados() { return painelDados; }
    protected JPanel getPainelBotoes() { return painelBotoes; }
    protected ButtonGroup getGrupoRadio() { return grupoRadio; }

    private <T> T getCampo(String nomeCampo, Class<T> tipo) {
        T campo = getCampoOpcional(nomeCampo, tipo);
        if (campo == null) {
            throw new IllegalStateException("Campo obrigatorio nao encontrado: " + nomeCampo
                    + " em " + this.telaCadastro.getClass().getName());
        }
        return campo;
    }

    private <T> T getCampoOpcional(String nomeCampo, Class<T> tipo) {
        try {
            Field campo = this.telaCadastro.getClass().getDeclaredField(nomeCampo);
            campo.setAccessible(true);
            Object valor = campo.get(this.telaCadastro);
            return tipo.cast(valor);
        } catch (NoSuchFieldException ex) {
            return null;
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException("Nao foi possivel acessar o campo: " + nomeCampo, ex);
        }
    }
}
