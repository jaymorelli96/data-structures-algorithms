package io.github.jaymorelli.stack;

import io.github.jaymorelli.linkedList.DoublyLinkedList;
import io.github.jaymorelli.linkedList.LinkedList;

import java.util.EmptyStackException;

/**
 * Implementation of a Stack data structure using a Linked List (same one implemented in this project).
 * A stack is a LIFO (Last In First Out) data structure that contains 3 main methods:
 * push, which adds and element to the stack, pop which removes the top element, and peek, which returns the last element without removing it.
 * @param <E>
 * @author Jean Morelli
 */
public class StackLL<E> {

    DoublyLinkedList<E> listOfElements = new DoublyLinkedList<>();


    private int size = 0;

    /**
     * Check if the list contains no elements.
     * @return boolean - true if list is considered empty
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns the number of elements in this list
     * @return int - number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Add element to the top of the stack
     * @param e - element to be added
     */
    public void push(E e) {
        listOfElements.add(e);
        size++;
    }

    /**
     * Removes and retrieves the top element of the stack
     * @return last element
     */
    public E pop() {
        if(isEmpty()) throw new EmptyStackException();

        E result = listOfElements.getLast();
        listOfElements.removeLast();
        size--;
        return result;
    }

    /**
     * Retrieves the top element of the stack
     * @return last element
     */
    public E peek() {
        if(isEmpty()) throw new EmptyStackException();

        return listOfElements.getLast();
    }
}
