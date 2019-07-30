package ecnu.dase.cache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/7/30 14:30
 * Implement a LRU cache based on HashMap and Double Linked List structures,
 * supporting put and get functions.
 */
public class LRUCache<K, V> implements ICache<K, V> {
    /**
     * A double linked queue storing entries.
     */
    private Deque<Entry<K, V>> deque;
    /**
     * A hashmap maps keys to values accordingly,
     * making query complexity O(1).
     */
    private Map<K, Entry<K, V>> cache;
    /**
     * capacity bound indicating memory limits.
     */
    private int capacity;

    public LRUCache() {
        deque = new LinkedList<>();
        cache = new HashMap<>();
        capacity = 8;
    }

    public LRUCache(int size) {
        deque = new LinkedList<>();
        cache = new HashMap<>();
        capacity = Math.max(1<<3, size);
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = cache.get(key);
        if(entry == null) {
            return null;
        }
        if(!entry.equals(deque.getFirst())) {
            // reset entry as the most recent used entry,
            // add it to the head of the list
            if(entry.equals(deque.getLast())) {
                deque.pollLast();
            } else {
                deque.remove(entry);
            }
            deque.addFirst(entry);
        }
        printCache();
        return entry.value;

    }

    @Override
    public void put(K key, V value) {
        if(cache.containsKey(key)) {
            Entry<K, V> entry = cache.get(key);
            entry.value = value;
            // this entry being the most recent used one,
            // set it to the head of the deque
            if(entry.equals(deque.getLast())) {
                deque.pollLast();
            } else {
                deque.remove(entry);
            }
            deque.addFirst(entry);
            printCache();
            return;
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        // Still got room for new entry
        if(cache.size() >= capacity) {
            //Remove the least recent used entry, a.k.a, the tail node
            Entry<K, V> leastrecentused = deque.pollLast();
            cache.remove(leastrecentused);
        }
        deque.addFirst(newEntry);
        cache.put(key, newEntry);
        printCache();
    }

    public void printCache() {
        for(Entry<K, V> e : deque) {
            System.out.print("("+e.key+","+e.value+")");
        }
        System.out.print("\n");
    }
}
