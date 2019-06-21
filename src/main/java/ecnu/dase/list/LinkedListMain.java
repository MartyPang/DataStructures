package ecnu.dase.list;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/6/21 13:57
 */
public class LinkedListMain {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // construct a linked list
        for(int i = 0; i < 10; ++i) {
            ListNode node = new ListNode(i);
            list.insert(node);
        }

        System.out.println("Before reverse: " + list.print());
        list.reverse();
        System.out.println("After reverse: " + list.print());

        System.out.println("Before reverse 3: " + list.print());
        list.nreverse(3);
        System.out.println("After reverse 3: " + list.print());

        System.out.println("Before reverse between 4 and 7: " + list.print());
        list.reverseBetween(4, 7);
        System.out.println("After reverse between 4 and 7: " + list.print());
    }
}
