package model;

public class CustoNivel1 {
    private int id;
    private String descricao;
    private String tipoCc;
    private String observacao;
    private String status;

    public CustoNivel1() {
    }

    public CustoNivel1(int id, String descricao, String tipoCc, String observacao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.tipoCc = tipoCc;
        this.observacao = observacao;
        this.status = status;
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

    public String getTipoCc() {
        return tipoCc;
    }

    public void setTipoCc(String tipoCc) {
        this.tipoCc = tipoCc;
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
