package semana02.ejercicios.utils;

public class NumberHolder {
    private final int value;

    public NumberHolder(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}
