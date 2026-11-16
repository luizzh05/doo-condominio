package service;

import java.time.LocalDate;
import model.Pessoa;

final class PessoaValidation {
    private PessoaValidation() {}

    static void validar(Pessoa pessoa) {
        if (pessoa.getEmail() == null || !pessoa.getEmail().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+")) {
            throw new IllegalArgumentException("Informe um email valido.");
        }
        if (pessoa.getDataNascimento() != null && pessoa.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento nao pode estar no futuro.");
        }
    }
}
