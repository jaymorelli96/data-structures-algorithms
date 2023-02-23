package io.github.jaymorelli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class LinkedListTest {

    @Test
    void newLinkedList_isEmpty() {
        LinkedList<String> linkedList = new LinkedList<>();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void add_linkedListAfterAddingElement_isNotEmpty() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    void size_afterAddingXelements_sizeIsX() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        assertEquals(3, linkedList.size());
        linkedList.add("Element 4");
        assertEquals(4, linkedList.size());
    }

    @Test
    void clear_afterClearing_listIsEmpty() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void getFirst_returnsFirstElementOfList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        String firstElement = linkedList.getFirst();
        assertEquals("Element 1", firstElement);
        linkedList.clear();
        assertNull(linkedList.getFirst());
    }

    @Test
    void getLast_returnsLastElementOfList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        String lastElement = linkedList.getLast();
        assertEquals("Element 2", lastElement);
        linkedList.add("Element 3");
        lastElement = linkedList.getLast();
        assertEquals("Element 3", lastElement);
    }


    @Test
    void get_emptyList_throwsException() {
        LinkedList<String> linkedList = new LinkedList<>();

        Exception ex = assertThrows(RuntimeException.class, () -> {
            String s = linkedList.get(0);
        });

        assertEquals("List is empty", ex.getMessage());
    }

    @Test
    void get_IndexIsOutOfBound_throwsException() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            String s = linkedList.get(1);
        });


        assertEquals("Index 1 is out of bound for size 1", ex.getMessage());
    }

    @Test
    void get_givenIndexOfElement_returnsElement() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        String element1 = linkedList.get(0);
        String element2 = linkedList.get(1);
        String element3 = linkedList.get(2);

        assertEquals("Element 1", element1);
        assertEquals("Element 2", element2);
        assertEquals("Element 3", element3);
    }

    @Test
    void removeLast_removeAndReturnLastElement() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        boolean lastElement = linkedList.removeLast();
        assertTrue(lastElement);
    }

    @Test
    void removeLast_listOfSize1AfterRemove_isEmpty() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        boolean lastElement = linkedList.removeLast();
        assertTrue(lastElement);
        assertTrue(linkedList.isEmpty());
    }


    @Test
    void removeFirst_removeAndReturnLastElement() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        boolean firstElement = linkedList.removeFirst();
        assertTrue(firstElement);
        assertEquals(1, linkedList.size());
        assertEquals("Element 2", linkedList.getFirst());
    }


    //remove i
    @Test
    void remove_givenIndexI_removeAndReturnElementI() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        boolean element2 = linkedList.remove(1);
        assertTrue(element2);
        assertEquals(2, linkedList.size());
        assertEquals("Element 1", linkedList.getFirst());
        assertEquals("Element 3", linkedList.getLast());
        boolean element3 = linkedList.remove(1);
        assertTrue(element3);
        assertEquals(1, linkedList.size());
        assertEquals("Element 1", linkedList.getLast());
    }

    @Test
    void addFirst_givenElement_elementIsNewHead() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        linkedList.addFirst("New Head");
        assertEquals(4, linkedList.size());
        assertEquals("New Head", linkedList.getFirst());
        assertEquals("Element 1", linkedList.get(1));
    }

    @Test
    void add_givenIndexAndElement_thenListShouldBeModifiedWithNewElement() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        linkedList.add(1, "New Element 2");
        assertEquals(4, linkedList.size());
        assertEquals("Element 1", linkedList.get(0));
        assertEquals("New Element 2", linkedList.get(1));
        assertEquals("Element 2", linkedList.get(2));
        assertEquals("Element 3", linkedList.get(3));
        linkedList.add(0, "New Head");
        assertEquals("New Head", linkedList.getFirst());
        linkedList.add(linkedList.size(), "Last Element");
        assertEquals("Last Element", linkedList.getLast());
    }

    @Test
    public void remove_givenElement_removeElementFromList() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");
        assertEquals(3, linkedList.size());
        boolean elementRemoved = linkedList.remove("Element 2");
        assertEquals(2, linkedList.size());
        assertTrue(elementRemoved);
        assertEquals("Element 1", linkedList.get(0));
        assertEquals("Element 3", linkedList.get(1));
    }


}