package ecnu.dase.stack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/4/28 15:02
 */
public class Main {
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>();
        ArrayStack<Integer> s2 = new ArrayStack<>();

        s1.push(1);
        s2.push(1);

        s1.push(2);
        s2.push(2);

        while (!s1.isEmpty()) {
            System.out.println(s1.pop());
        }
        while (!s2.isEmpty()) {
            System.out.println(s2.pop());
        }

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
    }
}
