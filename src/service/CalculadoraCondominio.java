package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Condominio;
import model.Edificio;
import model.Leitura;
import model.MovimentoCaixa;
import model.UnidadeCondomino;

public final class CalculadoraCondominio {

    private static final String AGUA = "Agua";
    private static final String GAS = "Gas";

    private CalculadoraCondominio() {
    }

    public static PreviaGeracaoCondominio calcular(Edificio edificio, List<UnidadeCondomino> unidades,
            List<Leitura> leituras, List<MovimentoCaixa> movimentos, YearMonth referencia, LocalDate hoje) {
        if (edificio == null || !"A".equals(edificio.getStatus())) {
            throw new IllegalArgumentException("Edifício inativo ou não informado.");
        }
        if (unidades == null || unidades.isEmpty()) {
            throw new IllegalArgumentException("Edifício sem unidades ativas com condômino vinculado.");
        }
        if (leituras == null) {
            leituras = List.of();
        }
        if (movimentos == null) {
            movimentos = List.of();
        }
        String mmaaaa = String.format("%02d/%04d", referencia.getMonthValue(), referencia.getYear());
        List<String> avisos = new ArrayList<>();

        verificarDuplicidade(unidades);

        // Movimentos
        BigDecimal totalUnd = BigDecimal.ZERO;
        BigDecimal totalM2 = BigDecimal.ZERO;
        for (MovimentoCaixa m : movimentos) {
            String formula = m.getFlagFormula() == null ? null : m.getFlagFormula().trim();
            if (formula == null || formula.isBlank()) {
                formula = edificio.getFormulaCalculo() == null ? null : edificio.getFormulaCalculo().trim();
            }
            if (formula == null || formula.isBlank()) {
                throw new IllegalArgumentException("Movimento #" + m.getId() + " sem fórmula e edifício sem fórmula de cálculo.");
            }
            if (!FormulaRateio.valida(formula)) {
                throw new IllegalArgumentException("Movimento #" + m.getId() + " com fórmula inválida: " + formula + ".");
            }
            String tipo = m.getTipo() == null ? null : m.getTipo().trim();
            BigDecimal valor = BigDecimal.valueOf(m.getValorEmitido());
            if ("Receita".equals(tipo)) {
                valor = valor.negate();
            } else if (!"Despesa".equals(tipo)) {
                throw new IllegalArgumentException("Movimento #" + m.getId() + " com tipo inválido: " + m.getTipo() + ".");
            }
            if (FormulaRateio.POR_UNIDADE.equals(formula)) {
                totalUnd = totalUnd.add(valor);
            } else {
                totalM2 = totalM2.add(valor);
            }
        }
        totalUnd = totalUnd.setScale(2, RoundingMode.HALF_UP);
        totalM2 = totalM2.setScale(2, RoundingMode.HALF_UP);

        // Pesos
        int n = unidades.size();
        List<BigDecimal> pesosUnd = new ArrayList<>();
        List<BigDecimal> pesosM2 = new ArrayList<>();
        BigDecimal somaM2 = BigDecimal.ZERO;
        for (UnidadeCondomino uc : unidades) {
            pesosUnd.add(BigDecimal.ONE);
            BigDecimal metragem = BigDecimal.valueOf(uc.getUnidade().getMetragemIndividual());
            pesosM2.add(metragem);
            somaM2 = somaM2.add(metragem);
        }
        if (totalM2.signum() != 0 && somaM2.signum() <= 0) {
            throw new IllegalArgumentException("Metragem individual total das unidades é zero; não é possível ratear movimentos Por m².");
        }
        List<BigDecimal> rateioUnd = ratear(totalUnd, pesosUnd);
        List<BigDecimal> rateioM2 = ratear(totalM2, pesosM2);

        // Leituras
        Map<Integer, BigDecimal> consAgua = new HashMap<>();
        Map<Integer, BigDecimal> consGas = new HashMap<>();
        Set<Integer> idsUc = new HashSet<>();
        for (UnidadeCondomino uc : unidades) {
            idsUc.add(uc.getId());
        }
        for (Leitura l : leituras) {
            UnidadeCondomino uc = l.getUnidadeCondomino();
            String tipo = l.getTipo() == null ? "" : l.getTipo().trim();
            if (uc == null || !idsUc.contains(uc.getId())) {
                avisos.add("Leitura #" + l.getId() + " ignorada: unidade não pertence aos condôminos ativos do edifício.");
                continue;
            }
            Map<Integer, BigDecimal> destino;
            if (AGUA.equalsIgnoreCase(tipo)) {
                destino = consAgua;
            } else if (GAS.equalsIgnoreCase(tipo)) {
                destino = consGas;
            } else {
                continue;
            }
            if (Double.isNaN(l.getMedicaoAnterior())) {
                avisos.add("Leitura #" + l.getId() + " (unidade " + descricao(uc) + ", " + rotuloTipo(tipo)
                        + ") sem medição anterior; consumo considerado 0.");
                continue;
            }
            BigDecimal consumo = BigDecimal.valueOf(l.getMedicaoAtual()).subtract(BigDecimal.valueOf(l.getMedicaoAnterior()));
            if (consumo.signum() < 0) {
                throw new IllegalArgumentException("Leitura #" + l.getId() + " (unidade " + descricao(uc) + ", " + rotuloTipo(tipo)
                        + ") com medição atual menor que a anterior.");
            }
            destino.merge(uc.getId(), consumo, BigDecimal::add);
        }

        BigDecimal precoAgua = edificio.getPrecoM3Agua() == null ? BigDecimal.ZERO : edificio.getPrecoM3Agua();
        BigDecimal precoGas = edificio.getPrecoM3Gas() == null ? BigDecimal.ZERO : edificio.getPrecoM3Gas();
        BigDecimal somaAgua = BigDecimal.ZERO;
        BigDecimal somaGas = BigDecimal.ZERO;
        for (UnidadeCondomino uc : unidades) {
            if (!consAgua.containsKey(uc.getId())) {
                avisos.add("Unidade " + descricao(uc) + " sem leitura de Água em " + mmaaaa + " (considerado 0).");
            } else {
                somaAgua = somaAgua.add(consAgua.get(uc.getId()));
            }
            if (!consGas.containsKey(uc.getId())) {
                avisos.add("Unidade " + descricao(uc) + " sem leitura de Gás em " + mmaaaa + " (considerado 0).");
            } else {
                somaGas = somaGas.add(consGas.get(uc.getId()));
            }
        }
        if (somaAgua.signum() > 0 && precoAgua.signum() == 0) {
            avisos.add("Preço do m³ de água do edifício é zero; consumo cobrado como 0.");
        }
        if (somaGas.signum() > 0 && precoGas.signum() == 0) {
            avisos.add("Preço do m³ de gás do edifício é zero; consumo cobrado como 0.");
        }
        if (movimentos.isEmpty()) {
            avisos.add("Nenhum movimento de rateio em " + mmaaaa + ".");
        }

        // Condominios
        LocalDate vencimento = calcularVencimento(referencia, hoje);
        List<Condominio> resultado = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            UnidadeCondomino uc = unidades.get(i);
            BigDecimal und = rateioUnd.get(i);
            BigDecimal m2 = rateioM2.get(i);
            BigDecimal agua = consAgua.getOrDefault(uc.getId(), BigDecimal.ZERO).multiply(precoAgua).setScale(2, RoundingMode.HALF_UP);
            BigDecimal gas = consGas.getOrDefault(uc.getId(), BigDecimal.ZERO).multiply(precoGas).setScale(2, RoundingMode.HALF_UP);
            BigDecimal valor = und.add(m2).add(agua).add(gas);
            if (valor.signum() < 0) {
                throw new IllegalArgumentException("Valor calculado negativo para a unidade " + descricao(uc) + " (receitas maiores que despesas).");
            }
            Condominio c = new Condominio();
            c.setMesReferencia(referencia.getMonthValue());
            c.setAnoReferencia(referencia.getYear());
            c.setDataEmissao(hoje);
            c.setDataVencimento(vencimento);
            c.setDataPagamento(null);
            c.setJuros(0);
            c.setMultas(0);
            c.setCorrecao(0);
            c.setValorPago(0);
            c.setStatus("A");
            c.setValorEmitido(valor.doubleValue());
            String obs = String.format("Gerado: Und %s; m² %s; Água %s; Gás %s",
                    und.toPlainString(), m2.toPlainString(), agua.toPlainString(), gas.toPlainString());
            c.setObservacao(obs.length() > 100 ? obs.substring(0, 100) : obs);
            c.setUnidadeCondomino(uc);
            resultado.add(c);
        }
        return new PreviaGeracaoCondominio(edificio, referencia, resultado, avisos);
    }

    /** Multa única de 2% sobre o valor emitido quando pago após o vencimento. */
    public static final BigDecimal TAXA_MULTA = new BigDecimal("0.02");
    /** Juros de mora de 1% ao mês, proporcional aos dias de atraso (mês de 30 dias). */
    public static final BigDecimal TAXA_JUROS_MES = new BigDecimal("0.01");

    public record Pagamento(LocalDate dataPagamento, long diasAtraso, BigDecimal multa, BigDecimal juros,
            BigDecimal correcao, BigDecimal valorPago) {}

    public static Pagamento calcularPagamento(double valorEmitido, LocalDate vencimento, LocalDate dataPagamento,
            BigDecimal correcao) {
        if (dataPagamento == null) {
            throw new IllegalArgumentException("Informe a data de pagamento.");
        }
        if (correcao == null) {
            correcao = BigDecimal.ZERO;
        }
        if (correcao.signum() < 0) {
            throw new IllegalArgumentException("A correção não pode ser negativa.");
        }
        BigDecimal valor = BigDecimal.valueOf(valorEmitido);
        long dias = vencimento == null ? 0 : Math.max(0, java.time.temporal.ChronoUnit.DAYS.between(vencimento, dataPagamento));
        BigDecimal multa = BigDecimal.ZERO;
        BigDecimal juros = BigDecimal.ZERO;
        if (dias > 0) {
            multa = valor.multiply(TAXA_MULTA);
            juros = valor.multiply(TAXA_JUROS_MES).multiply(BigDecimal.valueOf(dias))
                    .divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);
        }
        multa = multa.setScale(2, RoundingMode.HALF_UP);
        juros = juros.setScale(2, RoundingMode.HALF_UP);
        correcao = correcao.setScale(2, RoundingMode.HALF_UP);
        BigDecimal pago = valor.add(multa).add(juros).add(correcao).setScale(2, RoundingMode.HALF_UP);
        return new Pagamento(dataPagamento, dias, multa, juros, correcao, pago);
    }

    public static LocalDate calcularVencimento(YearMonth referencia, LocalDate hoje) {
        LocalDate base = referencia.plusMonths(1).atDay(10);
        return base.isBefore(hoje) ? hoje.plusDays(10) : base;
    }

    static List<BigDecimal> ratear(BigDecimal total, List<BigDecimal> pesos) {
        int n = pesos.size();
        List<BigDecimal> partes = new ArrayList<>();
        if (total.signum() == 0) {
            for (int i = 0; i < n; i++) {
                partes.add(BigDecimal.ZERO.setScale(2));
            }
            return partes;
        }
        BigDecimal soma = BigDecimal.ZERO;
        for (BigDecimal p : pesos) {
            soma = soma.add(p);
        }
        BigDecimal acumulado = BigDecimal.ZERO;
        for (int i = 0; i < n; i++) {
            BigDecimal parte;
            if (i < n - 1) {
                parte = total.multiply(pesos.get(i)).divide(soma, 2, RoundingMode.HALF_UP);
                acumulado = acumulado.add(parte);
            } else {
                parte = total.subtract(acumulado).setScale(2, RoundingMode.HALF_UP);
            }
            partes.add(parte);
        }
        return partes;
    }

    private static void verificarDuplicidade(List<UnidadeCondomino> unidades) {
        Map<Integer, Integer> contagem = new HashMap<>();
        Map<Integer, String> descricoes = new HashMap<>();
        for (UnidadeCondomino uc : unidades) {
            int id = uc.getUnidade().getId();
            contagem.merge(id, 1, Integer::sum);
            descricoes.put(id, descricao(uc));
        }
        List<String> duplicadas = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : contagem.entrySet()) {
            if (e.getValue() > 1) {
                duplicadas.add(descricoes.get(e.getKey()));
            }
        }
        if (!duplicadas.isEmpty()) {
            Collections.sort(duplicadas);
            throw new IllegalArgumentException("Unidades com mais de um condômino ativo: " + String.join(", ", duplicadas) + ".");
        }
    }

    private static String rotuloTipo(String tipo) {
        if (AGUA.equalsIgnoreCase(tipo)) {
            return "Água";
        }
        if (GAS.equalsIgnoreCase(tipo)) {
            return "Gás";
        }
        return tipo;
    }

    private static String descricao(UnidadeCondomino uc) {
        if (uc == null || uc.getUnidade() == null || uc.getUnidade().getDescricao() == null) {
            return "?";
        }
        return uc.getUnidade().getDescricao();
    }
}
