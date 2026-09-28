package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Proprietario;

public class ProprietarioDAO implements InterfaceDAO<Proprietario> {
    private static final String FIELDS = "nome_fantasia, razao_social, cpf, rg, cnpj, inscricao_estadual, "
            + "fone1, fone2, email, data_nascimento, data_cadastro, estado_civil, cep, logradouro, "
            + "cidade, bairro, complemento, observacao, status";
    private static final String VALUES = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

    @Override
    public void create(Proprietario objeto) {
        String sql = "INSERT INTO proprietario (" + FIELDS + ") VALUES (" + VALUES + ")";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fill(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar proprietário", ex);
        }
    }

    @Override
    public Proprietario retrieve(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id, " + FIELDS + " FROM proprietario WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar proprietário", ex);
        }
    }

    @Override
    public List<Proprietario> retrieve(String parametro, String valor) {
        String column = switch (parametro) {
            case "nome_fantasia" -> "nome_fantasia";
            case "cpf" -> "cpf";
            case "cnpj" -> "cnpj";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        List<Proprietario> result = new ArrayList<>();
        String sql = "SELECT id, " + FIELDS + " FROM proprietario WHERE " + column + " LIKE ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) result.add(map(rs));
            }
            return result;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar proprietários", ex);
        }
    }

    @Override
    public void update(Proprietario objeto) {
        String sql = "UPDATE proprietario SET nome_fantasia=?, razao_social=?, cpf=?, rg=?, cnpj=?, "
                + "inscricao_estadual=?, fone1=?, fone2=?, email=?, data_nascimento=?, data_cadastro=?, "
                + "estado_civil=?, cep=?, logradouro=?, cidade=?, bairro=?, complemento=?, observacao=?, status=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            fill(stmt, objeto);
            stmt.setInt(20, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar proprietário", ex);
        }
    }

    @Override
    public void delete(Proprietario objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM proprietario WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir proprietário", ex);
        }
    }

    private static void fill(PreparedStatement stmt, Proprietario p) throws SQLException {
        stmt.setString(1, p.getNomeFantasia());
        stmt.setString(2, p.getRazaoSocial());
        stmt.setString(3, p.getCpf());
        stmt.setString(4, p.getRg());
        stmt.setString(5, p.getCnpj());
        stmt.setString(6, p.getInscricaoEstadual());
        stmt.setString(7, p.getFone1());
        stmt.setString(8, p.getFone2());
        stmt.setString(9, p.getEmail());
        stmt.setObject(10, p.getDataNascimento());
        stmt.setObject(11, p.getDataCadastro());
        stmt.setString(12, p.getEstadoCivil());
        stmt.setString(13, p.getCep());
        stmt.setString(14, p.getLogradouro());
        stmt.setString(15, p.getCidade());
        stmt.setString(16, p.getBairro());
        stmt.setString(17, p.getComplemento());
        stmt.setString(18, p.getObservacao());
        stmt.setString(19, p.getStatus());
    }

    private static Proprietario map(ResultSet rs) throws SQLException {
        Proprietario p = new Proprietario();
        p.setId(rs.getInt("id"));
        p.setNomeFantasia(rs.getString("nome_fantasia"));
        p.setRazaoSocial(rs.getString("razao_social"));
        p.setCpf(rs.getString("cpf"));
        p.setRg(rs.getString("rg"));
        p.setCnpj(rs.getString("cnpj"));
        p.setInscricaoEstadual(rs.getString("inscricao_estadual"));
        p.setFone1(rs.getString("fone1"));
        p.setFone2(rs.getString("fone2"));
        p.setEmail(rs.getString("email"));
        p.setDataNascimento(rs.getObject("data_nascimento", java.time.LocalDate.class));
        p.setDataCadastro(rs.getObject("data_cadastro", java.time.LocalDate.class));
        p.setEstadoCivil(rs.getString("estado_civil"));
        p.setCep(rs.getString("cep"));
        p.setLogradouro(rs.getString("logradouro"));
        p.setCidade(rs.getString("cidade"));
        p.setBairro(rs.getString("bairro"));
        p.setComplemento(rs.getString("complemento"));
        p.setObservacao(rs.getString("observacao"));
        p.setStatus(rs.getString("status"));
        p.setTipoPessoa("Proprietario");
        return p;
    }
}
