package ecnu.dase.cache;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/7/30 15:29
 */
public class Main {
    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(8);
        cache.put("a", 5);
        cache.put("b", 1);
        cache.put("c", 7);
        cache.put("d", 10);
        cache.put("a", 5);
        cache.put("f", 1);
        cache.put("r", 7);
        cache.put("b", 10);
        cache.put("q", 5);
        cache.put("t", 1);
        cache.put("r", 7);
        cache.put("y", 10);
    }
}
