package service;

import model.CustoNivel1;
import model.DAO.CustoNivel1DAO;

public class CustoNivel1Service extends CrudService<CustoNivel1> {
    public CustoNivel1Service() {
        super(new CustoNivel1DAO());
    }
}
