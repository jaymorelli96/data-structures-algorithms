package io.github.jaymorelli;


import java.util.NoSuchElementException;

public interface SimpleList<E> {
    /**
     * Returns the number of elements in this list
     * @return int - number of elements
     */
    int size();
    /**
     * Check if the list contains no elements.
     * @return boolean - true if list is considered empty
     */
    boolean isEmpty();
    /**
     * Clear the list
     */
    void clear();
    /**
     * Returns the element in the given position.
     * @param i index of element
     * @return element in the position i
     */
    E get(int i);
    /**
     * Returns the first element of the list
     * @return first element
     */
    E getFirst();
    /**
     * Returns the last element of the list
     * @return first element
     */
    E getLast();
    /**
     * Add element to the back of the list.
     * @param e - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    boolean add(E e);
    /**
     * Add element in the given position.
     * @param e - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    boolean add(int i, E e);
    /**
     * Add element to the front of the list.
     * @param e - element to be added to the front of the list
     * @return boolean - return true if element was successfully added to the list
     */
    boolean addFirst(E e);
    /**
     * Remove first element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    boolean removeFirst();
    /**
     * Remove last element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    boolean removeLast();
    /**
     * Remove element in the index i off the list
     * @param i - index of the element
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    boolean remove(int i);
    /**
     * Remove given element off the list.
     * @param e - element to be removed
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    boolean remove(E e);
}
