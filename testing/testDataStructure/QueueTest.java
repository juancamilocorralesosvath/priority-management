package testDataStructure;

import datastructures.Queue;
import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueTest extends  TestCase{
    private Queue<Integer> queue;

    public void setUp1Queue() {
        queue = new Queue<>();
    }

    public void setUp2Queue() {
        queue = new Queue<>();
        queue.enqueue(1);
    }
    public void setUp3Queue() {
        queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
    }
    // : Verificar el comportamiento de Insertar un elemento en una cola vacía
    @Test
    public void testEnqueueSimple() {
        setUp1Queue();
        queue.enqueue(1);

        assertEquals(1, queue.dequeue().intValue());
    }

    // : Verificar el comportamiento de Insertar multiples elementos en una cola vacía
    @Test
    public void testEnqueueLimit() {
        setUp1Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);

        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
        assertEquals(4, queue.dequeue().intValue());
        assertEquals(5, queue.dequeue().intValue());
        assertEquals(6, queue.dequeue().intValue());
        assertEquals(7, queue.dequeue().intValue());
    }

    @Test
    public void testEnqueueInteresting() {
        setUp1Queue();
        queue.enqueue(null);

        assertEquals(null, queue.dequeue());
    }

    @Test
    public void testDequeueSimple() {
        setUp2Queue();

        assertEquals(1, queue.dequeue().intValue());
    }
    @Test
    public void testDequeueLimit() {
        setUp3Queue();

        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
        assertEquals(4, queue.dequeue().intValue());
        assertEquals(5, queue.dequeue().intValue());
    }

    @Test
    public void testDequeueInteresting() {
        setUp1Queue();

        assertEquals(null, queue.dequeue());
    }



    @Test
    public void testPeekSimple() {
        setUp2Queue();

        assertEquals(1, queue.peek().intValue());
    }
    @Test
    public void testPeekLimit() {
        setUp1Queue();

        assertEquals(null, queue.peek());
    }
    @Test
    public void testPeekInteresting() {
        setUp3Queue();

        assertEquals(1, queue.peek().intValue());
        assertEquals(1, queue.peek().intValue());
        assertEquals(1, queue.peek().intValue());
        assertEquals(1, queue.peek().intValue());
        assertEquals(1, queue.peek().intValue());
    }
}
