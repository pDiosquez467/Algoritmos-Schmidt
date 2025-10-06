package semana07.ejercicios;

import tdas.pila.teorica.Pila;

/**
 *  El punto de entrada principal del programa.
 *  Demuestra el uso de la clase Pila (Stack) personalizada,
 *  apilando varios enteros y luego desapilándolos uno por uno
 *  para verificar el comportamiento LIFO (Last-In, First-Out).
 *
 */
public class Ejercicio07 {

    public static void main(String[] args) {
        var pilaDeEnteros = new Pila<Integer>();

        System.out.println("== Apilando elementos ==");
        pilaDeEnteros.apilar(12);
        pilaDeEnteros.apilar(32);
        pilaDeEnteros.apilar(43);
        pilaDeEnteros.apilar(54);
        pilaDeEnteros.apilar(91);
        System.out.println("Elementos apilados (en orden de entrada): 12, 32, 43, 54, 91");
        System.out.println("========================");

        System.out.println("== Desapilando elementos (LIFO) ==");
        while (!pilaDeEnteros.estaVacia()) {
            System.out.println("Elemento desapilado: " + pilaDeEnteros.desapilar());
        }
        System.out.println("==================================");
    }
}
