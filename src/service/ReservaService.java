package service;

import model.Reserva;
import model.DAO.ReservaDAO;

public class ReservaService extends CrudService<Reserva> {
    public ReservaService() {
        super(new ReservaDAO());
    }

    @Override
    public void validar(Reserva registro) {
        super.validar(registro);
        if (registro.getAreaCompartilhadaEdificio() == null || registro.getAreaCompartilhadaEdificio().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione AreaCompartilhadaEdificio.");
        }
        if (registro.getUnidadeCondomino() == null || registro.getUnidadeCondomino().getId() <= 0) {
            throw new IllegalArgumentException("Busque e selecione UnidadeCondomino.");
        }
        if (registro.getDataHoraInicio() == null || registro.getDataHoraFim() == null
                || !registro.getDataHoraFim().isAfter(registro.getDataHoraInicio())) {
            throw new IllegalArgumentException("O fim da reserva deve ser posterior ao inicio.");
        }
    }
}
