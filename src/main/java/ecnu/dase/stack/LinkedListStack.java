package ecnu.dase.stack;

import java.util.EmptyStackException;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/4/28 15:25
 */
public class LinkedListStack<Item> implements MyStack<Item> {
    private class Node {
        Item val;
        Node next;
    }

    private Node top = null;
    private int n = 0;

    @Override
    public void push(Item item) {
        Node node = new Node();
        node.val = item;
        node.next = top;
        top = node;
        ++n;
    }

    @Override
    public Item pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        Item item = top.val;
        top = top.next;
        --n;
        return null;
    }

    @Override
    public Item top() {
        if(isEmpty()) return null;
        return top.val;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }
}
