package service;

import model.CustoNivel1;
import model.DAO.CustoNivel1DAO;

public class CustoNivel1Service extends CrudService<CustoNivel1> {
    public CustoNivel1Service() {
        super(new CustoNivel1DAO());
    }

    @Override
    public void validar(CustoNivel1 registro) {
        super.validar(registro);
        CadastroValidation.validarTexto(registro.getDescricao(), registro.getObservacao(), registro.getStatus());
        if (!"Credito".equals(registro.getTipoCc()) && !"Debito".equals(registro.getTipoCc())) {
            throw new IllegalArgumentException("Selecione o tipo CC (Credito ou Debito).");
        }
    }
}
