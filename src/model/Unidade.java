package model;

public class Unidade {
    
    private int id;
    private String descricao;
    private float metragemTotal;
    private float metragemIndividual;
    private int tipoUnidade;
    private String observacao;
    private String status;

    public Unidade(int id, String descricao, float metragemTotal, float metragemIndividual, int tipoUnidade, String observacao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.metragemTotal = metragemTotal;
        this.metragemIndividual = metragemIndividual;
        this.tipoUnidade = tipoUnidade;
        this.observacao = observacao;
        this.status = status;
    }
    
    public Unidade() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getMetragemTotal() {
        return metragemTotal;
    }

    public void setMetragemTotal(float metragemTotal) {
        this.metragemTotal = metragemTotal;
    }

    public float getMetragemIndividual() {
        return metragemIndividual;
    }

    public void setMetragemIndividual(float metragemIndividual) {
        this.metragemIndividual = metragemIndividual;
    }

    public int getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(int tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
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
    
}
