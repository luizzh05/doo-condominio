package controller;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import model.AreaCompartilhada;
import view.cadastro.TelaCadastroAreaCompartilhada;
import view.consulta.TelaConsultaAreaCompartilhada;

public class AreaCompartilhadaController {

    private final List<AreaCompartilhada> memoria = new ArrayList<>();
    private int proximoId = 1;

    public void gravar(TelaCadastroAreaCompartilhada tela) {
        AreaCompartilhada area = new AreaCompartilhada();
        area.setId(proximoId++);
        area.setDescricao(tela.getDescricao());
        area.setObservacao(tela.getObservacao());
        area.setStatus("Ativo");

        memoria.add(area);
        tela.setId(String.valueOf(area.getId()));
        tela.mostrarMensagem("Exemplo: area compartilhada adicionada em memoria.");
    }

    public void novo(TelaCadastroAreaCompartilhada tela) {
        tela.limparCampos();
        tela.habilitarEdicao(true);
    }

    public void cancelar(TelaCadastroAreaCompartilhada tela) {
        tela.limparCampos();
        tela.habilitarEdicao(false);
    }

    public void abrirConsulta(TelaCadastroAreaCompartilhada tela) {
        Frame parent = tela.getOwner() instanceof Frame ? (Frame) tela.getOwner() : null;
        TelaConsultaAreaCompartilhada consulta = new TelaConsultaAreaCompartilhada(parent, true, this);
        preencherTabela(consulta, memoria);
        consulta.setVisible(true);
    }

    public void pesquisar(TelaConsultaAreaCompartilhada tela) {
        String filtro = tela.getFiltro();
        if (filtro.isBlank()) {
            preencherTabela(tela, memoria);
            return;
        }

        String filtroNormalizado = filtro.toLowerCase(Locale.ROOT);
        List<AreaCompartilhada> resultado = memoria.stream()
                .filter(area -> contem(area.getDescricao(), filtroNormalizado)
                || contem(area.getStatus(), filtroNormalizado)
                || String.valueOf(area.getId()).contains(filtroNormalizado))
                .toList();

        preencherTabela(tela, resultado);
    }

    public void limparConsulta(TelaConsultaAreaCompartilhada tela) {
        tela.limparFiltro();
        preencherTabela(tela, memoria);
    }

    private boolean contem(String valor, String filtroNormalizado) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtroNormalizado);
    }

    private void preencherTabela(TelaConsultaAreaCompartilhada tela, List<AreaCompartilhada> areas) {
        DefaultTableModel modelo = tela.getModeloTabela();
        modelo.setRowCount(0);

        for (AreaCompartilhada area : areas) {
            modelo.addRow(new Object[]{
                area.getId(),
                area.getDescricao(),
                area.getStatus()
            });
        }
    }
}
