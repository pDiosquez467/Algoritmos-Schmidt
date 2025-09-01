package ejercicios;

import ejercicios.consola.Consola;

public class Ejercicio25 {

    public static void main(String[] args) {
        Consola.imprimir("Corriendo tests...");

        // Primos básicos
        assert esPrimo(2);
        assert esPrimo(3);
        assert esPrimo(5);

        // Primos grandes
        assert esPrimo(19);
        assert esPrimo(467);
        assert esPrimo(997);

        // Casos especiales negativos
        assert esPrimo(-2);
        assert esPrimo(-3);
        assert esPrimo(-101);

        // No primos básicos
        assert !esPrimo(1);
        assert !esPrimo(4);
        assert !esPrimo(6);

        // No primos grandes
        assert !esPrimo(468);
        assert !esPrimo(999);

        // Casos especiales negativos no primos
        assert !esPrimo(-1);
        assert !esPrimo(-4);
        assert !esPrimo(-18);

        // Ceros y extremos
        assert !esPrimo(0);
        assert !esPrimo(-0);

        Consola.imprimir("Todo OK!");
    }

    public static Boolean esPrimo(int numero) {
        int abs = Math.abs(numero);
        return abs > 1 && !tieneDivisoresPropios(abs);
    }

    private static Boolean tieneDivisoresPropios(int numero) {
        for (int i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) return true;
        }
        return false;
    }
}
