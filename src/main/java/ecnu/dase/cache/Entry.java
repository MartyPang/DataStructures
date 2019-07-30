package ecnu.dase.cache;

import java.util.Objects;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/7/30 14:40
 */
public class Entry<K, V> {
    K key;
    V value;
    public Entry(K k, V v) {
        key = k;
        value = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key) &&
                Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
