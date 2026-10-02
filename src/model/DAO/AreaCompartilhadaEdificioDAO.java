package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AreaCompartilhadaEdificio;
import model.AreaCompartilhada;
import model.Edificio;

public class AreaCompartilhadaEdificioDAO implements InterfaceDAO<AreaCompartilhadaEdificio> {
    private static final String COLUMNS = "id, observacao, status, area_compartilhada_id, edificio_id";

    @Override
    public void create(AreaCompartilhadaEdificio objeto) {
        String sql = "INSERT INTO area_compartilhada_edificio (observacao, status, area_compartilhada_id, edificio_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar area_compartilhada_edificio", ex);
        }
    }

    @Override
    public AreaCompartilhadaEdificio retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM area_compartilhada_edificio WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar area_compartilhada_edificio", ex);
        }
    }

    @Override
    public List<AreaCompartilhadaEdificio> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            AreaCompartilhadaEdificio item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "area_compartilhada" -> "(SELECT ac.descricao FROM area_compartilhada ac WHERE ac.id = area_compartilhada_edificio.area_compartilhada_id)";
            case "edificio" -> "(SELECT e.nome FROM edificio e WHERE e.id = area_compartilhada_edificio.edificio_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM area_compartilhada_edificio WHERE " + coluna + " LIKE ?";
        List<AreaCompartilhadaEdificio> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar area_compartilhada_edificio", ex);
        }
    }

    @Override
    public void update(AreaCompartilhadaEdificio objeto) {
        String sql = "UPDATE area_compartilhada_edificio SET observacao = ?, status = ?, area_compartilhada_id = ?, edificio_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(5, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar area_compartilhada_edificio", ex);
        }
    }

    @Override
    public void delete(AreaCompartilhadaEdificio objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM area_compartilhada_edificio WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir area_compartilhada_edificio", ex);
        }
    }

    private static void bind(PreparedStatement stmt, AreaCompartilhadaEdificio objeto) throws SQLException {
        stmt.setString(1, objeto.getObservacao());
        stmt.setString(2, objeto.getStatus());
        if (objeto.getAreaCompartilhada() == null || objeto.getAreaCompartilhada().getId() <= 0) throw new IllegalArgumentException("AreaCompartilhada é obrigatório");
        stmt.setInt(3, objeto.getAreaCompartilhada().getId());
        if (objeto.getEdificio() == null || objeto.getEdificio().getId() <= 0) throw new IllegalArgumentException("Edificio é obrigatório");
        stmt.setInt(4, objeto.getEdificio().getId());
    }

    private static AreaCompartilhadaEdificio map(ResultSet rs) throws SQLException {
        AreaCompartilhadaEdificio objeto = new AreaCompartilhadaEdificio();
        objeto.setId(rs.getInt("id"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int areaCompartilhadaId = rs.getInt("area_compartilhada_id");
        if (!rs.wasNull()) {
            AreaCompartilhada areaCompartilhada = new AreaCompartilhada();
            areaCompartilhada.setId(areaCompartilhadaId);
            objeto.setAreaCompartilhada(areaCompartilhada);
        }
        int edificioId = rs.getInt("edificio_id");
        if (!rs.wasNull()) {
            Edificio edificio = new Edificio();
            edificio.setId(edificioId);
            objeto.setEdificio(edificio);
        }
        return objeto;
    }
}
