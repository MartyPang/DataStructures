package ecnu.dase.list;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/6/21 13:57
 */
public class LinkedListMain {
    static class Node {
        int val;
        Node next;
        Node(int x) { val = x; }
    }
    public static void main(String[] args) {
        Node head = null;
        Node prev = null;
        for(int i = 0; i < 10; ++i) {
            if(i == 0) {
                head = new Node(i);
                prev = head;
            } else {
                Node n = new Node(i);
                prev.next = n;
                prev = n;
            }
        }

        Node n = reverse(head);
        while(n != null) {
            System.out.println(n.val);
            n = n.next;
        }

//        MyLinkedList list = new MyLinkedList();
//
//        // construct a linked list
//        for(int i = 0; i < 10; ++i) {
//            ListNode node = new ListNode(i);
//            list.insert(node);
//        }
//
//        System.out.println("Before reverse: " + list.print());
//        list.reverse();
//        System.out.println("After reverse: " + list.print());
//
//        System.out.println("Before reverse 3: " + list.print());
//        list.nreverse(3);
//        System.out.println("After reverse 3: " + list.print());
//
//        System.out.println("Before reverse between 4 and 7: " + list.print());
//        list.reverseBetween(4, 7);
//        System.out.println("After reverse between 4 and 7: " + list.print());
    }

    public static Node reverse(Node head) {
        if(head == null || head.next == null) return head;
        Node newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
