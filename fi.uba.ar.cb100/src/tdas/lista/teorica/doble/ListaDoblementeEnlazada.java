package tdas.lista.teorica.doble;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaDoblementeEnlazada<T> {
    private NodoDoblementeEnlazado<T> primero;
    private int size;

    public ListaDoblementeEnlazada() {
        this.primero = null;
        this.size    = 0;
    }

    public boolean add(T dato) {
        addLast(dato);
        return true;
    }

    public void addLast(T dato) {
        NodoDoblementeEnlazado<T> nuevo = new NodoDoblementeEnlazado<>(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoDoblementeEnlazado<T> actual = primero;
            while (actual.haySiguiente()) {
                actual = actual.siguiente();
            }
            nuevo.anterior(actual);
            actual.siguiente(nuevo);
        }
        size++;
    }

    public void add(int indice, T dato) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }

        NodoDoblementeEnlazado<T> nuevo = new NodoDoblementeEnlazado<>(dato);
        if (indice == 0) {
            if (primero != null) {
                primero.anterior(nuevo);
                nuevo.siguiente(primero);
            }
            primero = nuevo;
        } else {
            NodoDoblementeEnlazado<T> actual = primero;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente();
            }
            nuevo.anterior(actual);
            nuevo.siguiente(actual.siguiente());
            if (actual.siguiente() != null) {
                actual.anterior(nuevo);
            }
            actual.siguiente(nuevo);
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
        NodoDoblementeEnlazado<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente();
        }
        return actual.dato();
    }

    public int indexOf(T dato) {
        NodoDoblementeEnlazado<T> actual = primero;
        for (int i = 0; i < size ; i++) {
            if (Objects.equals(dato, actual.dato())) {
                return i;
            }
            actual = actual.siguiente();
        }
        return -1;
    }

    public boolean contains(T dato) {
        NodoDoblementeEnlazado<T> actual = primero;
        while (actual != null) {
            if (Objects.equals(dato, actual.dato())) {
                return true;
            }
            actual = actual.siguiente();
        }
        return false;
    }

    public void clear() {
        primero = null;
        size = 0;
    }

    public T set(int indice, T dato) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Fuera de rango");
        }
        NodoDoblementeEnlazado<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente();
        }
        T removido = actual.dato();
        actual.dato(dato);
        return removido;
    }
}
