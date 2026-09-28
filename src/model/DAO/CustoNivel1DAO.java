package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CustoNivel1;


public class CustoNivel1DAO implements InterfaceDAO<CustoNivel1> {
    private static final String COLUMNS = "id, descricao, tipo_cc, observacao, status";

    @Override
    public void create(CustoNivel1 objeto) {
        String sql = "INSERT INTO custo_nivel1 (descricao, tipo_cc, observacao, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar custo_nivel1", ex);
        }
    }

    @Override
    public CustoNivel1 retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM custo_nivel1 WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar custo_nivel1", ex);
        }
    }

    @Override
    public List<CustoNivel1> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            CustoNivel1 item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "descricao" -> "descricao";
            case "tipo_cc" -> "tipo_cc";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM custo_nivel1 WHERE " + coluna + " LIKE ?";
        List<CustoNivel1> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar custo_nivel1", ex);
        }
    }

    @Override
    public void update(CustoNivel1 objeto) {
        String sql = "UPDATE custo_nivel1 SET descricao = ?, tipo_cc = ?, observacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(5, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar custo_nivel1", ex);
        }
    }

    @Override
    public void delete(CustoNivel1 objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM custo_nivel1 WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir custo_nivel1", ex);
        }
    }

    private static void bind(PreparedStatement stmt, CustoNivel1 objeto) throws SQLException {
        stmt.setString(1, objeto.getDescricao());
        stmt.setString(2, objeto.getTipoCc());
        stmt.setString(3, objeto.getObservacao());
        stmt.setString(4, objeto.getStatus());
    }

    private static CustoNivel1 map(ResultSet rs) throws SQLException {
        CustoNivel1 objeto = new CustoNivel1();
        objeto.setId(rs.getInt("id"));
        objeto.setDescricao(rs.getString("descricao"));
        objeto.setTipoCc(rs.getString("tipo_cc"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        return objeto;
    }
}
