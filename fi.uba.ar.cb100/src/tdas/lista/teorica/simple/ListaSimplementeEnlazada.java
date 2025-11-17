package tdas.lista.teorica.simple;

import java.util.*;
import java.util.function.Consumer;

public class ListaSimplementeEnlazada<T> implements List<T> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private NodoSimplementeEnlazado<T> primero;
    private int tamanio;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Inicializa una lista simplemente enlazada sin elementos y de tamaño 0.
     * pre: -
     */
    public ListaSimplementeEnlazada() {
        this.primero = null;
        this.tamanio = 0;
    }

    //MÉTODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //MÉTODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //MÉTODOS DE CLASE ----------------------------------------------------------------------------------------
    //MÉTODOS GENERALES ---------------------------------------------------------------------------------------
    //MÉTODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * post: Devuelve el tamaño de la lista (la cantidad de elementos).
     * pre: -
     * @return el tamaño de la lista.
     */
    @Override
    public int size() {
        return this.tamanio;
    }

    /**
     * post: Indica si la lista está vacía.
     * pre: -
     * @return verdadero si la lista está vacía.
     */
    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }

    /**
     * post: Indica si el elemento dado pertenece a la lista.
     * pre: -
     * @param o: el elemento a buscar en la lista.
     * @return verdadero si el elemento dado pertenece a la lista.
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        NodoSimplementeEnlazado<T> actual = this.primero;
        while (actual != null) {
            if (Objects.equals(actual.getValor(), o)) return true;
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    /**
     * post: Devuelve un arreglo de 'Object' con los elementos de la lista.
     * pre: -
     * @return un arreglo con los elementos de la lista.
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.tamanio];
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int i = 0; i < this.tamanio; i++) {
            arr[i] = actual.getValor();
            actual = actual.getSiguiente();
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        this.add(this.tamanio, t);
        return true;
    }

    /**
     * post: Remueve el elemento dado de la lista.
     * pre: -
     * @param o: el elemento a remover.
     * @return verdadero si la remoción ocurrió exitosamente.
     */
    @Override
    public boolean remove(Object o) {
        NodoSimplementeEnlazado<T> anterior = null;
        NodoSimplementeEnlazado<T> actual   = this.primero;
        for (int i = 0; i < this.tamanio; i++) {
            if (Objects.equals(actual.getValor(), o)) {
                NodoSimplementeEnlazado<T> siguiente = actual.getSiguiente();
                if (anterior == null) {
                    this.primero = siguiente;
                } else {
                    anterior.setSiguiente(siguiente);
                }
                this.tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * post: Indica si todos los elementos de la colección dada pertenecen a la lista.
     * @param collection: la colección de elementos a evaluar.
     * @return verdadero si todos los elementos de la colección pertenecen a la lista.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) return false;
        for (var elemento: collection) {
            if (!(this.contains(elemento))) return false;
        }
        return true;
    }

    /**
     * post: Agrega todos los elementos de la colección dada.
     * pre: 'collection' debe ser no nula.
     * @param collection: la colección de elementos a agregar.
     * @return verdadero si la colección fue agregada exitosamente.
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null) throw new IllegalArgumentException("collection");
        for (T elemento: collection) {
            this.add(elemento);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        return false;
    }

    /**
     * post: Remueve todos los elementos de la colección dada.
     * pre: 'collection' debe ser no nula.
     * @param collection: la colección de elementos a remover.
     * @return verdadero si la colección fue removida exitosamente.
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) throw new IllegalArgumentException("collection");
        for (var elemento: collection) {
            this.remove(elemento);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    /**
     * post: Limpia la lista, dejándola vacía.
     */
    @Override
    public void clear() {
        this.primero = null;
        this.tamanio = 0;
    }

    /**
     * post: Devuelve el elemento en la posición indicada de la lista. Si la posición es negativa, funciona de
     * manera circular.
     * pre: La posición debe ser menor que el tamanio de la lista.
     * @param i: posición indicada.
     * @return el valor almacenado en la posición 'i'.
     */
    @Override
    public T get(int i) {
        if (i >= this.tamanio) throw new IndexOutOfBoundsException("Fuera de rango");
        if (i < 0) i = this.tamanio + i;
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int j = 0; j < i; j++) {
            actual = actual.getSiguiente();
        }
        return actual.getValor();
    }

    /**
     * post: Modifica el valor del elemento en la posición indicada de la lista.
     * @param i: la posición indicada.
     * @param t: el nuevo valor.
     * @return el nuevo valor del elemento modificado.
     */
    @Override
    public T set(int i, T t) {
        if (i >= this.tamanio) throw new IndexOutOfBoundsException("Fuera de rango");
        if (i < 0) i = this.tamanio + i;
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int j = 0; j < i; j++) {
            actual = actual.getSiguiente();
        }
        actual.setValor(t);
        return t;
    }

    @Override
    public void add(int i, T t) {
        if (i > this.tamanio) throw new IndexOutOfBoundsException("Fuera de rango");
        if (i < 0) i = this.tamanio + i;
        NodoSimplementeEnlazado<T> anterior = null;
        NodoSimplementeEnlazado<T> actual   = this.primero;
        for (int j = 0; j < i; j++) {
            anterior = actual;
            actual   = actual.getSiguiente();
        }
        NodoSimplementeEnlazado<T> nuevo = new NodoSimplementeEnlazado<>(t, actual);
        if (anterior == null) {
            this.primero = nuevo;
        } else {
            anterior.setSiguiente(nuevo);
        }
        this.tamanio++;
    }

    /**
     * post: Remueve el elemento en la posición indicada de la lista. Si la posición es negativa, usa criterio
     * circular.
     * pre: La posición debe ser menor al tamaño de la lista.
     * @param i: la posición del elemento a remover.
     * @return el elemento removido.
     */
    @Override
    public T remove(int i) {
        if (i >= this.tamanio) throw new IndexOutOfBoundsException("Fuera de rango");
        if (i < 0) i = this.tamanio + i;
        NodoSimplementeEnlazado<T> anterior = null;
        NodoSimplementeEnlazado<T> actual   = this.primero;
        for (int j = 0; j < i; j++) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        T removido = actual.getValor();
        NodoSimplementeEnlazado<T> siguiente = actual.getSiguiente();
        if (anterior == null) {
            this.primero = siguiente;
        } else {
            anterior.setSiguiente(siguiente);
        }
        this.tamanio--;
        return removido;
    }

    /**
     * post: Devuelve el índice del elemento dado. Si no lo encuentra, devuelve -1.
     * @param o: el elemento a buscar.
     * @return el índice del elemento dado.
     */
    @Override
    public int indexOf(Object o) {
        NodoSimplementeEnlazado<T> actual = this.primero;
        for (int i = 0; i < this.tamanio; i++) {
            if (Objects.equals(actual.getValor(), o)) return i;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    /**
     * post: Devuelve el último índice en la lista del elemento dado. Si no lo encuentra, devuelve -1.
     * @param o: el elemento a buscar.
     * @return el último índice en la lista.
     */
    @Override
    public int lastIndexOf(Object o) {
        NodoSimplementeEnlazado<T> actual = this.primero;
        int ultimoIndice = -1;
        for (int i = 0; i < this.tamanio; i++) {
            if (Objects.equals(actual.getValor(), o)) ultimoIndice = i;
            actual = actual.getSiguiente();
        }
        return ultimoIndice;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("List iterator");
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException("List iterator");
    }

    @Override
    public List<T> subList(int i, int j) {
        return List.of();
    }

    //MÉTODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    private class IteratorImpl implements Iterator<T> {

        NodoSimplementeEnlazado<T> preAnterior = null;
        NodoSimplementeEnlazado<T> anterior    = null;
        NodoSimplementeEnlazado<T> actual      = primero;

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T valor = actual.getValor();
            preAnterior = anterior;
            anterior    = actual;
            actual      = actual.getSiguiente();
            return valor;
        }

        @Override
        public void remove() {
            if (anterior == null) throw new IllegalStateException();

            if (preAnterior == null) {
                primero = actual;
            } else {
                preAnterior.setSiguiente(actual);
            }
            anterior = null;
            tamanio--;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

}
