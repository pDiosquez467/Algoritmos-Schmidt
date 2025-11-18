package tdas.vector;

import java.util.Collection;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface Vector<E> {

    /**
     * Appends the specified element to the end of this vector.
     * @param e: element to be appended to this vector.
     * @return true if the collection changed as a result of the call.
     */
    boolean add(E e);


    void add(int index, E element);
    boolean addAll(Collection<? extends E> c);
    boolean addAll(int index, Collection<? extends E> c);

    /**
     * Adds the specified component to be the end of this vector, increasing its
     * size by one. The capacity fo this vector is increased if its size becomes
     * greater than ist capacity.
     * @param obj: the component to be added.
     */
    void addElement(E obj);

    /**
     * Returns the current capacity of this vector.
     * @return the current capacity (the length of its internal data array,
     * kept in the field 'elementData' of this vector).
     */
    int capacity();

    /**
     * Removes all the elements from this vector. The vector will be empty
     * after this call returns (unless it throws an exception).
     */
    void clear();

    /**
     * Returns a clone of this vector. The copy will contain a reference to a
     * clone of the internal data array, not a reference to the original internal
     * data array of this vector.
     * @return a clone of this vector.
     */
    Object clone();

    /**
     * Returns true if the vector contains the specified element. More formally,
     * returns true if and only if this vector contains al least one element e
     * such that (o == null ? e == null : o.equals(e))
     * @param o: element whose presence in this vector is to be tested.
     * @return true if this vector contains the specified element.
     */
    boolean contains(Object o);

    /**
     * Returns true if this vector contains all the elements in the specified
     * collection.
     * @param c: a collection whose elements will be tested for containment in this
     * vector.
     * @return true if this vector contains all the elements in the specified collection.
     * @throws NullPointerException if the specified collection is null.
     */
    boolean containsAll(Collection<E> c);

    /**
     * Copies the components of this vector into the specified array. The item
     * at index k in this vector is copied into component k of 'arr'.
     * @param arr: an array into which the components get copied.
     * @throws NullPointerException if the given array is null.
     * @throws IndexOutOfBoundsException if the specified array is not large
     * enough to hold all the components of this vector.
     * @throws ArrayStoreException if the component of this is not of a runtime
     * type that can be stored in the specified array.
     */
    void copyInto(Object[] arr);

    /**
     * Return the component at the specified index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     * (index < 0 || index > size())
     * @param index: an index into this vector.
     */
    E elementAt(int index);

    /**
     * Return the first component (the item at index 0) of this vector.
     * @return the first component of this vector.
     * @throws java.util.NoSuchElementException if the vector is empty.
     */
    E firstElement();

    /**
     * Returns the last component of the vector.
     * @return the last component of the vector, i.e., the component at index
     * 'size() - 1'.
     * @throws java.util.NoSuchElementException if the vector has no components.
     */
    E lastElement();

    /**
     * Increases the capacity of this vector, if necessary, to be ensure that it
     * can hold at least the number of components specified by the minimum capacity
     * argument.
     * If the current capacity of this vector is less than 'minCapacity', then its
     * capacity is increased by replacing its internal data array, kept in the field
     * 'elementData', with a larger one. The size of the new data array will be the old
     * size plus 'capacityIncrement', unless the value of 'capacityIncrement' is less or
     * equal to zero, in which case the new capacity will be twice the old capacity; but
     * if the new size is still smaller than 'minCapacity' then the new capacity will be
     * 'minCapacity'.
     * @param minCapacity: the desire minimum capacity.
     */
    void ensureCapacity(int minCapacity);

    boolean equals(Object o);

    /**
     * Performs the given action for each element of the Iterable until all
     * elements have been processed or the action throws an exception.
     * @param action: the action to be performed for each element.
     */
    void forEach(Consumer<? super E> action);

    /**
     * Returns the element at the specified position in this vector.
     * @param index: index of the element to return.
     * @return object at the specified index.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     * (index < 0 || index >= size())
     */
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

    /**
     * Replaces the element at the specified position in this vector with the
     * specified element.
     * @param index: index of the element to replace.
     * @param element: element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     * (index < 0 || index >= size())
     */
    E set(int index, E element);

    /**
     * Sets the component at the specified index of this vector to be the specified
     * object. The previous component at the position is discarded.
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     * (index < 0 || index > size())
     * @param obj: what the component is to be set to.
     * @param index: the specified index.
     */
    void setElementAt(E obj, int index);

    /**
     * Returns the number of components in this vector.
     * @return the number of components in this vector.
     */
    int size();
    void sort(Comparator<? super E> c);
    List<E> subList(int fromIndex, int toIndex);

    /**
     * Returns an array containing all the elements in this vector in the correct order.
     * @return an array containing all the elements in this collection.
     */
    Object[] toArray();
    String toString();
}
