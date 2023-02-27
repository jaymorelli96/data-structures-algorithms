package io.github.jaymorelli.doublyLinkedList;

import io.github.jaymorelli.linkedList.DoublyLinkedList;
import org.junit.jupiter.api.Test;

import java.nio.file.DirectoryStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void givenNoInput_whenNewListIsCreated_thenListIsEmpty() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        assertEquals(0, linkedList.size());
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void givenAnEmptyList_whenListIsCleared_thenListStillEmpty() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        assertTrue(linkedList.isEmpty());
        linkedList.clear();
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void givenEmptyList_whenNElementIsAdded_thenSizeIsN() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("New Element");
        linkedList.add("New Element");
        linkedList.add("New Element");
        assertEquals(3, linkedList.size());
    }

    @Test
    void givenAnEmptyList_whenAnElementIsAdded_thenListIsNotEmptyAndSizeIs1() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("New Element");
        assertEquals(1, linkedList.size());
        assertFalse(linkedList.isEmpty());
    }

    @Test
    void givenAnListWith1Element_whenGetFirstIsCalled_thenReturnElement() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("New Element");

        String element = linkedList.getFirst();
        assertEquals("New Element", element);
    }

    @Test
    void given2Elements_whenTheyAreAdded_thenTheFirstToBeAddedIsHeadAndTheOtherTail() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Tail Element");

        String element = linkedList.getFirst();
        String element2 = linkedList.getLast();
        assertEquals("Head Element", element);
        assertEquals("Tail Element", element2);
    }

    @Test
    void givenNElements_whenTheyAreAdded_thenTheFirstToBeAddedIsHeadAndLastToBeAddedIsTheTail() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Middle Element");
        linkedList.add("Middle Element");
        linkedList.add("Middle Element");
        linkedList.add("Middle Element");
        linkedList.add("Tail Element");

        String element = linkedList.getFirst();
        String element2 = linkedList.getLast();
        assertEquals("Head Element", element);
        assertEquals("Tail Element", element2);
        assertEquals(6, linkedList.size());
    }

    @Test
    void givenNElementsInTheList_whenGetIsCalledWithAnIndex_thenReturnElementInTheIndexPosition() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Middle 1 Element");
        linkedList.add("Middle 2 Element");
        linkedList.add("Middle 3 Element");
        linkedList.add("Middle 4 Element");
        linkedList.add("Tail Element");

        String middle1 = linkedList.get(1);
        assertEquals("Middle 1 Element", middle1);
        String middle2 = linkedList.get(2);
        assertEquals("Middle 2 Element", middle2);
        String middle3 = linkedList.get(3);
        assertEquals("Middle 3 Element", middle3);
        String middle4 = linkedList.get(4);
        assertEquals("Middle 4 Element", middle4);
    }

    @Test
    void givenANonEmptyList_whenAddFirstIsCalled_thenNewElementIsHead() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Middle Element");
        linkedList.add("Tail Element");

        String firstElement = linkedList.getFirst();
        assertEquals("Head Element", firstElement);
        boolean elementAdded = linkedList.addFirst("New Head Element");
    assertTrue(elementAdded);
    assertEquals(4, linkedList.size());
    assertEquals("New Head Element", linkedList.getFirst());
    assertEquals("Head Element", linkedList.get(1));
    assertEquals("Middle Element", linkedList.get(2));
    assertEquals("Tail Element", linkedList.getLast());
}

    @Test
    void givenAddFirstIsCalled_whenAnotherElementIsAddedToTheList_thenThereIsANewHeadAndNewTail() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Middle Element");
        linkedList.add("Tail Element");

        linkedList.addFirst("New Head Element");
        linkedList.add("New Tail Element");

        assertEquals(5, linkedList.size());
        assertEquals("New Head Element", linkedList.getFirst());
        assertEquals("Head Element", linkedList.get(1));
        assertEquals("Middle Element", linkedList.get(2));
        assertEquals("Tail Element", linkedList.get(3));
        assertEquals("New Tail Element", linkedList.getLast());
    }

    @Test
    void givenAnEmptyList_whenFirstElementIsAddedWithAddFirstAndNewTailIsAdded_thenBothElementsAreAdded() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.addFirst("New Head Element");

        String firstElement = linkedList.getFirst();
        assertEquals(1, linkedList.size());
        assertEquals("New Head Element", linkedList.getFirst());

        linkedList.add("New Tail Element");
        assertEquals(2, linkedList.size());
        assertEquals("New Head Element", linkedList.getFirst());
        assertEquals("New Tail Element", linkedList.getLast());
    }

    @Test
    void givenAList_whenElementIsAddedAtAGivenIndex_thenElementIsInTheNewGivenPosition() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head Element");
        linkedList.add("Middle Element");
        linkedList.add("Tail Element");
        assertEquals("Middle Element", linkedList.get(1));

        linkedList.add(1, "Middle 1 Element");

        assertEquals(4, linkedList.size());
        assertEquals("Middle 1 Element", linkedList.get(1));
        assertEquals("Middle Element", linkedList.get(2));

        linkedList.add(2, "Middle 2 Element");
        assertEquals(5, linkedList.size());
        assertEquals("Middle 1 Element", linkedList.get(1));
        assertEquals("Middle 2 Element", linkedList.get(2));
        assertEquals("Middle Element", linkedList.get(3));
    }

    @Test
    void givenAnEmptyList_whenRemoveFirstIsCalled_thenExceptionIsThrown() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, linkedList::removeFirst);
    }

    @Test
   void givenAListWithSize1_whenRemoveFirstIsCalled_thenElementIsOutOfTheListAndListIsEmpty() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head");

        linkedList.removeFirst();

        assertEquals(0, linkedList.size());
        assertTrue(linkedList.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, linkedList::removeFirst);
    }

    @Test
    void givenAList_whenRemoveFirstIsCalled_thenThereIsANewHeadInTheList() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head");
        linkedList.add("Tail");

        linkedList.removeFirst();

        assertEquals("Tail", linkedList.getFirst());
        assertEquals("Tail", linkedList.getLast());
    }

    @Test
    void givenAnEmptyList_whenRemoveLastIsCalled_thenExceptionIsThrown() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, linkedList::removeLast);
    }

    @Test
    void givenAListWithSize1_whenRemoveLastIsCalled_thenElementIsOutOfTheListAndListIsEmpty() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head And Tail");

        linkedList.removeLast();

        assertEquals(0, linkedList.size());
        assertTrue(linkedList.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, linkedList::removeLast);
    }

    @Test
    void givenAList_whenRemoveLastIsCalled_thenThereIsANewTailInTheList() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head");
        linkedList.add("Middle");
        linkedList.add("Tail");

        linkedList.removeLast();

        assertEquals("Middle", linkedList.getLast());
    }


    @Test
    void givenList_whenRemoveWithIndexIsCalledWithAIndexOutOfBound_thenExceptionIsThrown() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(1));
    }

    @Test
    void givenAList_whenRemoveIsCalledWithAnIndex_thenElementAtIndexPositionIsRemovedFromList() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        assertEquals("1", linkedList.get(1));
        assertEquals("2", linkedList.get(2));

        linkedList.remove(1);
        assertEquals(3, linkedList.size());
        assertEquals("0", linkedList.get(0));
        assertEquals("2", linkedList.get(1));
        assertEquals("3", linkedList.get(2));
    }

    @Test
    void givenList_whenRemoveWithElementAsParamIsCalledAndElementNotExist_thenReturnsFalse() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("Head");
        boolean result = linkedList.remove("Different Head");
        assertFalse(result);
    }

    @Test
    void givenAList_whenRemoveWithElementAsParamIsCalled_thenTheGivenElementRemovedFromList() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("0");
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

        boolean removedNumber1 = linkedList.remove("1");
        boolean removedNumber2 = linkedList.remove("2");

        assertTrue(removedNumber1);
        assertTrue(removedNumber2);
        assertEquals(2, linkedList.size());
        assertEquals("0", linkedList.get(0));
        assertEquals("3", linkedList.get(1));
    }

}