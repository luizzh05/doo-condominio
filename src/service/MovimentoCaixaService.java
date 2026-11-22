package service;

import model.MovimentoCaixa;
import model.DAO.MovimentoCaixaDAO;

public class MovimentoCaixaService extends CrudService<MovimentoCaixa> {
    public MovimentoCaixaService() {
        super(new MovimentoCaixaDAO());
    }

    @Override
    public void validar(MovimentoCaixa registro) {
        super.validar(registro);
        if (registro.getEdificio() == null || registro.getEdificio().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o edifício.");
        }
        if (registro.getFornecedor() == null || registro.getFornecedor().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o fornecedor.");
        }
        if (registro.getCustoNivel1() == null || registro.getCustoNivel1().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o custo nível 1.");
        }
        if (registro.getCustoNivel2() == null || registro.getCustoNivel2().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o custo nível 2.");
        }
        if (registro.getDataVencimento() != null && registro.getDataVencimento().isBefore(registro.getDataEmissao())) {
            throw new IllegalArgumentException("A data de vencimento não pode ser anterior à data de emissão.");
        }
        if (registro.getCustoNivel1() == null || registro.getCustoNivel2() == null
                || registro.getCustoNivel2().getCustoNivel1() == null
                || registro.getCustoNivel2().getCustoNivel1().getId() != registro.getCustoNivel1().getId()) {
            throw new IllegalArgumentException("Os custos de nível 1 e 2 devem pertencer ao mesmo grupo.");
        }
        String tipo = registro.getTipo();
        if (!"Receita".equals(tipo) && !"Despesa".equals(tipo)) {
            throw new IllegalArgumentException("Selecione o tipo (Receita ou Despesa).");
        }
        String flagFormula = registro.getFlagFormula();
        if (flagFormula != null && !FormulaRateio.valida(flagFormula)) {
            throw new IllegalArgumentException("Selecione uma fórmula válida (Por Und. ou Por m²).");
        }
        if (!registro.isFlagRateio() && flagFormula != null) {
            throw new IllegalArgumentException("A fórmula só pode ser informada quando o movimento entra no rateio.");
        }
    }
}
