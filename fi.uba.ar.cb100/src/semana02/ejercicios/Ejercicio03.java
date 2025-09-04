package semana02.ejercicios;

public class Ejercicio03 {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(12);
        Integer b = Integer.valueOf(12);

        // == compara referencias, no valores.
        //Pero… para enteros pequeños (-128 a 127), Java guarda en caché los Integer, así que a y b terminan
        // apuntando al mismo objeto → true.
        Boolean iguales = a == b;
        System.out.println("Iguales con '=': " + iguales);

        // .equals compara el valor numérico → siempre true si son iguales.
        Boolean igualesConEquals = a.equals(b);
        System.out.println("Iguales con 'equals': " + igualesConEquals);
    }
}
