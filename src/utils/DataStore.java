package utils;

import model.*;
import java.time.LocalDate;
import java.util.*;

public class DataStore {

    private static DataStore instance;

    private final List<Edificio> edificios = new ArrayList<>();
    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private final List<Proprietario> proprietarios = new ArrayList<>();

    private int nextEdificioId = 4;
    private int nextFornecedorId = 4;
    private int nextProprietarioId = 4;

    private DataStore() {
        inicializarDados();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    private void inicializarDados() {
        edificios.add(new Edificio(1, "Edificio Aurora", 10, 40,
                "12.345.678/0001-90", 2010, 1200.0f,
                "01310-100", "Av. Paulista, 1000", "Sao Paulo", "Bela Vista", "",
                "A-001", "G-001", "Por m2", "Predio comercial moderno", "A"));
        edificios.add(new Edificio(2, "Residencial Sol Nascente", 8, 32,
                "98.765.432/0001-11", 2015, 980.5f,
                "04538-133", "Rua dos Pinheiros, 250", "Sao Paulo", "Pinheiros", "",
                "A-002", "G-002", "Por Und.", "Residencial familiar", "A"));
        edificios.add(new Edificio(3, "Torres do Vale", 15, 60,
                "56.789.123/0001-55", 2018, 2400.0f,
                "30140-110", "Av. Afonso Pena, 500", "Belo Horizonte", "Centro", "",
                "A-003", "G-003", "Por m2", "Condominio de luxo", "A"));

        fornecedores.add(new Fornecedor(1, "Manutech Servicos", "Manutech Servicos LTDA",
                "123.456.789-00", "MG-123456", "12.345.678/0001-10", "12345678",
                "(11) 3456-7890", "(11) 98765-4321", "contato@manutech.com.br",
                LocalDate.of(1990, 5, 15), LocalDate.now(), "Casado",
                "01310-100", "Rua das Flores, 100", "Sao Paulo", "Centro", "",
                "Empresa de manutencao predial", "J", "A"));
        fornecedores.add(new Fornecedor(2, "LimpaJa Limpeza", "LimpaJa Servicos de Limpeza LTDA",
                "987.654.321-00", "SP-987654", "98.765.432/0001-20", "98765432",
                "(11) 2345-6789", "(11) 97654-3210", "limpaja@limpaja.com.br",
                LocalDate.of(1985, 8, 20), LocalDate.now(), "Casado",
                "04538-133", "Av. dos Trabalhadores, 200", "Sao Paulo", "Vila Nova", "",
                "Servicos de limpeza e conservacao", "J", "A"));
        fornecedores.add(new Fornecedor(3, "Eletrica Rapida", "Eletrica Rapida ME",
                "456.789.123-00", "RJ-456789", "45.678.912/0001-30", "45678912",
                "(21) 3456-7890", "(21) 99876-5432", "eletricarapida@email.com",
                LocalDate.of(1978, 3, 10), LocalDate.now(), "Solteiro",
                "20040-020", "Rua da Eletricidade, 50", "Rio de Janeiro", "Centro", "",
                "Servicos eletricos em geral", "F", "A"));

        proprietarios.add(new Proprietario(1, "Joao Silva", "Joao da Silva",
                "111.222.333-44", "SP-111222", "", "",
                "(11) 91234-5678", "(11) 3456-7890", "joao.silva@email.com",
                LocalDate.of(1975, 6, 15), LocalDate.now(), "Casado",
                "01310-100", "Rua A, 100", "Sao Paulo", "Centro", "Apto 101",
                "Proprietario apto 101", "F", "A"));
        proprietarios.add(new Proprietario(2, "Maria Oliveira", "Maria das Gracas Oliveira",
                "555.666.777-88", "RJ-555666", "", "",
                "(21) 98765-4321", "(21) 2345-6789", "maria.oliveira@email.com",
                LocalDate.of(1982, 9, 22), LocalDate.now(), "Casado",
                "20040-020", "Rua B, 200", "Rio de Janeiro", "Copacabana", "Apto 305",
                "Proprietaria apto 305", "F", "A"));
        proprietarios.add(new Proprietario(3, "Carlos Souza", "Carlos Eduardo Souza",
                "999.888.777-66", "MG-999888", "", "",
                "(31) 99887-6543", "(31) 3456-7890", "carlos.souza@email.com",
                LocalDate.of(1968, 12, 5), LocalDate.now(), "Divorciado",
                "30140-110", "Av. Principal, 300", "Belo Horizonte", "Savassi", "Apto 802",
                "Proprietario apto 802", "F", "A"));
    }

    // --- Edificio ---

    public List<Edificio> getEdificios() {
        return Collections.unmodifiableList(edificios);
    }

    public void salvarEdificio(Edificio edificio) {
        if (edificio.getId() == 0) {
            edificio.setId(nextEdificioId++);
            edificios.add(edificio);
        } else {
            for (int i = 0; i < edificios.size(); i++) {
                if (edificios.get(i).getId() == edificio.getId()) {
                    edificios.set(i, edificio);
                    return;
                }
            }
            edificios.add(edificio);
        }
    }

    public Edificio buscarEdificioPorId(int id) {
        return edificios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    // --- Fornecedor ---

    public List<Fornecedor> getFornecedores() {
        return Collections.unmodifiableList(fornecedores);
    }

    public void salvarFornecedor(Fornecedor fornecedor) {
        if (fornecedor.getId() == 0) {
            fornecedor.setId(nextFornecedorId++);
            fornecedores.add(fornecedor);
        } else {
            for (int i = 0; i < fornecedores.size(); i++) {
                if (fornecedores.get(i).getId() == fornecedor.getId()) {
                    fornecedores.set(i, fornecedor);
                    return;
                }
            }
            fornecedores.add(fornecedor);
        }
    }

    public Fornecedor buscarFornecedorPorId(int id) {
        return fornecedores.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    // --- Proprietario ---

    public List<Proprietario> getProprietarios() {
        return Collections.unmodifiableList(proprietarios);
    }

    public void salvarProprietario(Proprietario proprietario) {
        if (proprietario.getId() == 0) {
            proprietario.setId(nextProprietarioId++);
            proprietarios.add(proprietario);
        } else {
            for (int i = 0; i < proprietarios.size(); i++) {
                if (proprietarios.get(i).getId() == proprietario.getId()) {
                    proprietarios.set(i, proprietario);
                    return;
                }
            }
            proprietarios.add(proprietario);
        }
    }

    public Proprietario buscarProprietarioPorId(int id) {
        return proprietarios.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
