package model;

import java.time.LocalDate;

public class MovimentoCaixa {

    private int id;
    private LocalDate dataEmissao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private float valorEmitido;
    private float multas;
    private float correcaoMonetaria;
    private float juros;
    private float valorPagoRec;
    private String tipo;
    private boolean flagRateio;
    private String flagFormula;
    private String observacao;
    private String status;
    private Edificio edificio;
    private Fornecedor fornecedor;
    private CustoNivel1 custoNivel1;
    private CustoNivel2 custoNivel2;

    public MovimentoCaixa() {
    }

    public MovimentoCaixa(int id, LocalDate dataEmissao, LocalDate dataVencimento, LocalDate dataPagamento, float valorEmitido, float multas, float correcaoMonetaria, float juros, float valorPagoRec, String tipo, boolean flagRateio, String flagFormula, String observacao, String status, Edificio edificio, Fornecedor fornecedor, CustoNivel1 custoNivel1, CustoNivel2 custoNivel2) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorEmitido = valorEmitido;
        this.multas = multas;
        this.correcaoMonetaria = correcaoMonetaria;
        this.juros = juros;
        this.valorPagoRec = valorPagoRec;
        this.tipo = tipo;
        this.flagRateio = flagRateio;
        this.flagFormula = flagFormula;
        this.observacao = observacao;
        this.status = status;
        this.edificio = edificio;
        this.fornecedor = fornecedor;
        this.custoNivel1 = custoNivel1;
        this.custoNivel2 = custoNivel2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public float getValorEmitido() {
        return valorEmitido;
    }

    public void setValorEmitido(float valorEmitido) {
        this.valorEmitido = valorEmitido;
    }

    public float getMultas() {
        return multas;
    }

    public void setMultas(float multas) {
        this.multas = multas;
    }

    public float getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }

    public void setCorrecaoMonetaria(float correcaoMonetaria) {
        this.correcaoMonetaria = correcaoMonetaria;
    }

    public float getJuros() {
        return juros;
    }

    public void setJuros(float juros) {
        this.juros = juros;
    }

    public float getValorPagoRec() {
        return valorPagoRec;
    }

    public void setValorPagoRec(float valorPagoRec) {
        this.valorPagoRec = valorPagoRec;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isFlagRateio() {
        return flagRateio;
    }

    public void setFlagRateio(boolean flagRateio) {
        this.flagRateio = flagRateio;
    }

    public String getFlagFormula() {
        return flagFormula;
    }

    public void setFlagFormula(String flagFormula) {
        this.flagFormula = flagFormula;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public CustoNivel1 getCustoNivel1() {
        return custoNivel1;
    }

    public void setCustoNivel1(CustoNivel1 custoNivel1) {
        this.custoNivel1 = custoNivel1;
    }

    public CustoNivel2 getCustoNivel2() {
        return custoNivel2;
    }

    public void setCustoNivel2(CustoNivel2 custoNivel2) {
        this.custoNivel2 = custoNivel2;
    }
}
