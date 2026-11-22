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
            throw new IllegalArgumentException("Informe mes entre 1 e 12 e ano entre 2000 e 2100.");
        }
        return YearMonth.of(anoSelecionado, mesSelecionado).minusMonths(1);
    }

    public PreviaGeracaoCondominio calcular(Edificio edificio, int mesSelecionado, int anoSelecionado) {
        if (edificio == null || edificio.getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o Edificio.");
        }
        Edificio atual = edificioDAO.retrieve(edificio.getId());
        if (atual == null) {
            throw new IllegalArgumentException("Busque e selecione o Edificio.");
        }
        YearMonth ref = referenciaDe(mesSelecionado, anoSelecionado);
        if (!ref.isBefore(YearMonth.now())) {
            throw new IllegalArgumentException(String.format(
                    "So e possivel gerar condominios de meses encerrados (referencia %02d/%04d).",
                    ref.getMonthValue(), ref.getYear()));
        }
        if (condominioDAO.existeParaEdificioReferencia(atual.getId(), ref.getMonthValue(), ref.getYear())) {
            throw new IllegalArgumentException(String.format(
                    "Ja existem condominios gerados para %s em %02d/%04d. Geracao bloqueada.",
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
            throw new IllegalArgumentException("Calcule a previa antes de gravar.");
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
}
