package tdas.lista.teorica.simple;

public class NodoSimplementeEnlazado<T> {

    private final T dato;
    private NodoSimplementeEnlazado<T> proximo;

    public NodoSimplementeEnlazado(T dato, NodoSimplementeEnlazado<T> proximo) {
        this.dato = dato;
        this.proximo = proximo;
    }

    public NodoSimplementeEnlazado(T dato) {
        this(dato, null);
    }

    public T getDato() {
        return dato;
    }

    public NodoSimplementeEnlazado<T> getProximo() {
        return proximo;
    }

    public void setProximo(NodoSimplementeEnlazado<T> proximo) {
        this.proximo = proximo;
    }

    public boolean tieneProximo() {
        return proximo != null;
    }
}
