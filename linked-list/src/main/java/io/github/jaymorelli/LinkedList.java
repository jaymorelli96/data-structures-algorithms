package io.github.jaymorelli;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Simple implementation of a single Linked List.
 * @author Jean Morelli
 */
public class LinkedList<E> implements SimpleList<E>{
    private Integer size = 0;
    private Node<E> head;

    /**
     * Returns the number of elements in this list
     * @return int - number of elements
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns <strong>true</strong> if list contains no elements.
     * @implNote
     * The head node should be null and size should be 0
     */
    @Override
    public boolean isEmpty() {
        return size.equals(0) && Objects.isNull(head);
    }

    /**
     * Clear the list
     * @implNote  set size to 0 and remove head reference by setting to null
     */
    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Returns the element in the given position. It will throw an exception if list is empty or index is out of bound.
     * @implNote Validate if list is empty or index is out of bound. After validated it will iterate over the list i times
     * @param i index of element
     * @return element in the position i
     */
    @Override
    public E get(int i) {
        if(isEmpty()) throw new RuntimeException("List is empty");
        else if(i >= size) throw new IndexOutOfBoundsException(String.format("Index %d is out of bound for size %d", i, size));
        else {
            return getNode(i).data;
        }
    }

    /**
     * Returns data of the head node or null if list is empty
     * @throws NoSuchElementException if list is empty
     * @return first element
     */
    @Override
    public E getFirst() {
        if(isEmpty()) throw new NoSuchElementException();

        return head.data;
    }

    /**
     * Returns data of the last node or null if list is empty
     * @throws NoSuchElementException if list is empty
     * @return last element
     */
    public E getLast() {
        Node<E> lastNode = getLastNode();
        if(Objects.isNull(lastNode)) throw new NoSuchElementException();

        return lastNode.data;
    }

    /**
     * Add element to the back of the list by  setting the next reference of the previously last node to the new element being added.
     * @implNote If list is empty set head to the new element, else get the last node and set its reference to the new element.
     * @param element - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean add(E element) {
        Node<E> n = new Node<>(null, element);
        if(isEmpty()) {
            head = n;
        } else {
            Node<E> lastNode = getLastNode();
            lastNode.next = n;
        }
        size++;
        return true;
    }

    /**
     * Add element to the nth position (i index) of the list by  setting the next reference of the previous node (i - 1) to the new element being added.
     * @throws IndexOutOfBoundsException if index i is not within the size of the list
     * @implNote If list is empty set head to the new element, otherwise insert element into index i position.
     * @param element - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean add(int i, E element) {
        if(i > size() || i < 0) throw new IndexOutOfBoundsException();

        if(i == 0) {
            addFirst(element);
        } else if(i == size - 1) {
            add(element);
        } else {
            Node<E> iMinus1 = getNode(i - 1);
            iMinus1.next = new Node<>(iMinus1.next, element);
            size++;
        }

        return true;
    }

    /**
     * Add element as head of the list.
     * @param element - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean addFirst(E element) {
        if(isEmpty()) {
            head = new Node<>(null, element);
        } else {
            Node<E> next = new Node<>(head.next, head.data);
            head = new Node<>(next, element);
        }
        size++;
        return true;
    }

    /**
     * Remove element in the index i off the list.
     * @throws IndexOutOfBoundsException if index i is not within the size of the list
     * @param i - index of the element
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean remove(int i) {
        if(isEmpty()) return false;
        if(i >= size || i < 0) throw new IndexOutOfBoundsException();


        if(i == 0) {
            return removeFirst();
        }
        else if(i == size - 1) {
            return removeLast();
        }
        else {
            Node<E> iMinus1 = getNode(i - 1);
            iMinus1.next = iMinus1.next.next;
            size--;
            return true;
        }
    }

    /**
     * Remove element e off the list.
     * @param e - element to be removed
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean remove(E e) {
        if(isEmpty()) throw new RuntimeException("List is empty");

        Node<E> node = head;
        boolean elementFound = false;

        //Find element
        while(node.hasNext()) {
            if(node.next.data.equals(e)) {
                elementFound = true;
                break;
            }
            node = node.next;
        }

        if(elementFound) {
            //Unlink element
            node.next = node.next.next;
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove first element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean removeFirst() {
        if(isEmpty()) return false;
        if(size() == 1) {
            clear();
        } else {
            head = getNode(1);
            size--;
        }
        return true;
    }

    /**
     * Remove last element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean removeLast() {
        if(size() == 0) return false;
        else if(size() == 1) {
            clear();
            return true;
        }
        else {
            Node<E> beforeLast = getNode(size() - 2);
            beforeLast.next = null;
            size--;
            return true;
        }
    }

    private Node<E> getLastNode() {
        return getNode(size() - 1);
    }

    private Node<E> getNode(int n) {
        Node<E> node = head;
        for (int i = 0; i < n; i++) {
            node = node.next;
        }
        return node;
    }

    private Node<E> getNode(E e) {
        Node<E> node = head;
        while(!node.data.equals(e)) {
            node = node.next;
        }
        return node;
    }


    /**
     * Class representation of a node.
     * Linked List is built upon nodes, which each node holds its data and a reference for the next node.
     * @param <E> Element - any class
     */
    private static class Node<E> {
        Node<E> next;
        E data;

        public Node(Node<E> next, E data) {
            this.next = next;
            this.data = data;
        }

        boolean hasNext() {
            return next != null;
        }
    }
}
