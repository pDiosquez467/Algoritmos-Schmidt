package semana05.ejercicios;

/**
 * Clase que proporciona métodos para calcular el producto de dos números
 * utilizando recursión de cola para optimización de memoria.
 */
public class Producto {

    /**
     * Calcula el producto de dos números enteros utilizando recursión de cola.
     *
     * @param factor  Primer factor de la multiplicación
     * @param otro    Segundo factor de la multiplicación
     * @return        Resultado de la multiplicación factor * otro
     * @example:
     * Producto.producto(5, 3); // Retorna 15
     * Producto.producto(4, 0); // Retorna 0
     * Producto.producto(0, 7); // Retorna 0
     */

    public static int producto(int factor, int otro) {
        return _producto(factor, otro, 0);
    }

    /**
     * Metodo auxiliar privado que implementa la recursión de cola para la multiplicación.
     *
     * @param factor       Primer factor de la multiplicación
     * @param otro         Segundo factor (contador de iteraciones)
     * @param acumulador   Acumulador del resultado parcial
     * @return             Resultado final de la multiplicación
     *
     * @implNote
     * Complejidad temporal: O(n) donde n es el valor absoluto de 'otro'
     * Complejidad espacial: O(1) con optimización de cola
     */
    private static int _producto(int factor, int otro, int acumulador) {
        return (otro == 0) ? acumulador : _producto(factor, otro - 1, acumulador + factor);
    }
}
