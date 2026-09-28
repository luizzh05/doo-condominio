package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AreaCompartilhada;


public class AreaCompartilhadaDAO implements InterfaceDAO<AreaCompartilhada> {
    private static final String COLUMNS = "id, descricao, observacao, status";

    @Override
    public void create(AreaCompartilhada objeto) {
        String sql = "INSERT INTO area_compartilhada (descricao, observacao, status) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar area_compartilhada", ex);
        }
    }

    @Override
    public AreaCompartilhada retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM area_compartilhada WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar area_compartilhada", ex);
        }
    }

    @Override
    public List<AreaCompartilhada> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            AreaCompartilhada item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "descricao" -> "descricao";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM area_compartilhada WHERE " + coluna + " LIKE ?";
        List<AreaCompartilhada> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar area_compartilhada", ex);
        }
    }

    @Override
    public void update(AreaCompartilhada objeto) {
        String sql = "UPDATE area_compartilhada SET descricao = ?, observacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar area_compartilhada", ex);
        }
    }

    @Override
    public void delete(AreaCompartilhada objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM area_compartilhada WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir area_compartilhada", ex);
        }
    }

    private static void bind(PreparedStatement stmt, AreaCompartilhada objeto) throws SQLException {
        stmt.setString(1, objeto.getDescricao());
        stmt.setString(2, objeto.getObservacao());
        stmt.setString(3, objeto.getStatus());
    }

    private static AreaCompartilhada map(ResultSet rs) throws SQLException {
        AreaCompartilhada objeto = new AreaCompartilhada();
        objeto.setId(rs.getInt("id"));
        objeto.setDescricao(rs.getString("descricao"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        return objeto;
    }
}
