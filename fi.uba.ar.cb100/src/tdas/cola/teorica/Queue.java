package tdas.cola.teorica;

import tdas.NodoSimplementeEnlazado;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class Queue<T> implements java.util.Queue<T> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private NodoSimplementeEnlazado<T> primero;
    private NodoSimplementeEnlazado<T> ultimo;
    private int size;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Queue() {
        this.primero = null;
        this.ultimo  = null;
        this.size    = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }

    @Override
    public boolean contains(Object o) {
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(actual.getValor(), o)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int i = 0; i < this.size; i++) {
            arr[i] = actual.getValor();
            actual = actual.getSiguiente();
        }
        return arr;
    }

    @Override
    public T[] toArray(Object[] objects) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        T valor = (T) o;
        NodoSimplementeEnlazado<T> nuevo = new NodoSimplementeEnlazado<>(valor);
        if (this.isEmpty()) {
            this.primero = nuevo;
        } else {
            this.ultimo.setSiguiente(nuevo);
        }
        this.ultimo = nuevo;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection collection) {
        Objects.requireNonNull(collection, "collection");
        int sizeAntes = this.size;
        for (var elemento: collection) {
            this.add(elemento);
        }
        return this.size == sizeAntes +  collection.size();
    }

    @Override
    public void clear() {
        this.primero = null;
        this.ultimo  = null;
        this.size    = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection collection) {
        Objects.requireNonNull(collection, "collection");

        for (var elemento: collection) {
            if (!this.contains(elemento)) return false;
        }
        return true;
    }

    @Override
    public boolean offer(Object o) {
        return this.add(o);
    }

    @Override
    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.poll();
    }

    @Override
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }
        T removido = this.primero.getValor();
        this.primero = this.primero.getSiguiente();
        if (this.primero == null) {
            this.ultimo = null;
        }
        this.size--;
        return removido;
    }

    @Override
    public T element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.primero.getValor();
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.primero.getValor();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------

    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    //MÉTODOS Y CLASES PRIVADOS -----------------------------------------------------------------------------------------

    private class IteratorImpl implements Iterator<T> {

        private NodoSimplementeEnlazado<T> actual = primero;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T valor = actual.getValor();
            actual = actual.getSiguiente();
            return valor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }
}