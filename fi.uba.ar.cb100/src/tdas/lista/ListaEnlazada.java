package tdas.lista;

import tdas.utils.Nodo;

import java.util.NoSuchElementException;

/**
 * Representa una lista de elementos genéricos.
 * @param <T>: El tipo de los elementos de la lista.
 */
public class ListaEnlazada<T> {

    /**
     * Representa un nodo interno de la lista.
     * @param <T>: El tipo de los elementos del nodo (coincide con el de la lista)
     */
    private class Nodo<T> {
        private final T dato;
        private Nodo<T> proximo;

        public Nodo(T dato, Nodo<T> proximo) {
            this.dato = dato;
            this.proximo = proximo;
        }
    }

    /**
     * Atributos de la lista.
     */
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
        Nodo<T> nuevo = new Nodo<>(dato, null);
        if(this.primero == null) {
            this.primero = nuevo;
        } else {
            this.ultimo.proximo = nuevo;
        }
        this.ultimo = nuevo;
        this.cantidad++;
    }

    /**
     * Inserta en el índice dado, el elemento dado.
     * @param index el índice de inserción.
     *  - Si es negativo, se interpreta como desplazamiento desde el final.
     *  - Ejemplo: -1 inserta antes del último elemento.
     * @param dato el valor a insertar.
     * @throws IndexOutOfBoundsException si el índice es mayor que la longitud de la lista.
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
            act = act.proximo;
        }

        Nodo<T> nuevo = new Nodo<>(dato, act);
        if (ant == null) {
            this.primero = nuevo;
            if (this.ultimo == null) {
                this.ultimo = nuevo;
            }
        } else {
            ant.proximo = nuevo;
            if (act == null) {
                this.ultimo = nuevo;
            }
        }
        this.cantidad++;
    }

    /**
     * Elimina y devuelve el elemento en la última posición de la lista.
     * @return el último elemento de la lista (eliminado).
     * Complejidad: O(n), siendo n la cantidad de elementos de la lista.
     * Nota: Aunque se dispone de un puntero al último nodo, al ser una lista simplemente enlazada
     * es necesario recorrerla para actualizar el puntero al penúltimo nodo.
     */
    public T pop() {
        return this.pop(this.cantidad-1);
    }

    /**
     * Elimina y devuelve el elemento en la posición dada.
     * @param index el índice del elemento a eliminar.
     *  - Si es negativo, se interpreta como desplazamiento desde el final.
     *  - Ejemplo: -1 elimina el último elemento.
     * @return el elemento eliminado de la lista.
     * @throws IndexOutOfBoundsException si el índice es mayor que la longitud de la lista.
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
            act = act.proximo;
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
                    ant.proximo = proximo;
                    if (proximo == null) {
                        this.ultimo = ant;
                    }
                }
                this.cantidad--;
                return removido;
            }
            ant = act;
            act = act.proximo;
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
        if (this.primero == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> act = this.primero;
        while (act.proximo != null) {
            sb.append(act.dato).append(", ");
            act = act.proximo;
        }
        sb.append(act.dato).append("]");
        return sb.toString();
    }
}
