package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.List;
import model.Condominio;
import model.Edificio;

public class PreviaGeracaoCondominio {

    private final Edificio edificio;
    private final YearMonth referencia;
    private final List<Condominio> condominios;
    private final List<String> avisos;

    public PreviaGeracaoCondominio(Edificio edificio, YearMonth referencia, List<Condominio> condominios, List<String> avisos) {
        this.edificio = edificio;
        this.referencia = referencia;
        this.condominios = List.copyOf(condominios);
        this.avisos = List.copyOf(avisos);
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public YearMonth getReferencia() {
        return referencia;
    }

    public List<Condominio> getCondominios() {
        return condominios;
    }

    public List<String> getAvisos() {
        return avisos;
    }

    public BigDecimal getValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Condominio c : condominios) {
            total = total.add(BigDecimal.valueOf(c.getValorEmitido()));
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
