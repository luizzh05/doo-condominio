package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Edificio;
import model.Unidade;

public class UnidadeDAO implements InterfaceDAO<Unidade> {
    private static final String COLUMNS = "id, descricao, metragem_total, metragem_individual, "
            + "tipo_unidade, observacao, status, edificio_id"
            + ", (SELECT v.nome FROM edificio v WHERE v.id = unidade.edificio_id) AS nome_edificio";

    @Override
    public void create(Unidade objeto) {
        String sql = "INSERT INTO unidade (descricao, metragem_total, metragem_individual, "
                + "tipo_unidade, observacao, status, edificio_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fill(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar unidade", ex);
        }
    }

    @Override
    public Unidade retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM unidade WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar unidade", ex);
        }
    }

    @Override
    public List<Unidade> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Unidade item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String column = switch (parametro) {
            case "descricao" -> "descricao";
            case "tipo_unidade" -> "tipo_unidade";
            case "edificio" -> "(SELECT e.nome FROM edificio e WHERE e.id = unidade.edificio_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM unidade WHERE " + column + " LIKE ?";
        List<Unidade> result = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) result.add(map(rs));
            }
            return result;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar unidades", ex);
        }
    }

    @Override
    public void update(Unidade objeto) {
        String sql = "UPDATE unidade SET descricao = ?, metragem_total = ?, metragem_individual = ?, "
                + "tipo_unidade = ?, observacao = ?, status = ?, edificio_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            fill(stmt, objeto);
            stmt.setInt(8, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar unidade", ex);
        }
    }

    @Override
    public void delete(Unidade objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM unidade WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir unidade", ex);
        }
    }

    private static void fill(PreparedStatement stmt, Unidade objeto) throws SQLException {
        stmt.setString(1, objeto.getDescricao());
        stmt.setDouble(2, objeto.getMetragemTotal());
        stmt.setDouble(3, objeto.getMetragemIndividual());
        stmt.setString(4, objeto.getTipoUnidade());
        stmt.setString(5, objeto.getObservacao());
        stmt.setString(6, objeto.getStatus());
        if (objeto.getEdificio() == null || objeto.getEdificio().getId() <= 0) throw new IllegalArgumentException("Edifício é obrigatório");
        stmt.setInt(7, objeto.getEdificio().getId());
    }

    private static Unidade map(ResultSet rs) throws SQLException {
        Unidade unidade = new Unidade();
        unidade.setId(rs.getInt("id"));
        unidade.setDescricao(rs.getString("descricao"));
        unidade.setMetragemTotal(rs.getDouble("metragem_total"));
        unidade.setMetragemIndividual(rs.getDouble("metragem_individual"));
        unidade.setTipoUnidade(rs.getString("tipo_unidade"));
        unidade.setObservacao(rs.getString("observacao"));
        unidade.setStatus(rs.getString("status"));
        Edificio edificio = new Edificio();
        edificio.setId(rs.getInt("edificio_id"));
        unidade.setEdificio(edificio);
        if (unidade.getEdificio() != null) unidade.getEdificio().setNome(rs.getString("nome_edificio"));
        return unidade;
    }
}
