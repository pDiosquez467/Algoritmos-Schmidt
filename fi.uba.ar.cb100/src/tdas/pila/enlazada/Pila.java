package tdas.pila.enlazada;

import tdas.pila.excepciones.PilaException;
import tdas.utils.Nodo;

/**
 * Representa una pila genérica.
 * Cumple con la interfaz básica del TDA Pila. Todas las operaciones en O(1).
 * @param <T>
 */
public class Pila<T> {
    private Nodo<T> tope;

    /**
     * Crea una pila genérica.
     */
    public Pila() {
        this.tope = null;
    }

    /**
     * Agrega un elemento en el tope de la pila dada.
     * @param dato: elemento a apilar.
     */
    public void apilar(T dato) {
        this.tope = new Nodo<T>(dato, this.tope);
    }

    /**
     * Desapila y devuelve el dato en el tope de la pila.
     * @return el dato del tope de la pila.
     * @throws PilaException si la pila está vacía.
     */
    public T desapilar() throws PilaException {
        if (this.estaVacia()) {
            throw new PilaException("Pila vacía");
        }
        T dato = this.tope.getDato();
        this.tope = this.tope.getProx();
        return dato;
    }

    /**
     * Devuelve el dato del elemento que está en el tope de la pila.
     * @return el dato del tope de la pila.
     * @throws PilaException si la pila está vacía.
     */
    public T verTope() throws PilaException {
        if (this.estaVacia()) {
            throw new PilaException("Pila vacía");
        }
        return this.tope.getDato();
    }

    /**
     * Indica si la pila está vacía.
     * @return true si la pila está vacía. En caso contrario, retorna false.
     */
    public boolean estaVacia() {
        return this.tope == null;
    }
}
