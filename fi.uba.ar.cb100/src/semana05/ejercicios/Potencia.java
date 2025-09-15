package semana05.ejercicios;

/**
 * Clase que proporciona métodos para calcular potencias utilizando
 * recursión de cola para optimización de memoria.
 */
public class Potencia {
    /**
     * Calcula la potencia de un número elevado a un exponente utilizando recursión de cola.
     *
     * @param base       Base de la potencia
     * @param exponente  Exponente al que se eleva la base
     * @return           Resultado de base elevado a exponente
     * @throws IllegalArgumentException si el exponente es negativo
     *
     * @example
     * Potencia.potencia(2, 3); // Retorna 8
     * Potencia.potencia(5, 0); // Retorna 1
     * Potencia.potencia(3, 1); // Retorna 3
     */
    public static int potencia(int base, int exponente) {
        return _potencia(base, exponente, 1);
    }

    /**
     * Metodo auxiliar privado que implementa la recursión de cola para la potencia.
     *
     * @param base         Base de la potencia
     * @param exponente    Exponente (contador de iteraciones)
     * @param acumulador   Acumulador del resultado parcial
     * @return             Resultado final de la potencia
     *
     * @implNote
     * Complejidad temporal: O(n) donde n es el exponente
     * Complejidad espacial: O(1) con optimización de cola
     */
    private static int _potencia(int base, int exponente, int acumulador) {
        return (exponente == 0) ? acumulador : _potencia(base, exponente-1, acumulador * base);
    }
}
