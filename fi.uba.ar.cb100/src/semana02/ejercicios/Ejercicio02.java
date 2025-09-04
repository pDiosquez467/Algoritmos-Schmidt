package semana02.ejercicios;

import semana02.ejercicios.utils.NumberHolder;
import semana02.ejercicios.utils.Swap;

public class Ejercicio02 {
    public static void main(String[] args) {
        NumberHolder a = new NumberHolder(10);
        NumberHolder b = new NumberHolder(12);

        System.out.println("Antes.");
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        /*
        En Java, los objetos también se pasan por valor, pero lo que se copia es la referencia al objeto.
        Eso significa que podés modificar el estado interno del objeto, pero no podés reasignar la referencia
        dentro del metodo y esperar que afecte afuera.

        Swap.swap(a, b) -> Eso lo que hace es cambiar las referencias locales dentro del metodo, pero no afecta
        a las variables de main.
        En main, a y b siguen apuntando a los mismos objetos que antes.
        */
        Swap.swap(a, b);

        System.out.println("\nDespués.");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
}
