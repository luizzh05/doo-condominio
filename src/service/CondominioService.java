package service;

import model.Condominio;
import model.DAO.CondominioDAO;

public class CondominioService extends CrudService<Condominio> {
    private final CondominioDAO condominioDAO;

    public CondominioService() {
        this(new CondominioDAO());
    }

    private CondominioService(CondominioDAO dao) {
        super(dao);
        this.condominioDAO = dao;
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
        int mes = registro.getMesReferencia();
        int ano = registro.getAnoReferencia();
        if (condominioDAO.existeParaUnidadeReferencia(registro.getUnidadeCondomino().getId(), mes, ano, registro.getId())) {
            throw new IllegalArgumentException(String.format("Ja existe condominio para esta unidade em %02d/%04d.", mes, ano));
        }
    }
}
