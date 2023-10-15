package testDataStructure;

import datastructures.HashTable;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class HashTableTest extends TestCase {
    private HashTable<String, Integer> hashtable;

    public void setUp() {
        hashtable = new HashTable<>(10);
    }

    @Test
    public void testAddAndSearch() {
        setUp();
        hashtable.add("uno", 1);
        hashtable.add("dos", 2);

        assertEquals(Integer.valueOf(1), hashtable.search("uno"));
    }

    @Test
    public void testDelete() {
        setUp();
        hashtable.add("uno", 1);
        hashtable.add("onu", 2);

        assertEquals(Integer.valueOf(2), hashtable.delete("onu"));
    }

    @Test
    public void testAddCollision() {
        setUp();
        hashtable.add("uno", 1);
        hashtable.add("onu", 2); // Colisión

        assertEquals(Integer.valueOf(1), hashtable.search("uno"));
    }


    @Test
    public void testAddAndDelete() {
        setUp();
        hashtable.add("uno", 1);
        hashtable.add("dos", 2);
        hashtable.add("tres", 3);

        assertEquals(Integer.valueOf(2), hashtable.delete("dos"));
    }
}
