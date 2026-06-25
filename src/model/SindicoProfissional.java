package model;

import java.time.LocalDate;

public class SindicoProfissional extends Pessoa {

    private String cra;

    public SindicoProfissional() {
    }

    public SindicoProfissional(int id, String nomeFantasia, String razaoSocial, String cpf, String rg, String cnpj, String inscricaoEstadual, String fone1, String fone2, String email, LocalDate dataNascimento, LocalDate dataCadastro, String estadoCivil, String cep, String logradouro, String cidade, String bairro, String complemento, String observacao, String tipoPessoa, String status, String cra) {
        super(id, nomeFantasia, razaoSocial, cpf, rg, cnpj, inscricaoEstadual, fone1, fone2, email, dataNascimento, dataCadastro, estadoCivil, cep, logradouro, cidade, bairro, complemento, observacao, tipoPessoa, status);
        this.cra = cra;
    }

    public String getCra() {
        return cra;
    }

    public void setCra(String cra) {
        this.cra = cra;
    }
}
