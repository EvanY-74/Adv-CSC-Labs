/**
 * Evan Yango
 * Course: Advanced Java I
 * Date: 04/09/2026
 *
 * Custom implementation of HashMap with separate chaining to handle hash collisions
 */

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Custom implementation of HashMap
 * Uses built-in .hashCode() method for hashing
 */
public class MyHashMap<K, V> {

    /**
     * Built in Inner entry class
     */
    static class Entry<K, V> {
        K key;
        V value;

        /**
         * Key value pair that is stored
         * 
         * @param key (generic) the hashed value being checked against
         * @param value (generic) the corresponding value to a key
         */
        Entry(K key, V value) {
            this.key   = key;
            this.value = value;
        }
    }

    // ── Fields ────────────────────────────────────────────────────────────
    /** table where all LinkedLists are stored */
    private LinkedList<Entry<K, V>>[] table;
    /** total number of entries in table */
    private int size;
    /** table array length */
    private static final int DEFAULT_CAPACITY = 11;

    /**
     * Constructor that defines table and size
     */
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size  = 0;
    }

    /**
     * Hash helper
     * 
     * @param key element to be hashed and indexed
     * @return hashed value as an int
     */
    private int hash(K key) {
        // Keys must not be null
        return Math.abs(key.hashCode()) % table.length;
    }

    // .equals() should be used instead of == because == compares location in memory, while .equals() actually compares the values based on specificied behavior. (Example: String .equals() checks each character in the string)
    // You need to use iterator because removing an element normally will through a ConcurrentModificationError so you must step through the LinkedList with an iterator and use the it.remove() method.
    /**
     * Puts in a key value pair into the table
     * 
     * @param key element to be hashed
     * @param value value corresponding to key
     * @return returns old value if replacing, otherwise returns null
     */
    public V put(K key, V value) {
        if (key == null) return null;

        int index = this.hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
            table[index].addFirst(new Entry<K, V>(key, value));
            this.size++;
            return null;
        }

        // iterate through bucket
        LinkedList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        // fallback case
        bucket.addFirst(new Entry<K, V>(key, value));
        this.size++;
        return null;
    }

    /**
     * Gets the value of a particular key
     * 
     * @param key specified key to search
     * @return returns value (or null if it does not exist)
     */
    public V get(K key) {
        if (key == null) return null;

        int index = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;

        // iterate through bucket
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        // fallback case
        return null;
    }

    /**
     * Checks if a key exists
     * 
     * @param key specified key to search
     * @return does the key exist?
     */
    public boolean containsKey(K key) {
        if (key == null) return false;

        int index = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket == null) return false;

        // iterate through bucket
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }

        // fallback case
        return false;
    }

    /**
     * Remove entry from HashMap based on key
     * 
     * @param key of entry to be removed
     * @return returns deleted value if successfully deleted, otherwise returns null
     */
    public V remove(K key) {
        if (key == null) return null;

        int index = this.hash(key);

        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;

        // iterate through bucket with iterator
        Iterator<Entry<K, V>> it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                it.remove();
                this.size--;
                return oldValue;
            }
        }

        // fallback case
        return null;
    }

    /**
     * get size of HashMap
     * 
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * check if HashMap is empty
     * 
     * @return size == 0?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    // ── Test driver ───────────────────────────────────────────────────────
    /**
     * Test driver
     */
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        // put -- basic insertions
        map.put("cat", 1);
        map.put("dog", 2);
        map.put("rat", 3);
        map.put("bat", 4);
        map.put("ant", 5);
        System.out.println("Size after 5 insertions: " + map.size());   // 5

        // get -- existing keys
        System.out.println("get(cat): " + map.get("cat"));              // 1
        System.out.println("get(bat): " + map.get("bat"));              // 4

        // get -- missing key
        System.out.println("get(owl): " + map.get("owl"));              // null

        // put -- duplicate key (update)
        map.put("cat", 99);
        System.out.println("get(cat) after update: " + map.get("cat")); // 99
        System.out.println("Size after update: " + map.size());         // 5

        // containsKey
        System.out.println("containsKey(dog): " + map.containsKey("dog")); // true
        System.out.println("containsKey(elk): " + map.containsKey("elk")); // false

        // remove -- existing key
        map.remove("dog");
        System.out.println("get(dog) after remove: " + map.get("dog")); // null
        System.out.println("Size after remove: " + map.size());         // 4

        // remove -- missing key
        map.remove("owl");
        System.out.println("Size after removing missing key: " + map.size()); // 4

        // null value -- a key can legitimately map to null
        map.put("fox", null);
        System.out.println("get(fox): " + map.get("fox"));              // null
        System.out.println("containsKey(fox): " + map.containsKey("fox")); // true
        System.out.println("Size with null value: " + map.size());      // 5

        // forced collision -- "AaAaAa" and "BBBaaa" have the same hashCode in Java
        MyHashMap<String, Integer> collisionMap = new MyHashMap<>();
        collisionMap.put("AaAaAa", 1);
        collisionMap.put("BBBaaa", 2);
        System.out.println("Collision get(AaAaAa): " + collisionMap.get("AaAaAa")); // 1
        System.out.println("Collision get(BBBaaa): " + collisionMap.get("BBBaaa")); // 2
        collisionMap.remove("AaAaAa");
        System.out.println("After remove, get(BBBaaa): " + collisionMap.get("BBBaaa")); // 2
        System.out.println("After remove, size: " + collisionMap.size()); // 1
    }
}
