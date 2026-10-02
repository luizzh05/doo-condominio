package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;

public class FornecedorDAO implements InterfaceDAO<Fornecedor> {
    private static final String COLUMNS = "id, nome_fantasia, razao_social, cpf, rg, cnpj, inscricao_estadual, fone1, fone2, email, data_nascimento, data_cadastro, estado_civil, cep, logradouro, cidade, bairro, complemento, observacao, status";

    @Override
    public void create(Fornecedor objeto) {
        String sql = "INSERT INTO fornecedor (nome_fantasia, razao_social, cpf, rg, cnpj, inscricao_estadual, fone1, fone2, email, data_nascimento, data_cadastro, estado_civil, cep, logradouro, cidade, bairro, complemento, observacao, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar fornecedor", ex);
        }
    }

    @Override
    public Fornecedor retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM fornecedor WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar fornecedor", ex);
        }
    }

    @Override
    public List<Fornecedor> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Fornecedor item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "nome_fantasia" -> "nome_fantasia";
            case "cpf" -> "cpf";
            case "cnpj" -> "cnpj";
            case "razao_social" -> "razao_social";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM fornecedor WHERE " + coluna + " LIKE ?";
        List<Fornecedor> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar fornecedor", ex);
        }
    }

    @Override
    public void update(Fornecedor objeto) {
        String sql = "UPDATE fornecedor SET nome_fantasia = ?, razao_social = ?, cpf = ?, rg = ?, cnpj = ?, inscricao_estadual = ?, fone1 = ?, fone2 = ?, email = ?, data_nascimento = ?, data_cadastro = ?, estado_civil = ?, cep = ?, logradouro = ?, cidade = ?, bairro = ?, complemento = ?, observacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(20, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar fornecedor", ex);
        }
    }

    @Override
    public void delete(Fornecedor objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir fornecedor", ex);
        }
    }

    private static void bind(PreparedStatement stmt, Fornecedor objeto) throws SQLException {
        stmt.setString(1, objeto.getNomeFantasia());
        stmt.setString(2, objeto.getRazaoSocial());
        stmt.setString(3, objeto.getCpf());
        stmt.setString(4, objeto.getRg());
        stmt.setString(5, objeto.getCnpj());
        stmt.setString(6, objeto.getInscricaoEstadual());
        stmt.setString(7, objeto.getFone1());
        stmt.setString(8, objeto.getFone2());
        stmt.setString(9, objeto.getEmail());
        stmt.setObject(10, objeto.getDataNascimento());
        stmt.setObject(11, objeto.getDataCadastro());
        stmt.setString(12, objeto.getEstadoCivil());
        stmt.setString(13, objeto.getCep());
        stmt.setString(14, objeto.getLogradouro());
        stmt.setString(15, objeto.getCidade());
        stmt.setString(16, objeto.getBairro());
        stmt.setString(17, objeto.getComplemento());
        stmt.setString(18, objeto.getObservacao());
        stmt.setString(19, objeto.getStatus());
    }

    private static Fornecedor map(ResultSet rs) throws SQLException {
        Fornecedor objeto = new Fornecedor();
        objeto.setId(rs.getInt("id"));
        objeto.setNomeFantasia(rs.getString("nome_fantasia"));
        objeto.setRazaoSocial(rs.getString("razao_social"));
        objeto.setCpf(rs.getString("cpf"));
        objeto.setRg(rs.getString("rg"));
        objeto.setCnpj(rs.getString("cnpj"));
        objeto.setInscricaoEstadual(rs.getString("inscricao_estadual"));
        objeto.setFone1(rs.getString("fone1"));
        objeto.setFone2(rs.getString("fone2"));
        objeto.setEmail(rs.getString("email"));
        objeto.setDataNascimento(rs.getObject("data_nascimento", java.time.LocalDate.class));
        objeto.setDataCadastro(rs.getObject("data_cadastro", java.time.LocalDate.class));
        objeto.setEstadoCivil(rs.getString("estado_civil"));
        objeto.setCep(rs.getString("cep"));
        objeto.setLogradouro(rs.getString("logradouro"));
        objeto.setCidade(rs.getString("cidade"));
        objeto.setBairro(rs.getString("bairro"));
        objeto.setComplemento(rs.getString("complemento"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        objeto.setTipoPessoa("Fornecedor");
        return objeto;
    }
}
