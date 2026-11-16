package service;

import model.AreaCompartilhadaEdificio;
import model.DAO.AreaCompartilhadaEdificioDAO;

public class AreaCompartilhadaEdificioService extends CrudService<AreaCompartilhadaEdificio> {
    public AreaCompartilhadaEdificioService() {
        super(new AreaCompartilhadaEdificioDAO());
    }
}
