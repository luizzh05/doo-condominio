package service;

import model.FuncaoMandato;
import model.DAO.FuncaoMandatoDAO;

public class FuncaoMandatoService extends CrudService<FuncaoMandato> {
    public FuncaoMandatoService() {
        super(new FuncaoMandatoDAO());
    }

    @Override
    public void validar(FuncaoMandato registro) {
        super.validar(registro);
        if (registro.getEdificio() == null || registro.getEdificio().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione Edificio.");
        }
        if (registro.getDataFim() != null && registro.getDataFim().isBefore(registro.getDataInicio())) {
            throw new IllegalArgumentException("DataFim nao pode ser anterior a DataInicio.");
        }
        if (registro.getSindicoProfissional() == null && registro.getProprietario() == null) {
            throw new IllegalArgumentException("Selecione um sindico profissional ou proprietario para o mandato.");
        }
    }
}
