package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Condominio;
import model.UnidadeCondomino;

public class CondominioDAO implements InterfaceDAO<Condominio> {
    private static final String COLUMNS = "id, mes_referencia, ano_referencia, data_emissao, data_vencimento, data_pagamento, juros, multas, correcao, valor_emitido, valor_pago, observacao, status, unidade_condomino_id"
            + ", (SELECT u.descricao FROM unidade u JOIN unidade_condomino uc ON uc.unidade_id = u.id WHERE uc.id = condominio.unidade_condomino_id) AS descricao_unidade";

    @Override
    public void create(Condominio objeto) {
        String sql = "INSERT INTO condominio (mes_referencia, ano_referencia, data_emissao, data_vencimento, data_pagamento, juros, multas, correcao, valor_emitido, valor_pago, observacao, status, unidade_condomino_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar condominio", ex);
        }
    }

    @Override
    public Condominio retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM condominio WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar condominio", ex);
        }
    }

    @Override
    public List<Condominio> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            Condominio item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "unidade" -> "(SELECT u.descricao FROM unidade u JOIN unidade_condomino uc ON uc.unidade_id = u.id WHERE uc.id = condominio.unidade_condomino_id)";
            case "mes_referencia" -> "mes_referencia";
            case "ano_referencia" -> "ano_referencia";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        boolean referencia = "mes_referencia".equals(coluna) || "ano_referencia".equals(coluna);
        Integer numeroReferencia = referencia ? Integer.valueOf(valor) : null;
        String sql = "SELECT " + COLUMNS + " FROM condominio WHERE " + coluna
                + (referencia ? " = ?" : " LIKE ?");
        List<Condominio> itens = new ArrayList<>();
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
            throw new IllegalStateException("Erro ao buscar condominio", ex);
        }
    }

    @Override
    public void update(Condominio objeto) {
        String sql = "UPDATE condominio SET mes_referencia = ?, ano_referencia = ?, data_emissao = ?, data_vencimento = ?, data_pagamento = ?, juros = ?, multas = ?, correcao = ?, valor_emitido = ?, valor_pago = ?, observacao = ?, status = ?, unidade_condomino_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(14, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar condominio", ex);
        }
    }

    @Override
    public void delete(Condominio objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM condominio WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir condominio", ex);
        }
    }

    private static void bind(PreparedStatement stmt, Condominio objeto) throws SQLException {
        stmt.setInt(1, objeto.getMesReferencia());
        stmt.setInt(2, objeto.getAnoReferencia());
        stmt.setObject(3, objeto.getDataEmissao());
        stmt.setObject(4, objeto.getDataVencimento());
        stmt.setObject(5, objeto.getDataPagamento());
        stmt.setDouble(6, objeto.getJuros());
        stmt.setDouble(7, objeto.getMultas());
        stmt.setDouble(8, objeto.getCorrecao());
        stmt.setDouble(9, objeto.getValorEmitido());
        stmt.setDouble(10, objeto.getValorPago());
        stmt.setString(11, objeto.getObservacao());
        stmt.setString(12, objeto.getStatus());
        if (objeto.getUnidadeCondomino() == null || objeto.getUnidadeCondomino().getId() <= 0) throw new IllegalArgumentException("UnidadeCondomino é obrigatório");
        stmt.setInt(13, objeto.getUnidadeCondomino().getId());
    }

    private static Condominio map(ResultSet rs) throws SQLException {
        Condominio objeto = new Condominio();
        objeto.setId(rs.getInt("id"));
        objeto.setMesReferencia(rs.getInt("mes_referencia"));
        objeto.setAnoReferencia(rs.getInt("ano_referencia"));
        objeto.setDataEmissao(rs.getObject("data_emissao", java.time.LocalDate.class));
        objeto.setDataVencimento(rs.getObject("data_vencimento", java.time.LocalDate.class));
        objeto.setDataPagamento(rs.getObject("data_pagamento", java.time.LocalDate.class));
        objeto.setJuros(rs.getDouble("juros"));
        objeto.setMultas(rs.getDouble("multas"));
        objeto.setCorrecao(rs.getDouble("correcao"));
        objeto.setValorEmitido(rs.getDouble("valor_emitido"));
        objeto.setValorPago(rs.getDouble("valor_pago"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int unidadeCondominoId = rs.getInt("unidade_condomino_id");
        if (!rs.wasNull()) {
            UnidadeCondomino unidadeCondomino = new UnidadeCondomino();
            unidadeCondomino.setId(unidadeCondominoId);
            objeto.setUnidadeCondomino(unidadeCondomino);
        }
        if (objeto.getUnidadeCondomino() != null) {
            model.Unidade unidadeVinculada = new model.Unidade();
            unidadeVinculada.setDescricao(rs.getString("descricao_unidade"));
            objeto.getUnidadeCondomino().setUnidade(unidadeVinculada);
        }
        return objeto;
    }
}
