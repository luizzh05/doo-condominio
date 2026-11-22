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
            throw new IllegalArgumentException("Busque e selecione o edifício.");
        }
        if (registro.getDataFim() != null && registro.getDataFim().isBefore(registro.getDataInicio())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }
        if (registro.getSindicoProfissional() == null && registro.getProprietario() == null) {
            throw new IllegalArgumentException("Selecione um síndico profissional ou proprietário para o mandato.");
        }
    }
}
