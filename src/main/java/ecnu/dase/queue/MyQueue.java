package ecnu.dase.queue;

public interface MyQueue<Item> {
    void enqueue(Item item);
    Item dequeue() throws Exception;
    Item front();
    int size();
    boolean isEmpty();
}
