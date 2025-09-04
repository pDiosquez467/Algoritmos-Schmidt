package semana02.ejercicios;

import semana02.ejercicios.utils.Swap;

public class Ejercicio01 {
    public static void main(String[] args) {
        int a = 10, b = 12;

        System.out.println("Antes.");
        System.out.println("a: " + a);
        System.out.println("b: " + b);

        /*
        Los tipos primitivos en Java se pasan siempre por valor.
        Es decir, cuando llamás a Swap.swap(a, b), en realidad lo que recibe el metodo son copias de a y b.
        Cambiar esas copias no afecta a las variables originales en main.

        Por eso, después del swap, a y b siguen iguales.
        * */
        Swap.swap(a, b);

        System.out.println("\nDespués.");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }

}
