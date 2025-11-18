package tdas.vector;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings(value = "unchecked")
public class VectorImpl<E> implements Vector<E> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    protected E[] elementData;
    protected int capacityIncrement;
    protected int elementCount;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * post: Constructs an empty vector with the specified initial capacity and capacity increment.
     * @param initialCapacity: the initial capacity of the vector.
     * @param capacityIncrement: the amount by which the capacity is increased when the
     * vector overflows.
     */
    public VectorImpl(int initialCapacity, int capacityIncrement) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity is negative");
        }
        this.elementData       = (E[]) new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
        this.elementCount      = 0;
    }

    /**
     * post: Constructs an empty vector with the specified initial capacity and with its capacity increment
     * equal to zero.
     * @param initialCapacity: the initial capacity of the vector.
     */
    public VectorImpl(int initialCapacity) {
        this(initialCapacity, 0);
    }

    /**
     * post: Constructs an empty vector so that its internal data array has size 10 and its standard capacity
     * increment is zero.
     */
    public VectorImpl() {
        this(10, 0);
    }

    /**
     * post: Constructs a vector containing the elements of the specified collection, in the order they are returned
     * by the collection's iterator.
     * @param c: the collection whose elements are to be placed into this vector.
     * @throws NullPointerException if the specified collection is null.
     */
    public VectorImpl(Collection<? extends E> c) {
        this(c.size(), 0);
        Iterator<?> it = c.iterator();
        for (int i = 0; i < c.size(); i++) {
            this.elementData[i] = (E) it.next();
        }
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

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public void addElement(E obj) {

    }

    @Override
    public int capacity() {
        return this.elementData.length;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.elementCount; i++) {
            E e = this.elementData[i];
            if (Objects.equals(e, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<E> c) {
        return false;
    }

    @Override
    public void copyInto(Object[] arr) {

    }

    @Override
    public E elementAt(int index) {
        return null;
    }

    @Override
    public void ensureCapacity(int minCapacity) {

    }

    @Override
    public E firstElement() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return this.indexOf(o, 0);
    }

    @Override
    public int indexOf(Object o, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }
        for (int i = index; i < this.elementCount; i++) {
            E e = this.elementData[i];
            if (Objects.equals(e, o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertElementAt(E e, int index) {

    }

    @Override
    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public E lastElement() {
        return null;
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.lastIndexOf(0, this.elementCount);
    }

    @Override
    public int lastIndexOf(Object o, int index) {
        if (index > this.size()) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        for (int i = this.elementCount; i > -1; i--) {
            E e = this.elementData[i];
            if (Objects.equals(e, o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<E> c) {
        return false;
    }

    @Override
    public void removeAllElements() {

    }

    @Override
    public boolean removeElement(Object e) {
        return false;
    }

    @Override
    public void removeElementAt(int index) {

    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public boolean retainAll(Collection<E> c) {
        return false;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void setElementAt(E e, int index) {

    }

    @Override
    public int size() {
        return this.elementCount;
    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
