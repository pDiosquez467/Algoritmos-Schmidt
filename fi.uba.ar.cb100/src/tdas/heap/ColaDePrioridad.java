package tdas.heap;

public interface ColaDePrioridad<T> {

    /**
     * post: Indica si la cola está vacía.
     * @return verdadero si la cola no tiene elementos.
     */
    boolean estaVacia();

    /**
     * post: Agrega un elemento a la cola.
     * @param elemento: el elemento a agregar.
     */
    void encolar(T elemento);

    /**
     * post: Devuelve el elemento con máxima prioridad.
     * @throws RuntimeException si la cola está vacía.
     * @return el elemento con máxima prioridad.
     */
    T verMaximo();

    /**
     * Elimina el elemento con máxima prioridad y lo devuelve.
     * @throws RuntimeException si la cola está vacía.
     * @return el elemento con máxima prioridad.
     */
    T desencolar();

    /**
     * post: Devuelve la cantidad de elementos que hay en la cola de prioridad.
     * @return la cantidad de elementos de la cola.
     */
    int cantidad();
}
