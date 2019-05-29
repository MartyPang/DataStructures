package ecnu.dase.map;

import java.util.*;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/28 15:01
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        // foreach
        for(Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // lambda
        map.forEach((k,v) -> System.out.println(k + ": " + v));

        // Iterator
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // TreeMap
        Map<Integer, String> treeMap = new TreeMap<>(map);
        // lambda
        map.forEach((k,v) -> System.out.println(k + ": " + v));

        // Sort map by value
        Map<Integer, String> sortMap = sortMapByValue(map);
        //lambda
        sortMap.forEach((k,v) -> System.out.println(k + ": " + v));

    }

    private static <K extends Comparable, V extends Comparable> Map<K, V> sortMapByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<K, V> sortedMap = new LinkedHashMap<>();

        for(Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
