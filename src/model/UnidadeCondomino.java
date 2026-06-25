package model;

import java.time.LocalDate;

public class UnidadeCondomino {

    private int id;
    private LocalDate dataAquisicao;
    private LocalDate dataVenda;
    private String observacao;
    private String status;
    private Unidade unidade;
    private Proprietario proprietario;

    public UnidadeCondomino() {
    }

    public UnidadeCondomino(int id, LocalDate dataAquisicao, LocalDate dataVenda, String observacao, String status, Unidade unidade, Proprietario proprietario) {
        this.id = id;
        this.dataAquisicao = dataAquisicao;
        this.dataVenda = dataVenda;
        this.observacao = observacao;
        this.status = status;
        this.unidade = unidade;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
