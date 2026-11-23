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
            throw new IllegalStateException("Erro ao criar condomínio", ex);
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
            throw new IllegalStateException("Erro ao consultar condomínio", ex);
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
            throw new IllegalStateException("Erro ao buscar condomínio", ex);
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
            throw new IllegalStateException("Erro ao atualizar condomínio", ex);
        }
    }

    public boolean registrarProtesto(int id) {
        String sql = "UPDATE condominio SET status = ? WHERE id = ? AND status = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, Condominio.PROTESTADO);
            stmt.setInt(2, id);
            stmt.setString(3, Condominio.PAGO);
            return stmt.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao registrar protesto", ex);
        }
    }

    @Override
    public void delete(Condominio objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM condominio WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir condomínio", ex);
        }
    }

    private static final String EXISTE_SQL = "SELECT 1 FROM condominio c JOIN unidade_condomino uc ON uc.id = c.unidade_condomino_id JOIN unidade u ON u.id = uc.unidade_id"
            + " WHERE u.edificio_id = ? AND c.mes_referencia = ? AND c.ano_referencia = ? LIMIT 1";

    public boolean existeParaEdificioReferencia(int edificioId, int mesReferencia, int anoReferencia) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return existe(conn, edificioId, mesReferencia, anoReferencia);
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar condomínio", ex);
        }
    }

    public List<Condominio> listarPorEdificioReferencia(int edificioId, int mesReferencia, int anoReferencia) {
        String sql = "SELECT c.id, c.mes_referencia, c.ano_referencia, c.data_emissao, c.data_vencimento, c.data_pagamento, c.juros, c.multas, c.correcao, c.valor_emitido, c.valor_pago, c.observacao, c.status, c.unidade_condomino_id, u.descricao AS descricao_unidade, p.nome_fantasia AS nome_proprietario"
                + " FROM condominio c JOIN unidade_condomino uc ON uc.id = c.unidade_condomino_id JOIN unidade u ON u.id = uc.unidade_id JOIN proprietario p ON p.id = uc.proprietario_id"
                + " WHERE u.edificio_id = ? AND c.mes_referencia = ? AND c.ano_referencia = ? ORDER BY u.descricao, c.id";
        List<Condominio> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, edificioId);
            stmt.setInt(2, mesReferencia);
            stmt.setInt(3, anoReferencia);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Condominio item = map(rs);
                    model.Proprietario proprietario = new model.Proprietario();
                    proprietario.setNomeFantasia(rs.getString("nome_proprietario"));
                    item.getUnidadeCondomino().setProprietario(proprietario);
                    itens.add(item);
                }
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao listar condomínios", ex);
        }
    }

    public void createLote(int edificioId, int mesReferencia, int anoReferencia, List<Condominio> condominios) {
        if (condominios == null || condominios.isEmpty()) {
            throw new IllegalArgumentException("Nenhum condomínio para gerar.");
        }
        String sql = "INSERT INTO condominio (mes_referencia, ano_referencia, data_emissao, data_vencimento, data_pagamento, juros, multas, correcao, valor_emitido, valor_pago, observacao, status, unidade_condomino_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String mensagemDuplicado = String.format("Já existem condomínios gerados para este edifício em %02d/%04d. Geração bloqueada.", mesReferencia, anoReferencia);
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                if (existe(conn, edificioId, mesReferencia, anoReferencia)) {
                    conn.rollback();
                    throw new IllegalArgumentException(mensagemDuplicado);
                }
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    for (Condominio condominio : condominios) {
                        bind(stmt, condominio);
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
                conn.commit();
            } catch (SQLException ex) {
                rollbackSilencioso(conn);
                if (duplicidade(ex)) {
                    throw new IllegalArgumentException(mensagemDuplicado);
                }
                throw new IllegalStateException("Erro ao gerar condomínios", ex);
            } catch (RuntimeException ex) {
                rollbackSilencioso(conn);
                throw ex;
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ignorada) {
                    // conexao sera fechada em seguida
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao gerar condomínios", ex);
        }
    }

    private static boolean duplicidade(Throwable ex) {
        java.util.Set<Throwable> visitados = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());
        return duplicidade(ex, visitados);
    }

    private static boolean duplicidade(Throwable t, java.util.Set<Throwable> visitados) {
        for (; t != null && visitados.add(t); t = t.getCause()) {
            if (t instanceof SQLException s) {
                if (s.getErrorCode() == 1062) return true;
                for (SQLException n = s.getNextException(); n != null && visitados.add(n); n = n.getNextException()) {
                    if (duplicidade(n, visitados)) return true;
                }
            }
        }
        return false;
    }

    public boolean existeParaUnidadeReferencia(int unidadeCondominoId, int mes, int ano, int ignorarId) {
        String sql = "SELECT 1 FROM condominio WHERE unidade_condomino_id = ? AND mes_referencia = ? AND ano_referencia = ? AND id <> ? LIMIT 1";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, unidadeCondominoId);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            stmt.setInt(4, ignorarId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar condomínio", ex);
        }
    }

    private static void rollbackSilencioso(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException ignorada) {
            // melhor esforco
        }
    }

    private static boolean existe(Connection conn, int edificioId, int mes, int ano) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(EXISTE_SQL)) {
            stmt.setInt(1, edificioId);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
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
        if (objeto.getUnidadeCondomino() == null || objeto.getUnidadeCondomino().getId() <= 0) throw new IllegalArgumentException("Condômino da unidade é obrigatório");
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
