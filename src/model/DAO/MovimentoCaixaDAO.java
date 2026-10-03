package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.MovimentoCaixa;
import model.Edificio;
import model.CustoNivel2;
import model.CustoNivel1;
import model.Fornecedor;

public class MovimentoCaixaDAO implements InterfaceDAO<MovimentoCaixa> {
    private static final String COLUMNS = "id, data_emissao, data_vencimento, data_pagamento, "
            + "valor_emitido, multas, correcao_monetaria, juros, valor_pagamento, tipo, "
            + "flag_rateio, flag_formula, observacao, status, edificio_id, custo_nivel2_id, fornecedor_id, "
            + "(SELECT c.custo_nivel1_id FROM custo_nivel2 c "
            + "WHERE c.id = movimento_caixa.custo_nivel2_id) AS custo_nivel1_id"
            + ", (SELECT v.nome FROM edificio v WHERE v.id = movimento_caixa.edificio_id) AS nome_edificio, (SELECT v.nome_fantasia FROM fornecedor v WHERE v.id = movimento_caixa.fornecedor_id) AS nome_fornecedor, (SELECT v.descricao FROM custo_nivel1 v JOIN custo_nivel2 c ON c.custo_nivel1_id = v.id WHERE c.id = movimento_caixa.custo_nivel2_id) AS nome_custo_nivel1, (SELECT v.descricao FROM custo_nivel2 v WHERE v.id = movimento_caixa.custo_nivel2_id) AS nome_custo_nivel2";

