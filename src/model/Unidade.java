package model;

public class Unidade {
    
    private int id;
    private String descricao;
    private double metragemTotal;
    private double metragemIndividual;
    private String tipoUnidade;
    private String observacao;
    private String status;
    private Edificio edificio;

    public Unidade(int id, String descricao, double metragemTotal, double metragemIndividual, String tipoUnidade, String observacao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.metragemTotal = metragemTotal;
        this.metragemIndividual = metragemIndividual;
        this.tipoUnidade = tipoUnidade;
        this.observacao = observacao;
        this.status = status;
    }

    public Unidade(int id, String descricao, double metragemTotal, double metragemIndividual, String tipoUnidade, String observacao, String status, Edificio edificio) {
        this.id = id;
        this.descricao = descricao;
        this.metragemTotal = metragemTotal;
        this.metragemIndividual = metragemIndividual;
        this.tipoUnidade = tipoUnidade;
        this.observacao = observacao;
        this.status = status;
        this.edificio = edificio;
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

    public double getMetragemTotal() {
        return metragemTotal;
    }

    public void setMetragemTotal(double metragemTotal) {
        this.metragemTotal = metragemTotal;
    }

    public double getMetragemIndividual() {
        return metragemIndividual;
    }

    public void setMetragemIndividual(double metragemIndividual) {
        this.metragemIndividual = metragemIndividual;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
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

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
    
}
