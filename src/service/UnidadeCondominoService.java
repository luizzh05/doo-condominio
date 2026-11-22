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
            throw new IllegalArgumentException("Busque e selecione a unidade.");
        }
        if (registro.getProprietario() == null || registro.getProprietario().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione o proprietário.");
        }
        if (registro.getDataVenda() != null && registro.getDataVenda().isBefore(registro.getDataAquisicao())) {
            throw new IllegalArgumentException("A data de venda não pode ser anterior à data de aquisição.");
        }
    }
}
