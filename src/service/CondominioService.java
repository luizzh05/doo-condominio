package service;

import model.Condominio;
import model.DAO.CondominioDAO;

public class CondominioService extends CrudService<Condominio> {
    public CondominioService() {
        super(new CondominioDAO());
    }

    @Override
    public void validar(Condominio registro) {
        super.validar(registro);
        if (registro.getUnidadeCondomino() == null || registro.getUnidadeCondomino().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione UnidadeCondomino.");
        }
        if (registro.getDataVencimento() != null && registro.getDataVencimento().isBefore(registro.getDataEmissao())) {
            throw new IllegalArgumentException("DataVencimento nao pode ser anterior a DataEmissao.");
        }
    }
}
