package tdas.lista.teorica.simple;

import java.util.*;

public class ListaSimplementeEnlazada<T> implements List<T> {
    private NodoSimplementeEnlazado<T> head;
    private int size;

    public ListaSimplementeEnlazada() {
        this.head = null;
        this.size  = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        NodoSimplementeEnlazado<T> node = new NodoSimplementeEnlazado<>(t);
        if (this.isEmpty()) {
            this.head = node;
        } else {
            NodoSimplementeEnlazado<T> current = this.head;
            while (current.next() != null) {
                current = current.next();
            }
            current.setNext(node);
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        NodoSimplementeEnlazado<T> current  = this.head;
        NodoSimplementeEnlazado<T> previous = null;
        while (current != null) {
            if (Objects.equals(current.data(), o)) {
                if (previous == null) {
                    this.head = current.next();
                } else {
                    previous.setNext(current.next());
                }
                current.setNext(null);
                this.size--;
                return true;
            }
            previous = current;
            current = current.next();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size  = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice: " + index + ", Tamaño: " + size);
        }

        NodoSimplementeEnlazado<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current.data();
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice: " + index + ", Tamaño: " + size);
        }

        NodoSimplementeEnlazado<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }

        T oldValue = current.data();
        current.setData(element);
        return oldValue;
    }

    @Override
    public void add(int index, T t) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Índice: " + index + ", Tamaño: " + size);
        }

        NodoSimplementeEnlazado<T> node = new NodoSimplementeEnlazado<>(t);
        if (index == 0) {
            node.setNext(this.head);
            this.head = node;
        } else {
            NodoSimplementeEnlazado<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next();
            }
            node.setNext(current.next());
            current.setNext(node);
        }
        this.size++;
    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        return List.of();
    }
}
