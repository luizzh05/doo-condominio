package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Reserva;
import model.AreaCompartilhadaEdificio;
import model.UnidadeCondomino;

public class ReservaDAO implements InterfaceDAO<Reserva> {
    private static final String COLUMNS = "id, data_hora_inicio, data_hora_fim, observacao, status, area_compartilhada_edificio_id, unidade_condominio_id"
            + ", (SELECT ac.descricao FROM area_compartilhada ac JOIN area_compartilhada_edificio ace ON ace.area_compartilhada_id = ac.id WHERE ace.id = reserva.area_compartilhada_edificio_id) AS descricao_area, (SELECT u.descricao FROM unidade u JOIN unidade_condomino uc ON uc.unidade_id = u.id WHERE uc.id = reserva.unidade_condominio_id) AS descricao_unidade";

    @Override
    public void create(Reserva objeto) {
        String sql = "INSERT INTO reserva (data_hora_inicio, data_hora_fim, observacao, status, area_compartilhada_edificio_id, unidade_condominio_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar reserva", ex);
        }
    }

    @Override
    public Reserva retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM reserva WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar reserva", ex);
        }
    }

    @Override
    public List<Reserva> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Reserva item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "area_compartilhada" -> "(SELECT ac.descricao FROM area_compartilhada ac JOIN area_compartilhada_edificio ace ON ace.area_compartilhada_id = ac.id WHERE ace.id = reserva.area_compartilhada_edificio_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM reserva WHERE " + coluna + " LIKE ?";
        List<Reserva> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar reserva", ex);
        }
    }

    @Override
    public void update(Reserva objeto) {
        String sql = "UPDATE reserva SET data_hora_inicio = ?, data_hora_fim = ?, observacao = ?, status = ?, area_compartilhada_edificio_id = ?, unidade_condominio_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(7, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar reserva", ex);
        }
    }

    @Override
    public void delete(Reserva objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM reserva WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir reserva", ex);
        }
    }

    private static void bind(PreparedStatement stmt, Reserva objeto) throws SQLException {
        stmt.setObject(1, objeto.getDataHoraInicio());
        stmt.setObject(2, objeto.getDataHoraFim());
        stmt.setString(3, objeto.getObservacao());
        stmt.setString(4, objeto.getStatus());
        if (objeto.getAreaCompartilhadaEdificio() == null || objeto.getAreaCompartilhadaEdificio().getId() <= 0) throw new IllegalArgumentException("AreaCompartilhadaEdificio é obrigatório");
        stmt.setInt(5, objeto.getAreaCompartilhadaEdificio().getId());
        if (objeto.getUnidadeCondomino() == null || objeto.getUnidadeCondomino().getId() <= 0) throw new IllegalArgumentException("UnidadeCondomino é obrigatório");
        stmt.setInt(6, objeto.getUnidadeCondomino().getId());
    }

    private static Reserva map(ResultSet rs) throws SQLException {
        Reserva objeto = new Reserva();
        objeto.setId(rs.getInt("id"));
        objeto.setDataHoraInicio(rs.getObject("data_hora_inicio", java.time.LocalDateTime.class));
        objeto.setDataHoraFim(rs.getObject("data_hora_fim", java.time.LocalDateTime.class));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int areaCompartilhadaEdificioId = rs.getInt("area_compartilhada_edificio_id");
        if (!rs.wasNull()) {
            AreaCompartilhadaEdificio areaCompartilhadaEdificio = new AreaCompartilhadaEdificio();
            areaCompartilhadaEdificio.setId(areaCompartilhadaEdificioId);
            objeto.setAreaCompartilhadaEdificio(areaCompartilhadaEdificio);
        }
        int unidadeCondominoId = rs.getInt("unidade_condominio_id");
        if (!rs.wasNull()) {
            UnidadeCondomino unidadeCondomino = new UnidadeCondomino();
            unidadeCondomino.setId(unidadeCondominoId);
            objeto.setUnidadeCondomino(unidadeCondomino);
        }
        if (objeto.getAreaCompartilhadaEdificio() != null) {
            model.AreaCompartilhada areaVinculada = new model.AreaCompartilhada();
            areaVinculada.setDescricao(rs.getString("descricao_area"));
            objeto.getAreaCompartilhadaEdificio().setAreaCompartilhada(areaVinculada);
        }
        if (objeto.getUnidadeCondomino() != null) {
            model.Unidade unidadeVinculada = new model.Unidade();
            unidadeVinculada.setDescricao(rs.getString("descricao_unidade"));
            objeto.getUnidadeCondomino().setUnidade(unidadeVinculada);
        }
        return objeto;
    }
}
