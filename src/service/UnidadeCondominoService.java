package service;

import model.UnidadeCondomino;
import model.DAO.UnidadeCondominoDAO;

public class UnidadeCondominoService extends CrudService<UnidadeCondomino> {
    public UnidadeCondominoService() {
        super(new UnidadeCondominoDAO());
    }

    @Override
    public void validar(UnidadeCondomino registro) {
        super.validar(registro);
        if (registro.getUnidade() == null || registro.getUnidade().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione Unidade.");
        }
        if (registro.getProprietario() == null || registro.getProprietario().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione Proprietario.");
        }
        if (registro.getDataVenda() != null && registro.getDataVenda().isBefore(registro.getDataAquisicao())) {
            throw new IllegalArgumentException("DataVenda nao pode ser anterior a DataAquisicao.");
        }
    }
}
