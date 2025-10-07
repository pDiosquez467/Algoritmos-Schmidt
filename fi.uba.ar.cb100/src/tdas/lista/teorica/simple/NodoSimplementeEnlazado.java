package tdas.lista.teorica.simple;

public class NodoSimplementeEnlazado<T> {
    private T data;
    private NodoSimplementeEnlazado<T> next;

    public NodoSimplementeEnlazado(T data, NodoSimplementeEnlazado<T> next) {
        this.data = data;
        this.next = next;
    }

    public NodoSimplementeEnlazado(T data) {
        this(data, null);
    }

    public T value() {
        return data;
    }

    public NodoSimplementeEnlazado<T> setData(T data) {
        this.data = data;
        return this;
    }

    public NodoSimplementeEnlazado<T> next() {
        return next;
    }

    public NodoSimplementeEnlazado<T> setNext(NodoSimplementeEnlazado<T> next) {
        this.next = next;
        return this;
    }
}
