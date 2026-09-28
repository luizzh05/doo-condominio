package model;

import java.time.LocalDate;

public class Leitura {

    private int id;
    private LocalDate dataLeitura;
    private int mesReferencia;
    private int anoReferencia;
    private double medicaoAnterior;
    private double medicaoAtual;
    private String tipo;
    private String observacao;
    private String status;
    private Unidade unidade;
    private UnidadeCondomino unidadeCondomino;

    public Leitura() {
    }

    public Leitura(int id, LocalDate dataLeitura, int mesReferencia, int anoReferencia, double medicaoAnterior, double medicaoAtual, String tipo, String observacao, String status, Unidade unidade) {
        this.id = id;
        this.dataLeitura = dataLeitura;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.medicaoAnterior = medicaoAnterior;
        this.medicaoAtual = medicaoAtual;
        this.tipo = tipo;
        this.observacao = observacao;
        this.status = status;
        this.unidade = unidade;
    }

    public Leitura(int id, LocalDate dataLeitura, int mesReferencia, int anoReferencia,
            double medicaoAnterior, double medicaoAtual, String tipo, String observacao,
            String status, Unidade unidade, UnidadeCondomino unidadeCondomino) {
        this(id, dataLeitura, mesReferencia, anoReferencia, medicaoAnterior, medicaoAtual,
                tipo, observacao, status, unidade);
        this.unidadeCondomino = unidadeCondomino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDate dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public int getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(int mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public int getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public double getMedicaoAnterior() {
        return medicaoAnterior;
    }

    public void setMedicaoAnterior(double medicaoAnterior) {
        this.medicaoAnterior = medicaoAnterior;
    }

    public double getMedicaoAtual() {
        return medicaoAtual;
    }

    public void setMedicaoAtual(double medicaoAtual) {
        this.medicaoAtual = medicaoAtual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public UnidadeCondomino getUnidadeCondomino() {
        return unidadeCondomino;
    }

    public void setUnidadeCondomino(UnidadeCondomino unidadeCondomino) {
        this.unidadeCondomino = unidadeCondomino;
    }
}
