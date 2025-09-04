package semana02.ejercicios;

public class Ejercicio05 {

    public static void main(String[] args) {
        try {
            System.out.println(factorial(50_000)); // valor muy grande
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow alcanzado!");
        }
    }

    public static int factorial(int numero) {
        if (numero == 0) {
            return 1;
        }
        return factorial(numero - 1) * numero;
    }
}
