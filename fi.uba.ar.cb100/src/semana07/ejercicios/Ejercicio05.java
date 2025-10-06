package semana07.ejercicios;

import tdas.cola.Cola;

/**
 * El punto de entrada principal del programa.
 * Demuestra el uso de la clase Cola (Queue) personalizada,
 * encolando varios enteros y luego desencolándolos uno por uno
 * para verificar el comportamiento FIFO (First-In, First-Out).
 *
 */
public class Ejercicio05 {

    public static void main(String[] args) {
        Cola<Integer> colaEnteros = new Cola<Integer>();

        System.out.println("--- Encolando elementos ---");
        colaEnteros.encolar(12);
        colaEnteros.encolar(4);
        colaEnteros.encolar(21);
        colaEnteros.encolar(-31);
        colaEnteros.encolar(46);
        System.out.println("Elementos encolados: 12, 4, 21, -31, 46");
        System.out.println("--------------------------");

        System.out.println("--- Desencolando elementos (FIFO) ---");
        while (!colaEnteros.estaVacia()) {
            System.out.println("Actual primero desencolado: " + colaEnteros.desencolar());
        }
        System.out.println("------------------------------------");
    }
}
