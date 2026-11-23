package service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import model.Condominio;
import model.DAO.CondominioDAO;
import model.DAO.EdificioDAO;
import model.DAO.LeituraDAO;
import model.DAO.MovimentoCaixaDAO;
import model.DAO.UnidadeCondominoDAO;
import model.Edificio;
import model.Leitura;
import model.MovimentoCaixa;
import model.UnidadeCondomino;

public class GestaoCondominioService {
    private final EdificioDAO edificioDAO = new EdificioDAO();
    private final UnidadeCondominoDAO unidadeCondominoDAO = new UnidadeCondominoDAO();
    private final LeituraDAO leituraDAO = new LeituraDAO();
    private final MovimentoCaixaDAO movimentoCaixaDAO = new MovimentoCaixaDAO();
    private final CondominioDAO condominioDAO = new CondominioDAO();
    private final CondominioService condominioService = new CondominioService();

    public static YearMonth referenciaDe(int mesSelecionado, int anoSelecionado) {
        if (mesSelecionado < 1 || mesSelecionado > 12 || anoSelecionado < 2000 || anoSelecionado > 2100) {
            throw new IllegalArgumentException("Informe mês entre 1 e 12 e ano entre 2000 e 2100.");
        }
        return YearMonth.of(anoSelecionado, mesSelecionado);
    }

    public PreviaGeracaoCondominio calcular(Edificio edificio, int mesSelecionado, int anoSelecionado) {
        if (edificio == null || edificio.getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o edifício.");
        }
        Edificio atual = edificioDAO.retrieve(edificio.getId());
        if (atual == null) {
            throw new IllegalArgumentException("Busque e selecione o edifício.");
        }
        YearMonth ref = referenciaDe(mesSelecionado, anoSelecionado);
        if (!ref.isBefore(YearMonth.now())) {
            throw new IllegalArgumentException(String.format(
                    "Só é possível gerar condomínios de meses encerrados (referência %02d/%04d).",
                    ref.getMonthValue(), ref.getYear()));
        }
        if (condominioDAO.existeParaEdificioReferencia(atual.getId(), ref.getMonthValue(), ref.getYear())) {
            throw new IllegalArgumentException(String.format(
                    "Já existem condomínios gerados para %s em %02d/%04d. Geração bloqueada.",
                    atual.getNome(), ref.getMonthValue(), ref.getYear()));
        }
        List<UnidadeCondomino> unidades = unidadeCondominoDAO.listarAtivasPorEdificio(atual.getId());
        List<Leitura> leituras = leituraDAO.listarAtivasPorEdificioReferencia(
                atual.getId(), ref.getMonthValue(), ref.getYear());
        List<MovimentoCaixa> movimentos = movimentoCaixaDAO.listarRateioPorEdificioPeriodo(
                atual.getId(), ref.atDay(1), ref.atEndOfMonth());
        return CalculadoraCondominio.calcular(atual, unidades, leituras, movimentos, ref, LocalDate.now());
    }

    public int gravar(PreviaGeracaoCondominio previa) {
        if (previa == null) {
            throw new IllegalArgumentException("Calcule a prévia antes de gravar.");
        }
        for (Condominio c : previa.getCondominios()) {
            condominioService.validar(c);
        }
        YearMonth ref = previa.getReferencia();
        condominioDAO.createLote(previa.getEdificio().getId(), ref.getMonthValue(), ref.getYear(),
                previa.getCondominios());
        return previa.getCondominios().size();
    }

    public List<Condominio> listar(int edificioId, YearMonth referencia) {
        return condominioDAO.listarPorEdificioReferencia(edificioId, referencia.getMonthValue(), referencia.getYear());
    }

    public Condominio buscar(int id) {
        Condominio atual = condominioDAO.retrieve(id);
        if (atual == null) {
            throw new IllegalArgumentException("Condomínio não encontrado. Pesquise novamente.");
        }
        return atual;
    }

    public CalculadoraCondominio.Pagamento simularPagamento(Condominio condominio, LocalDate dataPagamento,
            java.math.BigDecimal correcao) {
        return CalculadoraCondominio.calcularPagamento(condominio.getValorEmitido(),
                condominio.getDataVencimento(), dataPagamento, correcao);
    }

    public void pagar(int id, LocalDate dataPagamento, java.math.BigDecimal correcao) {
        Condominio atual = buscar(id);
        if (!Condominio.ATIVO.equals(atual.getStatus()) && !Condominio.PROCESSADO.equals(atual.getStatus())) {
            throw new IllegalArgumentException("Só é possível pagar condomínio Ativo ou Processado (status atual: "
                    + Condominio.descricaoStatus(atual.getStatus()) + ").");
        }
        if (atual.getDataEmissao() != null && dataPagamento != null && dataPagamento.isBefore(atual.getDataEmissao())) {
            throw new IllegalArgumentException("A data de pagamento não pode ser anterior à data de emissão.");
        }
        CalculadoraCondominio.Pagamento pg = simularPagamento(atual, dataPagamento, correcao);
        atual.setDataPagamento(pg.dataPagamento());
        atual.setMultas(pg.multa().doubleValue());
        atual.setJuros(pg.juros().doubleValue());
        atual.setCorrecao(pg.correcao().doubleValue());
        atual.setValorPago(pg.valorPago().doubleValue());
        atual.setStatus(Condominio.PAGO);
        condominioDAO.update(atual);
    }

    public void processar(int id) {
        Condominio atual = buscar(id);
        if (!Condominio.ATIVO.equals(atual.getStatus())) {
            throw new IllegalArgumentException("Só é possível processar condomínio Ativo (status atual: "
                    + Condominio.descricaoStatus(atual.getStatus()) + ").");
        }
        atual.setStatus(Condominio.PROCESSADO);
        condominioDAO.update(atual);
    }

    public void protestar(int id) {
        if (!condominioDAO.registrarProtesto(id)) {
            throw new IllegalArgumentException("Só é possível protestar um condomínio com status Pago.");
        }
    }
}
