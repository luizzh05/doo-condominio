package model;

public class CustoNivel2 {
    private int id;
    private String descricao;
    private String observacao;
    private String status;
    private CustoNivel1 custoNivel1;

    public CustoNivel2() {
    }

    public CustoNivel2(int id, String descricao, String observacao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.status = status;
    }

    public CustoNivel2(int id, String descricao, String observacao, String status, CustoNivel1 custoNivel1) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.status = status;
        this.custoNivel1 = custoNivel1;
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

    public CustoNivel1 getCustoNivel1() {
        return custoNivel1;
    }

    public void setCustoNivel1(CustoNivel1 custoNivel1) {
        this.custoNivel1 = custoNivel1;
    }
    
    
}
