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
        if (registro.getEdificio() == null || registro.getEdificio().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o edifício.");
        }
        if (registro.getMetragemIndividual() > registro.getMetragemTotal()) {
            throw new IllegalArgumentException("A metragem individual não pode exceder a total.");
        }
    }
}
