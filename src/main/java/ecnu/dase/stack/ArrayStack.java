package ecnu.dase.stack;

import java.util.EmptyStackException;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/4/28 14:49
 */
public class ArrayStack<Item> implements MyStack<Item> {
    private Item[] array = (Item[]) new Object[1]; //data array
    private int n = 0; //size

    @Override
    public void push(Item item) {
        checkLength();
        array[n++] = item;
    }

    @Override
    public Item pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        Item item = array[--n];
        checkLength();
        array[n] = null;//防止内存泄漏
        return item;
    }

    @Override
    public Item top() {
        if(isEmpty()) {
            return null;
        }
        return array[n-1];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    /**
     * 判断是否需要重新调整数组大小
     */
    private void checkLength() {
        if(n == array.length) {
            resize(2 * array.length);
        } else if(n > 0 && n < array.length / 4) {
          resize(array.length / 2);
        }
    }

    private void resize(int size) {
        Item[] tmp = (Item[]) new Object[size];
        for(int i = 0; i < n; ++i) {
            tmp[i] = array[i];
        }
        array = tmp;
    }
}
