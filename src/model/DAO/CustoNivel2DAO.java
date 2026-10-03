package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CustoNivel2;
import model.CustoNivel1;

public class CustoNivel2DAO implements InterfaceDAO<CustoNivel2> {
    private static final String COLUMNS = "id, descricao, observacao, status, custo_nivel1_id, "
            + "(SELECT c.descricao FROM custo_nivel1 c WHERE c.id = custo_nivel2.custo_nivel1_id) "
            + "AS descricao_custo_nivel1";

    @Override
    public void create(CustoNivel2 objeto) {
        String sql = "INSERT INTO custo_nivel2 (descricao, observacao, status, custo_nivel1_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar custo_nivel2", ex);
        }
    }

    @Override
    public CustoNivel2 retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM custo_nivel2 WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar custo_nivel2", ex);
        }
    }

    @Override
    public List<CustoNivel2> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            CustoNivel2 item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "descricao" -> "descricao";
            case "custo_nivel1" -> "(SELECT c.descricao FROM custo_nivel1 c WHERE c.id = custo_nivel2.custo_nivel1_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM custo_nivel2 WHERE " + coluna + " LIKE ?";
        List<CustoNivel2> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar custo_nivel2", ex);
        }
    }

    @Override
    public void update(CustoNivel2 objeto) {
        String sql = "UPDATE custo_nivel2 SET descricao = ?, observacao = ?, status = ?, custo_nivel1_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(5, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar custo_nivel2", ex);
        }
    }

    @Override
    public void delete(CustoNivel2 objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM custo_nivel2 WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir custo_nivel2", ex);
        }
    }

    private static void bind(PreparedStatement stmt, CustoNivel2 objeto) throws SQLException {
        stmt.setString(1, objeto.getDescricao());
        stmt.setString(2, objeto.getObservacao());
        stmt.setString(3, objeto.getStatus());
        if (objeto.getCustoNivel1() == null || objeto.getCustoNivel1().getId() <= 0) throw new IllegalArgumentException("CustoNivel1 é obrigatório");
        stmt.setInt(4, objeto.getCustoNivel1().getId());
    }

    private static CustoNivel2 map(ResultSet rs) throws SQLException {
        CustoNivel2 objeto = new CustoNivel2();
        objeto.setId(rs.getInt("id"));
        objeto.setDescricao(rs.getString("descricao"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int custoNivel1Id = rs.getInt("custo_nivel1_id");
        if (!rs.wasNull()) {
            CustoNivel1 custoNivel1 = new CustoNivel1();
            custoNivel1.setId(custoNivel1Id);
            custoNivel1.setDescricao(rs.getString("descricao_custo_nivel1"));
            objeto.setCustoNivel1(custoNivel1);
        }
        return objeto;
    }
}
