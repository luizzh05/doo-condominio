package service;

import model.AreaCompartilhada;
import model.DAO.AreaCompartilhadaDAO;

public class AreaCompartilhadaService extends CrudService<AreaCompartilhada> {
    public AreaCompartilhadaService() {
        super(new AreaCompartilhadaDAO());
    }

    @Override
    public void validar(AreaCompartilhada registro) {
        super.validar(registro);
        CadastroValidation.validarTexto(registro.getDescricao(), registro.getObservacao(), registro.getStatus());
    }
}
