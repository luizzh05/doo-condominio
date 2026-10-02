package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.FuncaoMandato;
import model.Edificio;
import model.Proprietario;
import model.SindicoProfissional;

public class FuncaoMandatoDAO implements InterfaceDAO<FuncaoMandato> {
    private static final String COLUMNS = "id, funcao, data_inicio, data_fim, observacao, status, edificio_id, proprietario_id, sindico_profissional_id";

    @Override
    public void create(FuncaoMandato objeto) {
        String sql = "INSERT INTO funcao_mandato (funcao, data_inicio, data_fim, observacao, status, edificio_id, proprietario_id, sindico_profissional_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar funcao_mandato", ex);
        }
    }

    @Override
    public FuncaoMandato retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM funcao_mandato WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar funcao_mandato", ex);
        }
    }

    @Override
    public List<FuncaoMandato> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            FuncaoMandato item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "funcao" -> "funcao";
            case "sindico" -> "(SELECT s.nome_fantasia FROM sindico_profissional s WHERE s.id = funcao_mandato.sindico_profissional_id)";
            case "edificio" -> "(SELECT e.nome FROM edificio e WHERE e.id = funcao_mandato.edificio_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM funcao_mandato WHERE " + coluna + " LIKE ?";
        List<FuncaoMandato> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar funcao_mandato", ex);
        }
    }

    @Override
    public void update(FuncaoMandato objeto) {
        String sql = "UPDATE funcao_mandato SET funcao = ?, data_inicio = ?, data_fim = ?, observacao = ?, status = ?, edificio_id = ?, proprietario_id = ?, sindico_profissional_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(9, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar funcao_mandato", ex);
        }
    }

    @Override
    public void delete(FuncaoMandato objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM funcao_mandato WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir funcao_mandato", ex);
        }
    }

    private static void bind(PreparedStatement stmt, FuncaoMandato objeto) throws SQLException {
        stmt.setString(1, objeto.getFuncao());
        stmt.setObject(2, objeto.getDataInicio());
        stmt.setObject(3, objeto.getDataFim());
        stmt.setString(4, objeto.getObservacao());
        stmt.setString(5, objeto.getStatus());
        if (objeto.getEdificio() == null || objeto.getEdificio().getId() <= 0) throw new IllegalArgumentException("Edificio é obrigatório");
        stmt.setInt(6, objeto.getEdificio().getId());
        stmt.setObject(7, objeto.getProprietario() == null ? null : objeto.getProprietario().getId());
        stmt.setObject(8, objeto.getSindicoProfissional() == null ? null : objeto.getSindicoProfissional().getId());
    }

    private static FuncaoMandato map(ResultSet rs) throws SQLException {
        FuncaoMandato objeto = new FuncaoMandato();
        objeto.setId(rs.getInt("id"));
        objeto.setFuncao(rs.getString("funcao"));
        objeto.setDataInicio(rs.getObject("data_inicio", java.time.LocalDate.class));
        objeto.setDataFim(rs.getObject("data_fim", java.time.LocalDate.class));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int edificioId = rs.getInt("edificio_id");
        if (!rs.wasNull()) {
            Edificio edificio = new Edificio();
            edificio.setId(edificioId);
            objeto.setEdificio(edificio);
        }
        int proprietarioId = rs.getInt("proprietario_id");
        if (!rs.wasNull()) {
            Proprietario proprietario = new Proprietario();
            proprietario.setId(proprietarioId);
            objeto.setProprietario(proprietario);
        }
        int sindicoProfissionalId = rs.getInt("sindico_profissional_id");
        if (!rs.wasNull()) {
            SindicoProfissional sindicoProfissional = new SindicoProfissional();
            sindicoProfissional.setId(sindicoProfissionalId);
            objeto.setSindicoProfissional(sindicoProfissional);
        }
        return objeto;
    }
}
