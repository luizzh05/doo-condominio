package service;

import model.CustoNivel2;
import model.DAO.CustoNivel2DAO;

public class CustoNivel2Service extends CrudService<CustoNivel2> {
    public CustoNivel2Service() {
        super(new CustoNivel2DAO());
    }
}
