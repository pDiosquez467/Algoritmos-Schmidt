package semana05.ejercicios;

/**
 * Clase que proporciona métodos para calcular números de la secuencia Fibonacci
 * utilizando recursión de cola para optimización de memoria.
 * La secuencia Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */
public class Fibonacci {

    /**
     * Calcula el n-ésimo número de la secuencia Fibonacci utilizando recursión de cola.
     *
     * @param n  Posición en la secuencia Fibonacci (n ≥ 0)
     * @return   El n-ésimo número de la secuencia Fibonacci
     * @throws IllegalArgumentException si n es negativo
     *
     * @example
     * Fibonacci.fibonacci(0); // Retorna 0
     * Fibonacci.fibonacci(1); // Retorna 1
     * Fibonacci.fibonacci(5); // Retorna 5
     * Fibonacci.fibonacci(7); // Retorna 13
     */
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n no puede ser negativo");
        }
        return go(n, 0, 1);
    }

    /**
     * Metodo auxiliar privado que implementa la recursión de cola para Fibonacci.
     * Utiliza dos acumuladores para mantener el estado actual y siguiente de la secuencia.
     *
     * @param n           Posición restante en la secuencia (contador de iteraciones)
     * @param actual      Número Fibonacci actual (Fₙ)
     * @param siguiente   Número Fibonacci siguiente (Fₙ₊₁)
     * @return            El n-ésimo número de la secuencia Fibonacci
     *
     * @implNote
     * Complejidad temporal: O(n)
     * Complejidad espacial: O(1) con optimización de cola
     *
     * @observation
     * El algoritmo utiliza el enfoque de "acumuladores paralelos" donde:
     * - actual representa Fₙ en cada iteración
     * - siguiente representa Fₙ₊₁ en cada iteración
     * - En cada paso recursivo, se avanza en la secuencia:
     *   actual ← siguiente
     *   siguiente ← actual + siguiente
     */
    private static int go(int n, int actual, int siguiente) {
        if (n == 0) {
            return actual;
        }
        if (n == 1) {
            return siguiente;
        }

        return go(n - 1, siguiente, actual + siguiente);
    }
}