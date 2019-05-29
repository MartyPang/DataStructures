package ecnu.dase.stack;

import java.util.EmptyStackException;

public interface MyStack<Item> {
    void push(Item item);
    Item pop() throws EmptyStackException;
    Item top();
    boolean isEmpty();
    int size();
}
