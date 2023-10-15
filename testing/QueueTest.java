import datastructures.Queue;
import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

public class QueueTest extends  TestCase{
    private Queue<Integer> queue;

    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testEnqueueAndDequeue() {
        setUp();
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
    }

    @Test
    public void testPeek() {
        setUp();
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.peek().intValue());
        assertEquals(1, queue.peek().intValue());
    }

    @Test
    public void testSize() {
        setUp();
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size());

        queue.dequeue();

        assertEquals(1, queue.size());
    }

    @Test
    public void testIsEmpty() {
        setUp();
        assertTrue(queue.isEmpty());

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }
}
