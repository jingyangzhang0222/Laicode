/*
* Given a stream of characters, find the first non-repeating character from stream. You need to tell the first non-repeating character in O(1) time at any moment.

Implement two methods of the class:

read() - read one character from the stream
firstNonRepeating() - return the first non-repeating character from the stream at any time when calling the method, return null if there does not exist such characters
Examples:

read:

a   b   c   a   c   c    b

firstNonRepeating:

a   a   a   b   b   b   null
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacterInStream {
    public static void main(String[] args) {
        //a, b, c, b, a, d, c, d,     a,     e
        //  a, a, a, a, c, c, d, null,  null, e
        FirstNonRepeatingCharacterInStream test = new FirstNonRepeatingCharacterInStream();
        char[] input = {'a', 'b', 'c', 'b', 'a', 'd', 'c', 'd', 'a', 'e'};
        for (char ch : input) {
            test.read(ch);
            System.out.println(test.firstNonRepeating());
        }
    }

    private Node head;
    private Node tail;
    private Map<Character, Node> map;

    public FirstNonRepeatingCharacterInStream() {
        map = new HashMap<>();
    }

    public void read(char ch) {
        if (!map.containsKey(ch)) {
            Node node = new Node(ch);
            addNode(node);
        } else {
            Node node = map.get(ch);
            if (node == null) {
                return;
            } else {
                deleteNode(node);
            }
        }
    }

    private void deleteNode(Node node) {
        map.put(node.val, null);
        if (head == node && tail == node) {
            head = null;
            tail = null;
            return;
        }

        if (node == head) {
            node.next.prev = null;
            head = node.next;
            node.next = null;
            return;
        }

        if (node == tail) {
            node.prev.next = null;
            tail = node.prev;
            node.prev = null;
            return;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void addNode(Node node) {
        map.put(node.val, node);
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public Character firstNonRepeating() {
        return head == null ? null : head.val;
    }

    class Node {
        char val;
        Node prev;
        Node next;

        public Node(char val) {
            this.val = val;
        }
    }
}
