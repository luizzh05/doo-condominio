package service;

public final class FormulaRateio {

    public static final String POR_UNIDADE = "Por Und.";
    public static final String POR_M2 = "Por m²";

    private FormulaRateio() {
    }

    public static boolean valida(String formula) {
        return POR_UNIDADE.equals(formula) || POR_M2.equals(formula);
    }
}
