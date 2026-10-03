package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.UnidadeCondomino;
import model.Proprietario;
import model.Unidade;

public class UnidadeCondominoDAO implements InterfaceDAO<UnidadeCondomino> {
    private static final String COLUMNS = "id, data_aquisicao, data_venda, observacao, status, proprietario_id, unidade_id"
            + ", (SELECT v.descricao FROM unidade v WHERE v.id = unidade_condomino.unidade_id) AS nome_unidade, (SELECT v.nome_fantasia FROM proprietario v WHERE v.id = unidade_condomino.proprietario_id) AS nome_proprietario";

    @Override
    public void create(UnidadeCondomino objeto) {
        String sql = "INSERT INTO unidade_condomino (data_aquisicao, data_venda, observacao, status, proprietario_id, unidade_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar unidade_condomino", ex);
        }
    }

    @Override
    public UnidadeCondomino retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM unidade_condomino WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar unidade_condomino", ex);
        }
    }

    @Override
    public List<UnidadeCondomino> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            UnidadeCondomino item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "unidade" -> "(SELECT u.descricao FROM unidade u WHERE u.id = unidade_condomino.unidade_id)";
            case "proprietario" -> "(SELECT p.nome_fantasia FROM proprietario p WHERE p.id = unidade_condomino.proprietario_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM unidade_condomino WHERE " + coluna + " LIKE ?";
        List<UnidadeCondomino> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar unidade_condomino", ex);
        }
    }

    @Override
    public void update(UnidadeCondomino objeto) {
        String sql = "UPDATE unidade_condomino SET data_aquisicao = ?, data_venda = ?, observacao = ?, status = ?, proprietario_id = ?, unidade_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(7, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar unidade_condomino", ex);
        }
    }

    @Override
    public void delete(UnidadeCondomino objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM unidade_condomino WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir unidade_condomino", ex);
        }
    }

    private static void bind(PreparedStatement stmt, UnidadeCondomino objeto) throws SQLException {
        stmt.setObject(1, objeto.getDataAquisicao());
        stmt.setObject(2, objeto.getDataVenda());
        stmt.setString(3, objeto.getObservacao());
        stmt.setString(4, objeto.getStatus());
        if (objeto.getProprietario() == null || objeto.getProprietario().getId() <= 0) throw new IllegalArgumentException("Proprietario é obrigatório");
        stmt.setInt(5, objeto.getProprietario().getId());
        if (objeto.getUnidade() == null || objeto.getUnidade().getId() <= 0) throw new IllegalArgumentException("Unidade é obrigatório");
        stmt.setInt(6, objeto.getUnidade().getId());
    }

    private static UnidadeCondomino map(ResultSet rs) throws SQLException {
        UnidadeCondomino objeto = new UnidadeCondomino();
        objeto.setId(rs.getInt("id"));
        objeto.setDataAquisicao(rs.getObject("data_aquisicao", java.time.LocalDate.class));
        objeto.setDataVenda(rs.getObject("data_venda", java.time.LocalDate.class));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int proprietarioId = rs.getInt("proprietario_id");
        if (!rs.wasNull()) {
            Proprietario proprietario = new Proprietario();
            proprietario.setId(proprietarioId);
            objeto.setProprietario(proprietario);
        }
        int unidadeId = rs.getInt("unidade_id");
        if (!rs.wasNull()) {
            Unidade unidade = new Unidade();
            unidade.setId(unidadeId);
            objeto.setUnidade(unidade);
        }
        if (objeto.getUnidade() != null) objeto.getUnidade().setDescricao(rs.getString("nome_unidade"));
        if (objeto.getProprietario() != null) objeto.getProprietario().setNomeFantasia(rs.getString("nome_proprietario"));
        return objeto;
    }
}
