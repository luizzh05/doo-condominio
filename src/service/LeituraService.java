package service;

import model.Leitura;
import model.DAO.LeituraDAO;

public class LeituraService extends CrudService<Leitura> {
    public LeituraService() {
        super(new LeituraDAO());
    }

    @Override
    public void validar(Leitura registro) {
        super.validar(registro);
        if (registro.getUnidadeCondomino() == null || registro.getUnidadeCondomino().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione UnidadeCondomino.");
        }
    }
}
