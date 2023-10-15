package testDataStructure;

import datastructures.PriorityQueue;
import junit.framework.TestCase;
import org.junit.Test;

public class PriorityQueueTest extends TestCase {

    private PriorityQueue<Integer> priorityQueue;

    public void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    public void testInsertAndPeek() {
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        assertEquals(Integer.valueOf(8), priorityQueue.peek());
    }

    @Test
    public void testPoll() {
        priorityQueue.insert(5);
        priorityQueue.insert(123);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        assertEquals(Integer.valueOf(123), priorityQueue.poll());
        assertEquals(Integer.valueOf(8), priorityQueue.poll());
    }

    @Test
    public void testSize() {
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        assertEquals(3, priorityQueue.size());

        priorityQueue.poll();

        assertEquals(2, priorityQueue.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.insert(5);

        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void testInsertAndPeek1() {
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);
        priorityQueue.insert(1);
        priorityQueue.insert(10);

        assertEquals(Integer.valueOf(10), priorityQueue.peek());
    }

    @Test
    public void testPoll1() {
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);
        priorityQueue.insert(1);
        priorityQueue.insert(10);

        assertEquals(Integer.valueOf(10), priorityQueue.poll());
        assertEquals(Integer.valueOf(8), priorityQueue.poll());
        assertEquals(Integer.valueOf(5), priorityQueue.poll());
    }

    @Test
    public void testSize1() {
        priorityQueue.insert(5);
        priorityQueue.insert(3);
        priorityQueue.insert(8);

        assertEquals(3, priorityQueue.size());

        priorityQueue.poll();

        assertEquals(2, priorityQueue.size());

        priorityQueue.insert(12);
        priorityQueue.insert(15);

        assertEquals(4, priorityQueue.size());
    }

    @Test
    public void testIsEmpty1() {
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.insert(5);

        assertFalse(priorityQueue.isEmpty());

        priorityQueue.poll();

        assertTrue(priorityQueue.isEmpty());
    }
}
