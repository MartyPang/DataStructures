package ecnu.dase.cache;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/7/30 14:25
 */
public interface ICache<K, V> {
    public V get(K key);
    public void put(K key, V value);
}
