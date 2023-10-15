package datastructures.IDatastructures;

public interface IHashTable<K, V> {
    public boolean add(K key, V value);
    public V search(K key);
    public V delete(K key);
}
