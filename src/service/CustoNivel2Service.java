package service;

import model.CustoNivel2;
import model.DAO.CustoNivel2DAO;

public class CustoNivel2Service extends CrudService<CustoNivel2> {
    public CustoNivel2Service() {
        super(new CustoNivel2DAO());
    }

    @Override
    public void validar(CustoNivel2 registro) {
        super.validar(registro);
        CadastroValidation.validarTexto(registro.getDescricao(), registro.getObservacao(), registro.getStatus());
        if (registro.getCustoNivel1() == null || registro.getCustoNivel1().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione um custo de nivel 1.");
        }
    }
}
