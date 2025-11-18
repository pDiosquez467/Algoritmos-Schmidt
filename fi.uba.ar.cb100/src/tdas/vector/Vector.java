package tdas.vector;

import java.util.Collection;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface Vector<E> {
    boolean add(E e);
    void add(int index, E element);
    boolean addAll(Collection<? extends E> c);
    boolean addAll(int index, Collection<? extends E> c);
    void addElement(E obj);

    /**
     * Returns the current capacity of this vector.
     * @return the current capacity (the length of its internal data array,
     * kept in the field 'elementData' of this vector).
     */
    int capacity();
    void clear();
    Object clone();

    /**
     * Returns true if the vector contains the specified element. More formally,
     * returns true if and only if this vector contains al least one element e
     * such that (o == null ? e == null : o.equals(e))
     * @param o: element whose presence in this vector is to be tested.
     * @return true if this vector contains the specified element.
     */
    boolean contains(Object o);
    boolean containsAll(Collection<E> c);
    void copyInto(Object[] arr);
    E elementAt(int index);
    void ensureCapacity(int minCapacity);
    boolean equals(Object o);
    E firstElement();
    void forEach(Consumer<? super E> action);
    E get(int index);
    int hashCode();

    /**
     * Returns the index of the first occurrence of the specified element
     * in this vector, or -1 of this vector does not contain the element.
     * More formally, returns the lowest index i such that
     * (o == null ? get(i) == null : o.equals(get(i))), or -1 if there is
     * no such index.
     * @param o: element to search for.
     * @return the index of the first occurrence of the specified element
     * in this vector, or -1 if this vector does not contain the element.
     */
    int indexOf(Object o);

    /**
     * Returns the index of the first occurrence of the specified element in
     * this vector, searching forwards from index, or returns -1 if the element
     * is not found. More formally, returns the lowest index i such that
     * (i >= index && (o == null ? get(i) == null : o.equals(get(i)))), or -1
     * fi there is no such index.
     * @param o: element to search for.
     * @param index: index to start searching from.
     * @return the index of the first occurrence of the element in this vector
     * at position index or later in the vector; -1 if the element is not found.
     */
    int indexOf(Object o, int index);
    void insertElementAt(E e, int index);

    /**
     * Tests if this vector has no components.
     * @return true if and only if this vector has no components, that is,
     * its size is zero; false, otherwise.
     */
    boolean isEmpty();

    Iterator<E> iterator();
    E lastElement();

    /**
     * Returns the index of the last occurrence of the specified element in
     * this vector, or -1 if this vector does not contain the element. More formally,
     * returns the highest index i such that (o == null ? get(i) == null :
     * o.equals(get(i))), or -1 if there is no such index.
     * @param o: element to search for.
     * @return the index of the last occurrence of the specified element in this
     * vector, or -1 if this vector does not contain the element.
     */
    int lastIndexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this
     * vector, searching backwards from the index, or returns -1 if the element is not
     * found. More formally, returns this highest index i such that
     * (i <= index && (o == null ? get(i) == null : o.equals(get(i)))), or -1
     * if there is no such index.
     * @param o: element to search for.
     * @param index: index to start searching backwards from.
     * @return the index of the last occurrence of the element at position less than
     * or equal to index in this vector; -1 if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is greater than or
     * equal to the current size of this vector.
     */
    int lastIndexOf(Object o, int index);
    E remove(int index);
    boolean remove(E e);
    boolean removeAll(Collection<E> c);
    void removeAllElements();
    boolean removeElement(Object e);
    void removeElementAt(int index);
    boolean removeIf(Predicate<? super E> filter);
    void replaceAll(UnaryOperator<E> operator);
    boolean retainAll(Collection<E> c);
    E set(int index, E element);
    void setElementAt(E e, int index);

    /**
     * Returns the number of components in this vector.
     * @return the number of components in this vector.
     */
    int size();
    void sort(Comparator<? super E> c);
    List<E> subList(int fromIndex, int toIndex);
    Object[] toArray();
    String toString();
}
