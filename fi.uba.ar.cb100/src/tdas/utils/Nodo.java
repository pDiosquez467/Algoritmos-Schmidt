package tdas.utils;

/**
 * Representa un nodo simplemente enlazado genérico.
 * @param <T>: El tipo genérico del dato que guarda el nodo.
 */
public class Nodo<T> {
    private final T dato;
    private Nodo<T> prox;

    /**
     * Crea una instancia de Nodo.
     * @param dato: El dato almacenado en el nodo.
     * @param prox: La referencia al próximo nodo enlazado.
     */
    public Nodo(T dato, Nodo<T> prox) {
        this.dato = dato;
        this.prox = prox;
    }

    /**
     * Crea una instancia de Nodo con una referencia nula al próximo.
     * @param dato: El dato almacenado en el nodo.
     */
    public Nodo(T dato) {
        this(dato, null);
    }

    /**
     * Devuelve el dato almacenado en el nodo.
     * @return el dato almacenado.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Devuelve la referencia al nodo enlazado.
     * @return el próximo nodo enlazado.
     */
    public Nodo<T> getProx() {
        return prox;
    }
}
