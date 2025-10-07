package tdas.lista.teorica.simple;

import tdas.lista.teorica.doble.NodoDoblementeEnlazado;

public class NodoSimplementeEnlazado<T> {
    private T value;
    private NodoDoblementeEnlazado<T> next;

    public NodoSimplementeEnlazado(T value, NodoDoblementeEnlazado<T> next) {
        this.value = value;
        this.next = next;
    }

    public NodoSimplementeEnlazado(T value) {
        this(value, null);
    }

    public T value() {
        return value;
    }

    public NodoSimplementeEnlazado<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public NodoDoblementeEnlazado<T> next() {
        return next;
    }

    public NodoSimplementeEnlazado<T> setNext(NodoDoblementeEnlazado<T> next) {
        this.next = next;
        return this;
    }
}
