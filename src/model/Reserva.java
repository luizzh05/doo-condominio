package model;

import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String observacao;
    private String status;

    private AreaCompartilhadaEdificio areaCompartilhadaEdificio;
    
    

    public Reserva() {
    }

    public Reserva(int id, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String observacao, String status, AreaCompartilhadaEdificio areaCompartilhadaEdificio) {
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

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
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
