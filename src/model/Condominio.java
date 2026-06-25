package model;

import java.time.LocalDate;

public class Condominio {

    private int id;
    private int mesReferencia;
    private int anoReferencia;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private float juros;
    private float multas;
    private float correcao;
    private float valorEmitido;
    private float valorPago;
    private String observacao;
    private String status;
    private UnidadeCondomino unidadeCondomino;

    public Condominio() {
    }

    public Condominio(int id, int mesReferencia, int anoReferencia, LocalDate dataEmissao, LocalDate dataVencimento, float juros, float multas, float correcao, float valorEmitido, float valorPago, String observacao, String status, UnidadeCondomino unidadeCondomino) {
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

    public float getJuros() {
        return juros;
    }

    public void setJuros(float juros) {
        this.juros = juros;
    }

    public float getMultas() {
        return multas;
    }

    public void setMultas(float multas) {
        this.multas = multas;
    }

    public float getCorrecao() {
        return correcao;
    }

    public void setCorrecao(float correcao) {
        this.correcao = correcao;
    }

    public float getValorEmitido() {
        return valorEmitido;
    }

    public void setValorEmitido(float valorEmitido) {
        this.valorEmitido = valorEmitido;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
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
