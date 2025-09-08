package semana03.ejercicios.nivel01.fraccion;

public class Main {
    public static void main(String[] args) {
        Fraccion unaFraccion = new Fraccion(6, 5);
        Fraccion otraFraccion = new Fraccion(4, 5);

        System.out.println("Fracción 1: " + unaFraccion);
        System.out.println("Fracción 2: " + otraFraccion);

        System.out.println("\nOperaciones:");
        System.out.println("Suma: " + unaFraccion.sumar(otraFraccion));
        System.out.println("Resta: " + unaFraccion.restar(otraFraccion));
        System.out.println("Multiplicación: " + unaFraccion.multiplicar(otraFraccion));
        System.out.println("División: " + unaFraccion.dividir(otraFraccion));

        System.out.println("\nComparaciones:");
        System.out.println("¿Es menor que?: " + unaFraccion.menorQue(otraFraccion));
        System.out.println("¿Es mayor que?: " + unaFraccion.mayorQue(otraFraccion));

        System.out.println("\nValores decimales:");
        System.out.println("Primera fracción como decimal: " + unaFraccion.obtenerDecimal());
        System.out.println("Segunda fracción como decimal: " + otraFraccion.obtenerDecimal());

        Fraccion terceraFraccion = new Fraccion(6, 5);
        System.out.println("\nComparación de igualdad:");
        System.out.println("¿Son iguales primera y segunda?: " + unaFraccion.equals(otraFraccion));
        System.out.println("¿Son iguales primera y tercera?: " + unaFraccion.equals(terceraFraccion));
    }
}