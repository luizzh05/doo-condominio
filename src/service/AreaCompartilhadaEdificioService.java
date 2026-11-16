package service;

import model.AreaCompartilhadaEdificio;
import model.DAO.AreaCompartilhadaEdificioDAO;

public class AreaCompartilhadaEdificioService extends CrudService<AreaCompartilhadaEdificio> {
    public AreaCompartilhadaEdificioService() {
        super(new AreaCompartilhadaEdificioDAO());
    }

    @Override
    public void validar(AreaCompartilhadaEdificio registro) {
        super.validar(registro);
        if (registro.getAreaCompartilhada() == null || registro.getAreaCompartilhada().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione AreaCompartilhada.");
        }
        if (registro.getEdificio() == null || registro.getEdificio().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione Edificio.");
        }
    }
}
