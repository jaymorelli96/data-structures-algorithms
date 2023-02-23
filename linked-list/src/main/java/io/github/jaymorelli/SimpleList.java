package io.github.jaymorelli;

public interface SimpleList<E> {
    int size();
    boolean isEmpty();
    void clear();
    E get(int i);
    E getFirst();
    E getLast();
    boolean add(E e);
    boolean add(int i, E e);
    boolean addFirst(E e);
    boolean removeFirst();
    boolean removeLast();
    boolean remove(int i);
    boolean remove(E e);
}
