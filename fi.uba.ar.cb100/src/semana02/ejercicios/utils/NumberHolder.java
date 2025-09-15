package semana02.ejercicios.utils;

public record NumberHolder(int value) {

    @Override
    public String toString() {
        return "value=" + value;
    }
}
