package semana07.ejercicios;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import tdas.pila.teorica.Pila;

import java.util.*;

public class Ejercicio08 {

    public static void main(String[] args) {
        System.out.println("=== Pruebas de la función invertirLista ===");
        System.out.println("==========================================");

        var listaNumeros = Arrays.asList(12, 32, 45, 6, 76, 8);
        System.out.println("1. Lista Original (Números): " + listaNumeros);
        var invertidaNumeros = invertirLista(listaNumeros);
        System.out.println("   Lista Invertida: " + invertidaNumeros);

        List<Integer> esperadoNumeros = Arrays.asList(8, 76, 6, 45, 32, 12);
        boolean test1 = Objects.equals(invertidaNumeros, esperadoNumeros);
        System.out.println("   Resultado Correcto? " + (test1 ? "✅ SÍ" : "❌ NO") + "\n");

        // --- Caso 2: Lista Vacía ---
        var listaVacia = Collections.<String>emptyList();
        System.out.println("2. Lista Original (Vacía): " + listaVacia);
        var invertidaVacia = invertirLista(listaVacia);
        System.out.println("   Lista Invertida: " + invertidaVacia);
        boolean test2 = invertidaVacia.isEmpty();
        System.out.println("   Resultado Correcto? " + (test2 ? "✅ SÍ" : "❌ NO") + "\n");
    }

    /**
     * Dada una lista de elementos, devuelve una nueva lista con los
     * elementos de la original en el orden inverso, utilizando una Pila
     * temporal para lograr el efecto LIFO (Last-In, First-Out).
     *
     * @param <T> El tipo de los elementos en la lista.
     * @param lista La lista de elementos original a invertir.
     * @return Una nueva lista con los elementos de la lista original en orden inverso.
     * Devuelve una lista vacía si la lista original es nula.
     */
    public static <T> List<T> invertirLista(List<T> lista) {
        if (lista == null) {
            return new ArrayList<>();
        }

        List<T> invertida = new ArrayList<>(lista.size());
        Pila<T> temp = new Pila<>();

        for (T elemento : lista) {
            temp.apilar(elemento);
        }

        while (!temp.estaVacia()) {
            invertida.add(temp.desapilar());
        }

        return invertida;
    }
}
