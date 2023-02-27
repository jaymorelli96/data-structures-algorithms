package io.github.jaymorelli.linkedList;


import io.github.jaymorelli.SimpleList;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Implementation of a doubly linked list which means that each node holds 2 links references.
 * One for the next node and another one for the previous node.
 * This will improve the performance because it allows traversal of the list in either direction.
 * Compared to the LinkedList in this same module, this implementation here also holds reference of the tail.
 * @author Jean Morelli
 */
public class DoublyLinkedList<E> implements SimpleList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    /**
     * Returns the number of elements in this list
     * @return int - number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the list contains no elements.
     * @return boolean - true if list is considered empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clear the list
     * @implNote  set size to 0 and remove head reference
     */
    @Override
    public void clear() {
        Node<E> head = null;
        Node<E> tail = null;
        size = 0;
    }

    /**
     * Returns the element in the given position.
     * @throws IndexOutOfBoundsException if given index is less than 0 or greater than or equals to its size.
     * @param i index of element
     * @return element in the position i
     */
    @Override
    public E get(int i) {
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException();
        return getNode(i).data;
    }

    /**
     * Returns data of the head node
     * @throws NoSuchElementException if list is empty
     * @return first element
     */
    @Override
    public E getFirst() {
        if(size == 0) throw new NoSuchElementException();

        return head.data;
    }

    /**
     * Returns data of the last node
     * @throws NoSuchElementException if list is empty
     * @return last element
     */
    @Override
    public E getLast() {
        if(size == 0) throw new NoSuchElementException();

        return tail.data;
    }

    /**
     * Add element to the back of the list.
     * @param element - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean add(E element) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<>(oldTail, null, element);
        tail = newNode;
        if(Objects.isNull(oldTail)) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        size++;
        return true;
    }

    /**
     * Add element to the nth position (i index) of the list.
     * @throws IndexOutOfBoundsException if index i is not within the size of the list
     * @param e - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean add(int i, E e) {
        if(i < 0 || i > size) throw new IndexOutOfBoundsException();

        if(i == 0) addFirst(e);
        else if(i == size) add(e);
        else {
            Node<E> nodeAfter = getNode(i);
            Node<E> nodeBefore = nodeAfter.previous;
            Node<E> newNode = new Node<>(nodeBefore, nodeAfter, e);
            nodeBefore.next = newNode;
            nodeAfter.previous = newNode;
        }

        size++;
        return true;
    }

    /**
     * Add element as head of the list.
     * @param e - element to be added to the end of the list
     * @return boolean - return true if element was successfully added to the list
     */
    @Override
    public boolean addFirst(E e) {
        head = new Node<>(null, head, e);
        if(Objects.isNull(tail)) {
            tail = head;
        }
        size++;
        return true;
    }

    /**
     * Remove first element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean removeFirst() {
        if(size == 0) throw new IndexOutOfBoundsException();

        head = getNode(1);
        head.previous = null;

        size--;
        return true;
    }

    /**
     * Remove last element of the list
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean removeLast() {
        if(size == 0) throw new IndexOutOfBoundsException();

        tail = getNode(size - 2);
        tail.next = null;

        size--;
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
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException();
        Node<E> nodeI = getNode(i);
        unlink(nodeI);

        size--;
        return true;
    }

    /**
     * Remove element e off the list.
     * @param e - element to be removed
     * @return boolean - return true if element was removed from the list and false otherwise
     */
    @Override
    public boolean remove(E e) {
        boolean result = false;

        //Find Element
        Node<E> currentNode = head;
        while(currentNode.hasNext()) {
            if(currentNode.data.equals(e)) {
                result = true;
                unlink(currentNode);
                break;
            }
            currentNode = currentNode.next;
        }

        size--;
        return result;
    }

    private void unlink(Node<E> node) {
        Node<E> beforeI = node.previous;
        Node<E> afterI = node.next;
        if(beforeI.hasPrevious()) {
            beforeI.next = afterI;
        }
        if(afterI.hasNext()) {
            afterI.previous = beforeI;
        }
    }

    private Node<E> getNode(int i) {
        if(i >= size / 2) {
            return traverseFromTail(i);
        } else {
            return traverseFromHead(i);
        }
    }

    private Node<E> traverseFromHead(int i) {
        Node<E> result = head;
        for (int j = 0; j < i; j++) {
            result = result.next;
        }
        return result;
    }

    private Node<E> traverseFromTail(int i) {
        Node<E> result = tail;
        for (int j = size - 1; j > i; j--) {
            result = result.previous;
        }
        return result;
    }


    /**
     * Class representation of a node.
     * Linked List is built upon nodes, which each node holds its data and a reference for the next node and also for the previous one.
     * @param <E> Element - any object
     */
    private static class Node<E> {
        Node<E> next;
        Node<E> previous;
        E data;

        public Node(Node<E> previous, Node<E> next, E data) {
            this.next = next;
            this.previous = previous;
            this.data = data;
        }

        boolean hasNext() {
            return next != null;
        }
        boolean hasPrevious() {
            return previous != null;
        }
    }
}
