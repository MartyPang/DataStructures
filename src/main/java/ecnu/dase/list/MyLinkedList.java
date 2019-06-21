package ecnu.dase.list;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/6/21 13:53
 * MyLinkedList includes some basic operations on a single linked list.
 * The "insert" method inserts a new node at the end of the list. If head is null, then assign head with node.
 * The "reverse" method reverses the whole list.
 * The "nreverse" function reverses the top n nodes of the list.
 * The "reverseBetween" function reverses nodes between position m and position n.
 * The "print" function prints all nodes with arrows connected.
 */
public class MyLinkedList {
    ListNode head;
    MyLinkedList() { head = null; }
    MyLinkedList(ListNode h) { head = h; }

    //global variable for n reverse function
    ListNode successor = null;

    public void insert(ListNode node) {
        if(head == null) {
            head = node;
            return;
        }
        ListNode h = head;
        while(h.next != null) {
            h = h.next;
        }
        h.next = node;
        node.next = null;
    }

    public void reverse() {
        head = reverseHelper(head);
    }

    private ListNode reverseHelper(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode last = reverseHelper(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public void nreverse(int n) {
        successor = null;
        head = nreverseHelper(head, n);
    }

    private ListNode nreverseHelper(ListNode head, int n) {
        if(n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = nreverseHelper(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    /**
     * reverse nodes between m and n
     * @param m
     * @param n
     */
    public void reverseBetween(int m, int n) {
        head = reverseBetweenHelper(head, m , n);
    }

    private ListNode reverseBetweenHelper(ListNode head, int m, int n) {
        if(m == 1) {
            return nreverseHelper(head, n);
        }
        head.next = reverseBetweenHelper(head.next, m-1, n-1);
        return head;
    }

    public String print() {
        return printHelper(head);
    }

    private String printHelper(ListNode head) {
        if(head == null) return "null";
        return head.val + "->" + printHelper(head.next);
    }
}
