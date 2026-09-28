package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Edificio;


public class EdificioDAO implements InterfaceDAO<Edificio> {
    private static final String COLUMNS = "id, nome, quantidade_andares, quantidade_unidades, cnpj, ano_lancamento, area_total, cep, logradouro, cidade, bairro, complemento, numero_unidade_agua, numero_unidade_gas, formula_calculo, observacao, status";

    @Override
    public void create(Edificio objeto) {
        String sql = "INSERT INTO edificio (nome, quantidade_andares, quantidade_unidades, cnpj, ano_lancamento, area_total, cep, logradouro, cidade, bairro, complemento, numero_unidade_agua, numero_unidade_gas, formula_calculo, observacao, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar edificio", ex);
        }
    }

    @Override
    public Edificio retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM edificio WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar edificio", ex);
        }
    }

    @Override
    public List<Edificio> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Edificio item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "nome" -> "nome";
            case "cnpj" -> "cnpj";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM edificio WHERE " + coluna + " LIKE ?";
        List<Edificio> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar edificio", ex);
        }
    }

    @Override
    public void update(Edificio objeto) {
        String sql = "UPDATE edificio SET nome = ?, quantidade_andares = ?, quantidade_unidades = ?, cnpj = ?, ano_lancamento = ?, area_total = ?, cep = ?, logradouro = ?, cidade = ?, bairro = ?, complemento = ?, numero_unidade_agua = ?, numero_unidade_gas = ?, formula_calculo = ?, observacao = ?, status = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(17, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar edificio", ex);
        }
    }

    @Override
    public void delete(Edificio objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM edificio WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir edificio", ex);
        }
    }

    private static void bind(PreparedStatement stmt, Edificio objeto) throws SQLException {
        stmt.setString(1, objeto.getNome());
        stmt.setInt(2, objeto.getQuantidadeAndares());
        stmt.setInt(3, objeto.getQuantidadeUnidades());
        stmt.setString(4, objeto.getCnpj());
        stmt.setString(5, Integer.toString(objeto.getAnoLancamento()));
        stmt.setDouble(6, objeto.getAreaTotal());
        stmt.setString(7, objeto.getCep());
        stmt.setString(8, objeto.getLogradouro());
        stmt.setString(9, objeto.getCidade());
        stmt.setString(10, objeto.getBairro());
        stmt.setString(11, objeto.getComplemento());
        stmt.setString(12, objeto.getNumeroUnidadeAgua());
        stmt.setString(13, objeto.getNumeroUnidadeGas());
        stmt.setString(14, objeto.getFormulaCalculo());
        stmt.setString(15, objeto.getObservacao());
        stmt.setString(16, objeto.getStatus());
    }

    private static Edificio map(ResultSet rs) throws SQLException {
        Edificio objeto = new Edificio();
        objeto.setId(rs.getInt("id"));
        objeto.setNome(rs.getString("nome"));
        objeto.setQuantidadeAndares(rs.getInt("quantidade_andares"));
        objeto.setQuantidadeUnidades(rs.getInt("quantidade_unidades"));
        objeto.setCnpj(rs.getString("cnpj"));
        objeto.setAnoLancamento(Integer.parseInt(rs.getString("ano_lancamento")));
        objeto.setAreaTotal(rs.getDouble("area_total"));
        objeto.setCep(rs.getString("cep"));
        objeto.setLogradouro(rs.getString("logradouro"));
        objeto.setCidade(rs.getString("cidade"));
        objeto.setBairro(rs.getString("bairro"));
        objeto.setComplemento(rs.getString("complemento"));
        objeto.setNumeroUnidadeAgua(rs.getString("numero_unidade_agua"));
        objeto.setNumeroUnidadeGas(rs.getString("numero_unidade_gas"));
        objeto.setFormulaCalculo(rs.getString("formula_calculo"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        return objeto;
    }
}
