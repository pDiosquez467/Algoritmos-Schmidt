package semana07.ejercicios;

import java.util.List;
import java.util.Objects;


public class Ejercicio02 {

    /**
     * Cuenta la cantidad de apariciones del número dado en la lista
     * de números dada.
     * @param numeros: lista de números donde buscar.
     * @param numero: número buscado. Puede ser null.
     * @return número de apariciones del número en la lista.
     */
    public static int contarApariciones(List<Integer> numeros, Integer numero) {
        return (int) numeros.stream()
                .filter(n -> Objects.equals(n, numero))
                .count();
    }
}