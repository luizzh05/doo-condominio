package model;

import java.time.LocalDate;

public class Reserva {

    private int id;
    private LocalDate dataHoraInicio;
    private LocalDate dataHoraFim;
    private String observacao;
    private String status;

    private AreaCompartilhadaEdificio areaCompartilhadaEdificio;
    
    

    public Reserva() {
    }

    public Reserva(int id, LocalDate dataHoraInicio, LocalDate dataHoraFim, String observacao, String status, AreaCompartilhadaEdificio areaCompartilhadaEdificio) {
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

    public LocalDate getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDate dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDate getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDate dataHoraFim) {
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
