
package model;

public class AreaCompartilhadaEdificio {
   
    private int id;
    private String observacao;
    private String status;
    
    AreaCompartilhada areaCompartilhada;
    Edificio edificio;
    
    

    public AreaCompartilhadaEdificio() {
    }

    public AreaCompartilhadaEdificio(int id, String observacao, String status, AreaCompartilhada areaCompartilhada, Edificio edificio) {
        this.id = id;
        this.observacao = observacao;
        this.status = status;
        this.areaCompartilhada = areaCompartilhada;
        this.edificio = edificio;
    }

    public AreaCompartilhada getAreaCompartilhada() {
        return areaCompartilhada;
    }

    public void setAreaCompartilhada(AreaCompartilhada areaCompartilhada) {
        this.areaCompartilhada = areaCompartilhada;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
