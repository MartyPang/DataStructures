package ecnu.dase.queue;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/4/28 16:18
 */
public class LinkedListQueue<Item> implements MyQueue<Item> {
    private class Node {
        Item val;
        Node next;
    }

    private Node first = null;
    private Node last = null;
    private int n = 0;

    @Override
    public void enqueue(Item item) {
        Node node  = new Node();
        node.val = item;
        node.next = null;
        if(isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        ++n;
    }

    @Override
    public Item dequeue() throws Exception {
        if(isEmpty()) {
            throw new Exception("It's an empty queue");
        }
        Node node = first;
        first = first.next;
        --n;
        if(isEmpty()) {
            last = null;
        }
        return node.val;
    }

    @Override
    public Item front() {
        if(isEmpty()) return null;
        return first.val;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }
}
