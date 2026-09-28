package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.SindicoProfissional;

public class SindicoProfissionalDAO implements InterfaceDAO<SindicoProfissional> {
    private static final String COLUMNS = "id, cra, nome_fantasia, razao_social, cpf, rg, cnpj, inscricao_estadual, fone1, fone2, email, data_nascimento, data_cadastro, estado_civil, cep, logradouro, cidade, bairro, complemento, observacao, status";

    @Override
    public void create(SindicoProfissional objeto) {
        String sql = "INSERT INTO sindico_profissional (cra, nome_fantasia, razao_social, cpf, rg, cnpj, inscricao_estadual, fone1, fone2, email, data_nascimento, data_cadastro, estado_civil, cep, logradouro, cidade, bairro, complemento, observacao, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar sindico_profissional", ex);
        }
    }

    @Override
    public SindicoProfissional retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM sindico_profissional WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar sindico_profissional", ex);
        }
    }

    @Override
    public List<SindicoProfissional> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            SindicoProfissional item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "cra" -> "cra";
            case "nome_fantasia" -> "nome_fantasia";
            case "cpf" -> "cpf";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM sindico_profissional WHERE " + coluna + " LIKE ?";
        List<SindicoProfissional> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar sindico_profissional", ex);
        }
    }

    @Override
    public void update(SindicoProfissional objeto) {
        String sql = "UPDATE sindico_profissional SET cra = ?, nome_fantasia = ?, razao_social = ?, cpf = ?, rg = ?, cnpj = ?, inscricao_estadual = ?, fone1 = ?, fone2 = ?, email = ?, data_nascimento = ?, data_cadastro = ?, estado_civil = ?, cep = ?, logradouro = ?, cidade = ?, bairro = ?, complemento = ?, observacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(21, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar sindico_profissional", ex);
        }
    }

    @Override
    public void delete(SindicoProfissional objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM sindico_profissional WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir sindico_profissional", ex);
        }
    }

    private static void bind(PreparedStatement stmt, SindicoProfissional objeto) throws SQLException {
        stmt.setString(1, objeto.getCra());
        stmt.setString(2, objeto.getNomeFantasia());
        stmt.setString(3, objeto.getRazaoSocial());
        stmt.setString(4, objeto.getCpf());
        stmt.setString(5, objeto.getRg());
        stmt.setString(6, objeto.getCnpj());
        stmt.setString(7, objeto.getInscricaoEstadual());
        stmt.setString(8, objeto.getFone1());
        stmt.setString(9, objeto.getFone2());
        stmt.setString(10, objeto.getEmail());
        stmt.setObject(11, objeto.getDataNascimento());
        stmt.setObject(12, objeto.getDataCadastro());
        stmt.setString(13, objeto.getEstadoCivil());
        stmt.setString(14, objeto.getCep());
        stmt.setString(15, objeto.getLogradouro());
        stmt.setString(16, objeto.getCidade());
        stmt.setString(17, objeto.getBairro());
        stmt.setString(18, objeto.getComplemento());
        stmt.setString(19, objeto.getObservacao());
        stmt.setString(20, objeto.getStatus());
    }

    private static SindicoProfissional map(ResultSet rs) throws SQLException {
        SindicoProfissional objeto = new SindicoProfissional();
        objeto.setId(rs.getInt("id"));
        objeto.setCra(rs.getString("cra"));
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
        objeto.setTipoPessoa("SindicoProfissional");
        return objeto;
    }
}
