package model;

import java.time.LocalDate;

public class Condominio {

    private int id;
    private int mesReferencia;
    private int anoReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private double juros;
    private double multas;
    private double correcao;
    private double valorEmitido;
    private double valorPago;
    private String observacao;
    private String status;
    private UnidadeCondomino unidadeCondomino;

    public Condominio() {
    }

    public Condominio(int id, int mesReferencia, int anoReferencia, LocalDate dataEmissao, LocalDate dataVencimento, double juros, double multas, double correcao, double valorEmitido, double valorPago, String observacao, String status, UnidadeCondomino unidadeCondomino) {
        this.id = id;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.juros = juros;
        this.multas = multas;
        this.correcao = correcao;
        this.valorEmitido = valorEmitido;
        this.valorPago = valorPago;
        this.observacao = observacao;
        this.status = status;
        this.unidadeCondomino = unidadeCondomino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getMultas() {
        return multas;
    }

    public void setMultas(double multas) {
        this.multas = multas;
    }

    public double getCorrecao() {
        return correcao;
    }

    public void setCorrecao(double correcao) {
        this.correcao = correcao;
    }

    public double getValorEmitido() {
        return valorEmitido;
    }

    public void setValorEmitido(double valorEmitido) {
        this.valorEmitido = valorEmitido;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
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

    public UnidadeCondomino getUnidadeCondomino() {
        return unidadeCondomino;
    }

    public void setUnidadeCondomino(UnidadeCondomino unidadeCondomino) {
        this.unidadeCondomino = unidadeCondomino;
    }
}
