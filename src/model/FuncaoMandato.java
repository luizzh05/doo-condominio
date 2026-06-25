package model;

import java.time.LocalDate;

public class FuncaoMandato {

    private int id;
    private String funcao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacao;
    private String status;
    private SindicoProfissional sindicoProfissional;
    private Edificio edificio;

    public FuncaoMandato() {
    }

    public FuncaoMandato(int id, String funcao, LocalDate dataInicio, LocalDate dataFim, String observacao, String status, SindicoProfissional sindicoProfissional, Edificio edificio) {
        this.id = id;
        this.funcao = funcao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacao = observacao;
        this.status = status;
        this.sindicoProfissional = sindicoProfissional;
        this.edificio = edificio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
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

    public SindicoProfissional getSindicoProfissional() {
        return sindicoProfissional;
    }

    public void setSindicoProfissional(SindicoProfissional sindicoProfissional) {
        this.sindicoProfissional = sindicoProfissional;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }
}
