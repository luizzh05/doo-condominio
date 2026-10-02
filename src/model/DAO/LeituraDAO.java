package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Leitura;
import model.Unidade;
import model.UnidadeCondomino;

public class LeituraDAO implements InterfaceDAO<Leitura> {
    private static final String COLUMNS = "id, data_leitura, mes_referencia, ano_referencia, "
            + "medicao_anterior, medicao_atual, tipo, observacao, status, unidade_condomino_id, "
            + "(SELECT uc.unidade_id FROM unidade_condomino uc "
            + "WHERE uc.id = leitura.unidade_condomino_id) AS unidade_id";

    @Override
    public void create(Leitura objeto) {
        String sql = "INSERT INTO leitura (data_leitura, mes_referencia, ano_referencia, medicao_anterior, medicao_atual, tipo, observacao, status, unidade_condomino_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar leitura", ex);
        }
    }

    @Override
    public Leitura retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM leitura WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar leitura", ex);
        }
    }

    @Override
    public List<Leitura> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Leitura item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "tipo" -> "tipo";
            case "unidade" -> "(SELECT u.descricao FROM unidade u JOIN unidade_condomino uc ON uc.unidade_id = u.id WHERE uc.id = leitura.unidade_condomino_id)";
            case "mes_referencia" -> "mes_referencia";
            case "ano_referencia" -> "ano_referencia";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        boolean referencia = "mes_referencia".equals(coluna) || "ano_referencia".equals(coluna);
        Integer numeroReferencia = referencia ? Integer.valueOf(valor) : null;
        String sql = "SELECT " + COLUMNS + " FROM leitura WHERE " + coluna
                + (referencia ? " = ?" : " LIKE ?");
        List<Leitura> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (referencia) {
                stmt.setInt(1, numeroReferencia);
            } else {
                stmt.setString(1, "%" + valor + "%");
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar leitura", ex);
        }
    }

    @Override
    public void update(Leitura objeto) {
        String sql = "UPDATE leitura SET data_leitura = ?, mes_referencia = ?, ano_referencia = ?, medicao_anterior = ?, medicao_atual = ?, tipo = ?, observacao = ?, status = ?, unidade_condomino_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(10, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar leitura", ex);
        }
    }

    @Override
    public void delete(Leitura objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM leitura WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir leitura", ex);
        }
    }

    private static void bind(PreparedStatement stmt, Leitura objeto) throws SQLException {
        stmt.setObject(1, objeto.getDataLeitura());
        stmt.setInt(2, objeto.getMesReferencia());
        stmt.setInt(3, objeto.getAnoReferencia());
        stmt.setDouble(4, objeto.getMedicaoAnterior());
        stmt.setDouble(5, objeto.getMedicaoAtual());
        stmt.setString(6, objeto.getTipo());
        stmt.setString(7, objeto.getObservacao());
        stmt.setString(8, objeto.getStatus());
        if (objeto.getUnidadeCondomino() == null || objeto.getUnidadeCondomino().getId() <= 0) throw new IllegalArgumentException("UnidadeCondomino é obrigatório");
        stmt.setInt(9, objeto.getUnidadeCondomino().getId());
    }

    private static Leitura map(ResultSet rs) throws SQLException {
        Leitura objeto = new Leitura();
        objeto.setId(rs.getInt("id"));
        objeto.setDataLeitura(rs.getObject("data_leitura", java.time.LocalDate.class));
        objeto.setMesReferencia(rs.getInt("mes_referencia"));
        objeto.setAnoReferencia(rs.getInt("ano_referencia"));
        objeto.setMedicaoAnterior(rs.getDouble("medicao_anterior"));
        objeto.setMedicaoAtual(rs.getDouble("medicao_atual"));
        objeto.setTipo(rs.getString("tipo"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int unidadeCondominoId = rs.getInt("unidade_condomino_id");
        if (!rs.wasNull()) {
            UnidadeCondomino unidadeCondomino = new UnidadeCondomino();
            unidadeCondomino.setId(unidadeCondominoId);
            objeto.setUnidadeCondomino(unidadeCondomino);
        }
        int unidadeId = rs.getInt("unidade_id");
        if (!rs.wasNull()) {
            Unidade unidade = new Unidade();
            unidade.setId(unidadeId);
            objeto.setUnidade(unidade);
        }
        return objeto;
    }
}
