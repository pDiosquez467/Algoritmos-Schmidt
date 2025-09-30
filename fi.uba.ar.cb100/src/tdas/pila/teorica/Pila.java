package tdas.pila.teorica;

import tdas.lista.teorica.simple.ListaSimplementeEnlazada;
import tdas.lista.teorica.simple.it.IteradorLista;
import tdas.utils.Nodo;

public class Pila<T> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private Nodo<T> tope;
    private int tamanio;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Inicializa una pila vacía.
     */
    public Pila() {
        this.tope    = null;
        this.tamanio = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Agrega el dato a la pila.
     * @param dato: el elemento a agregar.
     */
    public void apilar(T dato) {
        tope = new Nodo<>(dato, tope);
        tamanio++;
    }

    /**
     * Agrega cada uno de los elementos de la lista dada en la pila.
     * @param lista lista de elementos a agregar a la pila.
     */
    public void apilar(ListaSimplementeEnlazada<T> lista) {
        IteradorLista<T> it = lista.iterador();
        while (it.haySiguiente()) {
            apilar(it.obtener());
            it.siguiente();
        }
    }

    /**
     * Devuelve el elemento en el tope de la pila y la achica en 1.
     * @return el elemento removido en el tope de la pila.
     * Pre: La pila no debe estar vacía.
     */
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        T desapilado = tope.dato();
        tope = tope.proximo();
        return desapilado;
    }


    /**
     * Devuelve el elemento en el tope de la pila.
     * @return el tope de la pila.
     * Pre: La pila no debe estar vacía.
     */
    public T obtener() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return tope.dato();
    }

    /**
     * Indica si la pila está vacía.
     * @return verdadero si la pila no tiene elementos.
     */
    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Devuelve la cantidad de elementos de la pila.
     * @return la cantidad de elementos de la pila.
     */
    public int cantidadElementos() {
        return tamanio;
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
}
