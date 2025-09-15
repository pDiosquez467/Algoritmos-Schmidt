package tdas.lista;

import tdas.utils.Nodo;

import java.util.NoSuchElementException;

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
        if(this.primero == null) {
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
        if (index > this.len()) {
            throw new IndexOutOfBoundsException("Fuera de índice");
        }

        if (index < 0) {
            index = this.len() + index;
        }

        Nodo<T> ant = null, act = this.primero;
        for (int i = 0; i < index; i++) {
            ant = act;
            act = act.getProximo();
        }

        Nodo<T> nuevo = new Nodo<>(dato, act);
        if (ant == null) {
            this.primero = nuevo;
            if (this.ultimo == null) {
                this.ultimo = nuevo;
            }
        } else {
            ant.setProximo(nuevo);
            if (act == null) {
                this.ultimo = nuevo;
            }
        }
        this.cantidad++;
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
        if (index > this.len()) {
            throw new IndexOutOfBoundsException("Fuera de índice");
        }

        if (index < 0) {
            index = this.len() + index;
        }

        Nodo<T> ant = null, act = this.primero;
        for (int i = 0; i < index; i++) {
            ant = act;
            act = act.getProximo();
        }
        T eliminado = act.dato;

        if (ant == null) {
            this.primero = act.proximo;
            if (this.primero == null) {
                this.ultimo = null;
            }
        } else {
            ant.proximo = act.proximo;
            if (act.proximo == null) {
                this.ultimo = ant;
            }
        }
        this.cantidad--;
        return eliminado;
    }

    /**
     * Remueve el elemento dado de la lista.
     * @param dato: el valor a remover.
     * Complejidad: O(n), siendo n la cantidad de elementos de la lista.
     */
    public T remove(T dato) {
        Nodo<T> ant = null, act = this.primero;
        while (act != null) {
            if (act.dato.equals(dato)) {
                T removido = act.dato;
                Nodo<T> proximo = act.proximo;
                if (ant == null) {
                    this.primero = proximo;
                    if (this.primero == null) {
                        this.ultimo = null;
                    }
                } else {
                    ant.setProximo(proximo);
                    if (proximo == null) {
                        this.ultimo = ant;
                    }
                }
                this.cantidad--;
                return dato;
            }
            ant = act;
            act = act.getProximo();
        }
        throw new NoSuchElementException("El valor no pertenece a la lista");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> act = this.primero;
        while (act.getProximo() != null) {
            sb.append(act.getDato()).append(", ");
            act = act.getProximo();
        }
        sb.append(act.getDato()).append("]");
        return sb.toString();
    }
}
