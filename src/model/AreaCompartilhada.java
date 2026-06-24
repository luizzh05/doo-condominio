package model;
public class AreaCompartilhada {
    
    private int id;
    private String descricao;
    private String observacao;
    private String status;

    public AreaCompartilhada() {
    }

    public AreaCompartilhada(int id, String descricao, String observacao, String status) {
        this.id = id;
        this.descricao = descricao;
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
