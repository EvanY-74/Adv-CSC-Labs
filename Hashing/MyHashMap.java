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
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 11;

    /**
     * Constructor
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

    /**
     * Puts in a key value pair into the table
     * 
     * @param key element to be hashed
     * @param value value corresponding to key
     * @return returns old value if replacing, otherwise returns null
     */
    public V put(K key, V value) {
        if (key == null) return null;

        // TODO Step 1: compute the index using hash(key)
        int index = this.hash(key);

        // TODO Step 2: if table[index] is null, create a new LinkedList there
        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
            table[index].addFirst(new Entry<K, V>(key, value));
            this.size++;
            return null;
        }

        // TODO Step 3: walk the list at table[index]
        //   -- compare keys using .equals(), not ==
        //   -- if an entry with the same key already exists, update its value
        //      and return the OLD value (do not increment size)
        LinkedList<Entry<K, V>> bucket = table[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }

        // TODO Step 4: no existing entry found -- add a new Entry to the
        //   FRONT of the list (O(1) -- no traversal needed), increment size, return null
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

        // TODO Step 1: compute the index using hash(key)
        int index = this.hash(key);

        // TODO Step 2: if table[index] is null, return null (key not present)
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;

        // TODO Step 3: walk the list at table[index]
        //   -- if an entry with a matching key is found, return its value
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        // TODO Step 4: key was not in the list -- return null
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

        // TODO: return true if the key exists in the map, false otherwise
        // Hint: get(key) returns null when the key is not present --
        //   but what if a key IS present and its value is null?
        //   Walk the list directly to be safe.

        int index = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket == null) return false;

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }

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

        // TODO Step 1: compute the index using hash(key)
        int index = this.hash(key);

        // TODO Step 2: if table[index] is null, return null (nothing to remove)
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;

        // TODO Step 3: walk the list at table[index]
        //   -- compare keys using .equals(), not ==
        //   -- if an entry with a matching key is found:
        //      remove it from the list, decrement size, return its value
        //   -- You cannot remove from a list inside a for-each loop.
        //      Use an iterator: Iterator<Entry<K,V>> it = table[index].iterator()
        //      then it.remove() when you find the match.
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

        // TODO Step 4: key was not found -- return null

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
