package semana02.ejercicios.utils;

public class Swap {

    public static void swap(int a, int b) {
        int aux = a;
        a = b;
        b = a;
    }

    public static void swap(NumberHolder a, NumberHolder b) {
        NumberHolder aux = a;
        a = b;
        b = aux;
    }
}
