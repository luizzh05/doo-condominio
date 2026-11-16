package service;

final class CadastroValidation {
    private CadastroValidation() {}

    static void validarTexto(String descricao, String observacao, String status) {
        if (descricao == null || descricao.isBlank() || descricao.length() > 100) {
            throw new IllegalArgumentException("Informe uma descricao de ate 100 caracteres.");
        }
        if (observacao != null && observacao.length() > 100) {
            throw new IllegalArgumentException("A observacao deve ter ate 100 caracteres.");
        }
        if (!"A".equals(status) && !"I".equals(status)) {
            throw new IllegalArgumentException("Selecione o status.");
        }
    }
}
