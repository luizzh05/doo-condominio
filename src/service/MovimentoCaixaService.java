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
        if (registro.getDataVencimento() != null && registro.getDataVencimento().isBefore(registro.getDataEmissao())) {
            throw new IllegalArgumentException("DataVencimento nao pode ser anterior a DataEmissao.");
        }
        if (registro.getCustoNivel1() == null || registro.getCustoNivel2() == null
                || registro.getCustoNivel2().getCustoNivel1() == null
                || registro.getCustoNivel2().getCustoNivel1().getId() != registro.getCustoNivel1().getId()) {
            throw new IllegalArgumentException("Os custos de nivel 1 e 2 devem pertencer ao mesmo grupo.");
        }
    }
}
