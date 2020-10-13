package Enums;

public enum Size {

    SMALL, MEDIUM, LARGE, KING;

    public String toString(Size size) {
        switch (size) {
            case SMALL -> {
                return "Pequena";
            }
            case MEDIUM -> {
                return "Média";
            }
            case LARGE -> {
                return "Grande";
            }
            case KING -> {
                return "Rei";
            }
            default -> {
                return "Média";
            }
        }
    }
}
