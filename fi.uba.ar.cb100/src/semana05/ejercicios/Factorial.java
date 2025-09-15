package semana05.ejercicios;

/**
 * Clase que proporciona métodos para calcular factoriales utilizando
 * recursión de cola para optimización de memoria.
 */
public class Factorial {
    /**
     * Calcula el factorial de un número utilizando recursión de cola.
     *
     * @param n  Número entero no negativo para calcular su factorial
     * @return   Factorial de n (n!)
     * @example
     * Factorial.factorial(5); // Retorna 120
     * Factorial.factorial(0); // Retorna 1
     * Factorial.factorial(1); // Retorna 1
     */
    public static int factorial(int n) {
        return _factorial(n ,1);
    }

    /**
     * Metodo auxiliar privado que implementa la recursión de cola para el factorial.
     *
     * @param n            Número original (contador de iteraciones)
     * @param acumulador   Acumulador del resultado parcial
     * @return             Resultado final del factorial
     *
     * @implNote
     * Complejidad temporal: O(n)
     * Complejidad espacial: O(1) con optimización de cola
     */
    private static int _factorial(int n, int acumulador) {
        return (n == 0) ? acumulador : _factorial(n-1, acumulador * n);
    }
}
