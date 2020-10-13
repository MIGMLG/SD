package Enums;

public enum Unit {

    GRAMS, LITERS, UNITS;

    public String toString(Unit unit) {
        switch (unit) {
            case GRAMS:
                return "Gramas";
            case LITERS:
                return "Litros";
            case UNITS:
                return "Unidades";
            default:
                return "Unidade desconhecida";
        }
    }
}
