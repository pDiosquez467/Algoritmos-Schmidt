package tdas;

public class NodoSimplementeEnlazado<T> extends Nodo<T> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private NodoSimplementeEnlazado<T> siguiente;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa un nodo simplemente enlazado con el valor dado y la referencia el nodo siguiente.
     * @param valor: el valor almacenado por el nodo.
     * @param siguiente: el nodo siguiente.
     */
    public NodoSimplementeEnlazado(T valor, NodoSimplementeEnlazado<T> siguiente) {
        super(valor);
        this.siguiente = siguiente;
    }

    /**
     * post: Inicializa un nodo simplemente enlazado con el valor dado y sin referencia a un siguiente.
     * @param valor: el valor almacenado por el nodo.
     */
    public NodoSimplementeEnlazado(T valor) {
        super(valor);
        this.siguiente = null;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Devuelve el siguiente del nodo.
     * @return el siguiente del nodo.
     */
    public NodoSimplementeEnlazado<T> getSiguiente() {
        return siguiente;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * post: Permite modificar el siguiente nodo.
     * @param siguiente: el nuevo siguiente.
     */
    public void setSiguiente(NodoSimplementeEnlazado<T> siguiente) {
        this.siguiente = siguiente;
    }
}
