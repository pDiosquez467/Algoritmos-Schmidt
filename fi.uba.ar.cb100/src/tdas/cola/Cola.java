package tdas.cola;

import tdas.utils.Nodo;

/**
 * Representa una cola genérica de elementos (Estructura FIFO).
 * Invariante de representación:
 * -- Si la cola está vacía, primero y último son null.
 * -- Si la cola no está vacía, primero y último no son null.
 * @param <T>
 */
public class Cola<T> {
    private Nodo<T> primero;
    private Nodo<T> ultimo;

    /**
     * Crea una nueva cola genérica.
     */
    public Cola() {
        this.primero = null;
        this.ultimo  = null;
    }

    /**
     * Agrega un nuevo elemento a la cola, en el último lugar.
     * @param dato: El nuevo elemento agregado.
     */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (this.estaVacia()) {
            this.primero = nuevo;
        } else {
            this.ultimo.setProx(nuevo);
        }
        this.ultimo = nuevo;
    }

    /**
     * Desencola y devuelve el primer elemento de la cola.
     * @return el primer elemento de la cola.
     * @throws RuntimeException si la cola está vacía.
     */
    public T desencolar() {
        if (this.estaVacia()) {
            throw new RuntimeException("Cola vacía");
        }
        T dato = this.primero.dato();
        this.primero = this.primero.proximo();

        if (this.primero == null) {
            this.ultimo = null;
        }

        return dato;
    }

    /**
     * Devuelve el elemento que está en el primer lugar en la cola.
     * @return el primer elemento.
     * @throws RuntimeException si la cola está vacía.
     */
    public T verPrimero() {
        if (this.estaVacia()) {
            throw new RuntimeException("Cola vacía");
        }
        return this.primero.dato();
    }

    /**
     * Indica si la cola está vacía.
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.primero == null;
    }
}
