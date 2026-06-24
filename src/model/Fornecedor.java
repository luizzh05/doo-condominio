package model;

import java.time.LocalDate;


public class Fornecedor extends Pessoa {

    public Fornecedor() {
    }

    public Fornecedor(int id, String nomeFantasia, String razaoSocial, String cpf, String rg, String cnpj, String inscricaoEstadual, String fone1, String fone2, String email, LocalDate dataNascimento, LocalDate dataCadastro, String estadoCivil, String cep, String logradouro, String cidade, String bairro, String complemento, String observacao, String tipoPessoa, String status) {
        super(id, nomeFantasia, razaoSocial, cpf, rg, cnpj, inscricaoEstadual, fone1, fone2, email, dataNascimento, dataCadastro, estadoCivil, cep, logradouro, cidade, bairro, complemento, observacao, tipoPessoa, status);
    }
    
}
