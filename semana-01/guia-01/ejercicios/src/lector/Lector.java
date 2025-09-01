package ejercicios.lector;

import java.util.Scanner;

public class Lector {
    static Scanner lector;

    public static void inicializar() {
        lector = new Scanner(System.in);
    }

    public static String leer() {
        return lector.next();
    }

    public static int leerEntero() {
        return lector.nextInt();
    }

    public static double leerReal() {
        return lector.nextDouble();
    }

    public static void finalizar() {
        lector.close();
    }
}