    @Override
    public void create(MovimentoCaixa objeto) {
        String sql = "INSERT INTO movimento_caixa (data_emissao, data_vencimento, data_pagamento, valor_emitido, multas, correcao_monetaria, juros, valor_pagamento, tipo, flag_rateio, flag_formula, observacao, status, edificio_id, custo_nivel2_id, fornecedor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind(stmt, objeto);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) objeto.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao criar movimento_caixa", ex);
        }
    }

    @Override
    public MovimentoCaixa retrieve(int id) {
        String sql = "SELECT " + COLUMNS + " FROM movimento_caixa WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao consultar movimento_caixa", ex);
        }
    }

    @Override
    public List<MovimentoCaixa> retrieve(String parametro, String valor) {
        if ("id".equals(parametro)) {
            MovimentoCaixa item = retrieve(Integer.parseInt(valor));
            return item == null ? List.of() : List.of(item);
        }
        String coluna = switch (parametro) {
            case "tipo" -> "tipo";
            case "edificio" -> "(SELECT e.nome FROM edificio e WHERE e.id = movimento_caixa.edificio_id)";
            case "fornecedor" -> "(SELECT f.nome_fantasia FROM fornecedor f WHERE f.id = movimento_caixa.fornecedor_id)";
            case "status" -> "status";
            default -> throw new IllegalArgumentException("Campo de busca inválido: " + parametro);
        };
        String sql = "SELECT " + COLUMNS + " FROM movimento_caixa WHERE " + coluna + " LIKE ?";
        List<MovimentoCaixa> itens = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + valor + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) itens.add(map(rs));
            }
            return itens;
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao buscar movimento_caixa", ex);
        }
    }

    @Override
    public void update(MovimentoCaixa objeto) {
        String sql = "UPDATE movimento_caixa SET data_emissao = ?, data_vencimento = ?, data_pagamento = ?, valor_emitido = ?, multas = ?, correcao_monetaria = ?, juros = ?, valor_pagamento = ?, tipo = ?, flag_rateio = ?, flag_formula = ?, observacao = ?, status = ?, edificio_id = ?, custo_nivel2_id = ?, fornecedor_id = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            bind(stmt, objeto);
            stmt.setInt(17, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao atualizar movimento_caixa", ex);
        }
    }

    @Override
    public void delete(MovimentoCaixa objeto) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM movimento_caixa WHERE id = ?")) {
            stmt.setInt(1, objeto.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new IllegalStateException("Erro ao excluir movimento_caixa", ex);
        }
    }

    private static void bind(PreparedStatement stmt, MovimentoCaixa objeto) throws SQLException {
        stmt.setObject(1, objeto.getDataEmissao());
        stmt.setObject(2, objeto.getDataVencimento());
        stmt.setObject(3, objeto.getDataPagamento());
        stmt.setDouble(4, objeto.getValorEmitido());
        stmt.setDouble(5, objeto.getMultas());
        stmt.setDouble(6, objeto.getCorrecaoMonetaria());
        stmt.setDouble(7, objeto.getJuros());
        stmt.setDouble(8, objeto.getValorPagoRec());
        stmt.setString(9, objeto.getTipo());
        stmt.setBoolean(10, objeto.isFlagRateio());
        stmt.setString(11, objeto.getFlagFormula());
        stmt.setString(12, objeto.getObservacao());
        stmt.setString(13, objeto.getStatus());
        if (objeto.getEdificio() == null || objeto.getEdificio().getId() <= 0) throw new IllegalArgumentException("Edificio é obrigatório");
        stmt.setInt(14, objeto.getEdificio().getId());
        if (objeto.getCustoNivel2() == null || objeto.getCustoNivel2().getId() <= 0) throw new IllegalArgumentException("CustoNivel2 é obrigatório");
        stmt.setInt(15, objeto.getCustoNivel2().getId());
        if (objeto.getFornecedor() == null || objeto.getFornecedor().getId() <= 0) throw new IllegalArgumentException("Fornecedor é obrigatório");
        stmt.setInt(16, objeto.getFornecedor().getId());
    }

    private static MovimentoCaixa map(ResultSet rs) throws SQLException {
        MovimentoCaixa objeto = new MovimentoCaixa();
        objeto.setId(rs.getInt("id"));
        objeto.setDataEmissao(rs.getObject("data_emissao", java.time.LocalDate.class));
        objeto.setDataVencimento(rs.getObject("data_vencimento", java.time.LocalDate.class));
        objeto.setDataPagamento(rs.getObject("data_pagamento", java.time.LocalDate.class));
        objeto.setValorEmitido(rs.getDouble("valor_emitido"));
        objeto.setMultas(rs.getDouble("multas"));
        objeto.setCorrecaoMonetaria(rs.getDouble("correcao_monetaria"));
        objeto.setJuros(rs.getDouble("juros"));
        objeto.setValorPagoRec(rs.getDouble("valor_pagamento"));
        objeto.setTipo(rs.getString("tipo"));
        objeto.setFlagRateio(rs.getBoolean("flag_rateio"));
        objeto.setFlagFormula(rs.getString("flag_formula"));
        objeto.setObservacao(rs.getString("observacao"));
        objeto.setStatus(rs.getString("status"));
        int edificioId = rs.getInt("edificio_id");
        if (!rs.wasNull()) {
            Edificio edificio = new Edificio();
            edificio.setId(edificioId);
            objeto.setEdificio(edificio);
        }
        int custoNivel2Id = rs.getInt("custo_nivel2_id");
        if (!rs.wasNull()) {
            CustoNivel2 custoNivel2 = new CustoNivel2();
            custoNivel2.setId(custoNivel2Id);
            objeto.setCustoNivel2(custoNivel2);
        }
        int custoNivel1Id = rs.getInt("custo_nivel1_id");
        if (!rs.wasNull()) {
            CustoNivel1 custoNivel1 = new CustoNivel1();
            custoNivel1.setId(custoNivel1Id);
            objeto.setCustoNivel1(custoNivel1);
        }
        int fornecedorId = rs.getInt("fornecedor_id");
        if (!rs.wasNull()) {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(fornecedorId);
            objeto.setFornecedor(fornecedor);
        }
        if (objeto.getEdificio() != null) objeto.getEdificio().setNome(rs.getString("nome_edificio"));
        if (objeto.getFornecedor() != null) objeto.getFornecedor().setNomeFantasia(rs.getString("nome_fornecedor"));
        if (objeto.getCustoNivel1() != null) objeto.getCustoNivel1().setDescricao(rs.getString("nome_custo_nivel1"));
        if (objeto.getCustoNivel2() != null) objeto.getCustoNivel2().setDescricao(rs.getString("nome_custo_nivel2"));
        if (objeto.getCustoNivel2() != null) objeto.getCustoNivel2().setCustoNivel1(objeto.getCustoNivel1());
        return objeto;
    }
}
