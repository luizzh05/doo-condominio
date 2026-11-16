package service;

import model.Fornecedor;
import model.DAO.FornecedorDAO;

public class FornecedorService extends CrudService<Fornecedor> {
    public FornecedorService() {
        super(new FornecedorDAO());
    }

    @Override
    public void validar(Fornecedor registro) {
        super.validar(registro);
        PessoaValidation.validar(registro);
    }
}
