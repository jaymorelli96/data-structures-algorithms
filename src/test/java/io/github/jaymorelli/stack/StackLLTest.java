package io.github.jaymorelli.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackLLTest {


    @Test
    void whenNewStackIsCreated_thenSizeIs0() {
        StackLL<String> stack = new StackLL<>();

        assertEquals(0, stack.size());
    }

    @Test
    void givenStackWithSize0_whenIsEmptyIsCalled_thenStackIsEmpty() {
        StackLL<String> stack = new StackLL<>();

        assertTrue(stack.isEmpty());
    }


    @Test
    void givenAnEmptyStack_whenNewElementIsPushedIntoTheStack_thenSizeIs1() {
        StackLL<String> stack = new StackLL<>();
        stack.push("1");

        assertEquals(1, stack.size());
    }

    @Test
    void givenAStack_whenElementIsPushedAndThenPopped_thenElementIsReturned() {
        StackLL<String> stack = new StackLL<>();
        stack.push("1");

        String result = stack.pop();

        assertEquals("1", result);
    }

    @Test
    void givenAStack_whenMultipleElementsArePushedAndPopIsCalled_thenLastElementIsReturned() {
        StackLL<String> stack = new StackLL<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        String result = stack.pop();

        assertEquals("3", result);
    }

    @Test
    void givenANonEmptyStack_whenPopIsCalledMultipleTimes_thenEachTimeLastElementIsRemovedFromListAndReturned() {
        StackLL<String> stack = new StackLL<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals(3, stack.size());

        assertEquals("3", stack.pop());
        assertEquals(2, stack.size());

        assertEquals("2", stack.pop());
        assertEquals(1, stack.size());

        assertEquals("1", stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void givenANonEmptyStack_whenPeekIsCalled_thenLastElementIsReturnedButNotRemoved() {
        StackLL<String> stack = new StackLL<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals(3, stack.size());

        assertEquals("3", stack.peek());
        assertEquals(3, stack.size());
        assertEquals("3", stack.peek());
    }


}
