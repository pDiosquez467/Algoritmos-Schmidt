package ejercicios;

import ejercicios.consola.Consola;
import ejercicios.lector.Lector;

public class Ejercicio18 {

    public static void main(String[] args) {
        int numero, otro;
        Lector.inicializar();
        Consola.imprimir("Ingrese dos números para obtener el producto entre ellos...");
        Consola.imprimir("El primer número:");
        numero = Lector.leerEntero();
        Consola.imprimir("El otro:");
        otro = Lector.leerEntero();
        Lector.finalizar();
        Consola.imprimir("Resultado: " + productoEntre(numero, otro));
    }

    public static int productoEntre(int numero, int otro) {
        int res = 0;
        for (int i = 0; i < otro; i++) {
            res += numero;
        }
        return res;
    }
}
