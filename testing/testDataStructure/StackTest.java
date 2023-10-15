package testDataStructure;


import datastructures.Stack;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class StackTest extends TestCase{
    private Stack<Integer> stack;

    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPushAndPop() {
        setUp();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
    }

    @Test
    public void testPeek() {
        setUp();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.peek().intValue());
        assertEquals(2, stack.peek().intValue());
    }

    @Test
    public void testSize() {
        setUp();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.size());

        stack.pop();

        assertEquals(1, stack.size());
    }

    @Test
    public void testIsEmpty() {
        setUp();
        assertTrue(stack.isEmpty());

        stack.push(1);

        assertFalse(stack.isEmpty());
    }


}
