package ejercicios;

import ejercicios.consola.Consola;
import ejercicios.lector.Lector;

class Ejercicio01 {

    public static void main(String[] args) {
        Consola.imprimir("Ingrese un número entero:");
        Lector.inicializar();
        Consola.imprimir("Número = " + Lector.leerEntero());
        Lector.finalizar();
    }
}