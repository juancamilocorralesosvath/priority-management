package testDataStructure;

import datastructures.HashTable;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class HashTableTest extends TestCase {
    private HashTable<String, Integer> hashtable;

    public void setUp1Hash() {
        hashtable = new HashTable<>(10);
    }
    public void setUp2Hash() {
        hashtable = new HashTable<>(10);
        hashtable.add("uno", 1);
    }

    @Test
    public void testAddSimple() {
        setUp1Hash();
        hashtable.add("uno", 1);

        assertEquals(Integer.valueOf(1), hashtable.search("uno"));
    }
    // agregar un elemento con la misma clave
    // se verifica que se este llevando a cabo el encadenamiento
    // de manera apropiada
    @Test
    public void testAddLimit() {
        setUp2Hash();
        hashtable.add("uno", 343);

        assertEquals(Integer.valueOf(1), hashtable.search("uno"));
        hashtable.delete("uno");
        assertEquals(Integer.valueOf(343), hashtable.search("uno"));
    }

    // agregar un elemento con una clave nula
    // no nos interesa guardar claves nulas, por lo que
    // retorna false
    @Test
    public void testAddInteresting() {
        setUp1Hash();
        assertEquals(false, hashtable.add(null, 343));
    }

    // buscar un elemento que se que existe
    @Test
    public void testSearchSimple() {
        setUp2Hash();

        assertEquals(Integer.valueOf(1), hashtable.search("uno"));
    }

    // buscar un elemento que no existe
    @Test
    public void testSearchLimit() {
        setUp2Hash();

        assertEquals(null, hashtable.search("dos"));
    }

    // buscar un elemento en una tabla hash vacia
    @Test
    public void testSearchInteresting() {
        setUp1Hash();

        assertEquals(null, hashtable.search("uno"));
    }

    // eliminar un elemento que existe
    @Test
    public void testDeleteSimple() {
        setUp2Hash();

        assertEquals(Integer.valueOf(1), hashtable.delete("uno"));
    }


    // eliminar un elemento que no existe
    @Test
    public void testDeleteLimit() {
        setUp2Hash();

        assertEquals(null, hashtable.delete("dos"));
    }

    // eliminar un elemento en una tabla hash vacia
    @Test
    public void testDeleteInteresting() {
        setUp1Hash();

        assertEquals(null, hashtable.search("uno"));
    }

}
