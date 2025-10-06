package semana07.ejercicios;

import tdas.vector.Iterador;
import tdas.vector.Vector;

public class Ejercicio03 {

    public static void main(String[] args) {
        Vector<Integer> arr = new Vector<>();

        int x1 = 1;
        int x2 = 100;
        int rango = x2 - x1 + 1;

        for (int i = 0; i < 5; i++) {
            int numeroAleatorio = (int) (Math.random() * rango) + x1;
            arr.agregar(numeroAleatorio);
        }

        for (int i = 0; i < 1000; i++) {
            int randomNumber = (int) (Math.random() * rango) + x1;
            arr.agregar(randomNumber);
        }

        for (int i = 0; i < 500; i++) {
            arr.remover(i);
        }

        System.out.println(suma(arr));
    }


    /**
     * Devuelve la suma de los elementos del arr de enteros dado.
     * @param arr: el arr de números enteros.
     * @return la suma de los elementos del arr.
     */
    public static int suma(Vector<Integer> arr) {
        int res = 0;
        Iterador<Integer> it = arr.iterador();
        while (it.haySiguiente()) {
            res += it.verActual();
            it.siguiente();
        }
        return res;
    }
}
