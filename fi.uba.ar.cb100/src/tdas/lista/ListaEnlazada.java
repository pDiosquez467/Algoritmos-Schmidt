package tdas.lista;

import tdas.utils.Nodo;

public class ListaEnlazada<T> {

    private class Nodo<T> {
        private final T dato;
        private Nodo<T> proximo;

        public Nodo(T dato, Nodo<T> proximo) {
            this.dato = dato;
            this.proximo = proximo;
        }

        public Nodo(T dato) {
            this(dato, null);
        }

        public T getDato() {
            return dato;
        }

        public Nodo<T> getProximo() {
            return proximo;
        }

        public void setProximo(Nodo<T> proximo) {
            this.proximo = proximo;
        }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantidad;

    /**
     * Crea una lista enlazada simple.
     * Invariante de representación:
     *  - O bien, 'primero' y 'ultimo' son ambos null (lista vacía) o bien ambos son not null (lista no vacía).
     */
    public ListaEnlazada() {
        this.primero  = null;
        this.ultimo   = null;
        this.cantidad = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     * @param dato: el elemento a agregar en la lista.
     * Complejidad: O(1)
     */
    public void append(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if(!this.estaVacia()) {
            this.primero = nuevo;
        } else {
            this.ultimo.setProximo(nuevo);
        }
        this.ultimo = nuevo;
        this.cantidad++;
    }

    /**
     * Inserta en el índice dado, el elemento dado.
     * @param index: el índice de inserción.
     * @param dato: el valor a insertar.
     * Complejidad: O(n), siendo n la cantidad de elementos de la lista.
     */
    public void insert(int index, T dato) {

    }

    /**
     * Elimina y devuelve el elemento en la última posición de la lista.
     * @return el último elemento de la lista (eliminado).
     * Complejidad: O(1)
     */
    public T pop() {
        return this.pop(this.cantidad-1);
    }

    /**
     * Elimina y devuelve el elemento en la posición dado.
     * @return el elemento eliminado de la lista.
     * Complejidad: O(n), siendo n la cantidad de elementos de la lista.
     */
    public T pop(int index) {
        return null;
    }

    /**
     * Remueve el elemento dado de la lista.
     * @param dato: el valor a remover.
     * Complejidad: O(n), siendo n la cantidad de elementos de la lista.
     */
    public T remove(T dato) {
        return null;
    }

    /**
     * Indica si la lista dada está vacía.
     * @return verdadero si la lista no tiene elementos.
     * Complejidad: O(1)
     */
    public boolean estaVacia() {
        return this.primero == null;
    }

    /**
     * Devuelve el largo de la lista, es decir, la cantidad de elementos que tiene.
     * @return la cantidad de elementos de la lista.
     * Complejidad: O(1)
     */
    public int len() {
        return this.cantidad;
    }
}
