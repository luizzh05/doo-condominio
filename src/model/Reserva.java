package model;

public class Reserva {

    private int id;
    private String dataHoraInicio;
    private String dataHoraFim;
    private String observacao;
    private String status;

    AreaCompartilhadaEdificio areaCompartilhadaEdificio;
    
    //Ainda temos atributos/objetos para serem inseridos aqui
    
    

    public Reserva() {
    }

    public Reserva(int id, String dataHoraInicio, String dataHoraFim, String observacao, String status, AreaCompartilhadaEdificio areaCompartilhadaEdificio) {
        this.id = id;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.observacao = observacao;
        this.status = status;
        this.areaCompartilhadaEdificio = areaCompartilhadaEdificio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
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

    public AreaCompartilhadaEdificio getAreaCompartilhadaEdificio() {
        return areaCompartilhadaEdificio;
    }

    public void setAreaCompartilhadaEdificio(AreaCompartilhadaEdificio areaCompartilhadaEdificio) {
        this.areaCompartilhadaEdificio = areaCompartilhadaEdificio;
    }

    
    
}
