package service;

import model.Unidade;
import model.DAO.UnidadeDAO;

public class UnidadeService extends CrudService<Unidade> {
    public UnidadeService() {
        super(new UnidadeDAO());
    }

    @Override
    public void validar(Unidade registro) {
        super.validar(registro);
        if (registro.getMetragemIndividual() > registro.getMetragemTotal()) {
            throw new IllegalArgumentException("A metragem individual nao pode exceder a total.");
        }
    }
}
