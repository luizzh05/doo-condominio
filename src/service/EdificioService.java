package service;

import java.math.BigDecimal;

import model.Edificio;
import model.DAO.EdificioDAO;

public class EdificioService extends CrudService<Edificio> {
    public EdificioService() {
        super(new EdificioDAO());
    }

    @Override
    public void validar(Edificio registro) {
        super.validar(registro);
        validarPreco(registro.getPrecoM3Agua(), "agua");
        validarPreco(registro.getPrecoM3Gas(), "gas");
    }

    private static void validarPreco(BigDecimal preco, String tipo) {
        if (preco == null || preco.signum() < 0 || preco.scale() > 4 || preco.precision() - preco.scale() > 8) {
            throw new IllegalArgumentException("Informe um preco por m3 valido para " + tipo + ".");
        }
    }
}
