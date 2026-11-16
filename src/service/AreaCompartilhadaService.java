package service;

import model.AreaCompartilhada;
import model.DAO.AreaCompartilhadaDAO;

public class AreaCompartilhadaService extends CrudService<AreaCompartilhada> {
    public AreaCompartilhadaService() {
        super(new AreaCompartilhadaDAO());
    }
}
