package service;

import model.Leitura;
import model.DAO.LeituraDAO;

public class LeituraService extends CrudService<Leitura> {
    public LeituraService() {
        super(new LeituraDAO());
    }
}
