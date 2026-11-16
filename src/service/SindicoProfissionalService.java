package service;

import model.SindicoProfissional;
import model.DAO.SindicoProfissionalDAO;

public class SindicoProfissionalService extends CrudService<SindicoProfissional> {
    public SindicoProfissionalService() {
        super(new SindicoProfissionalDAO());
    }

    @Override
    public void validar(SindicoProfissional registro) {
        super.validar(registro);
        PessoaValidation.validar(registro);
    }
}
