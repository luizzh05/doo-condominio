
package model;


public class Edificio {
    
    private int id;
    private String nome;
    private int quantidadeAndares;
    private int quantidadeUnidades;
    private String cnpj;
    private int anoLancamento;
    private float areaTotal;
    private String cep;
    private String logradouro;
    private String cidade;
    private String bairro;
    private String complemento;
    private String numeroUnidadeAgua;
    private String numeroUnidadeGas;
    private String formulaCalculo;
    private String observacao;
    private String status;


    public Edificio() {
    }

    public Edificio(int id, String nome, int quantidadeAndares, int quantidadeUnidades, String cnpj, int anoLancamento, float areaTotal, String cep, String logradouro, String cidade, String bairro, String complemento, String numeroUnidadeAgua, String numeroUnidadeGas, String formulaCalculo, String observacao, String status) {
        this.id = id;
        this.nome = nome;
        this.quantidadeAndares = quantidadeAndares;
        this.quantidadeUnidades = quantidadeUnidades;
        this.cnpj = cnpj;
        this.anoLancamento = anoLancamento;
        this.areaTotal = areaTotal;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numeroUnidadeAgua = numeroUnidadeAgua;
        this.numeroUnidadeGas = numeroUnidadeGas;
        this.formulaCalculo = formulaCalculo;
        this.observacao = observacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeAndares() {
        return quantidadeAndares;
    }

    public void setQuantidadeAndares(int quantidadeAndares) {
        this.quantidadeAndares = quantidadeAndares;
    }

    public int getQuantidadeUnidades() {
        return quantidadeUnidades;
    }

    public void setQuantidadeUnidades(int quantidadeUnidades) {
        this.quantidadeUnidades = quantidadeUnidades;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public float getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(float areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumeroUnidadeAgua() {
        return numeroUnidadeAgua;
    }

    public void setNumeroUnidadeAgua(String numeroUnidadeAgua) {
        this.numeroUnidadeAgua = numeroUnidadeAgua;
    }

    public String getNumeroUnidadeGas() {
        return numeroUnidadeGas;
    }

    public void setNumeroUnidadeGas(String numeroUnidadeGas) {
        this.numeroUnidadeGas = numeroUnidadeGas;
    }

    public String getFormulaCalculo() {
        return formulaCalculo;
    }

    public void setFormulaCalculo(String formulaCalculo) {
        this.formulaCalculo = formulaCalculo;
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
