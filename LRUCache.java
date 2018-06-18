/*
* Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

    20180527
    146
    hard
    OOD
    ?
    ?

* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> test = new LRUCache<>(2);
        System.out.println(test.get(1));
        test.put(1, 1);
        test.put(2, 2);
        System.out.println(test.get(2));
        System.out.println(test.get(1));
        test.put(3, 3);
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
    }

    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() == capacity) {
                removeLRUCache();
            }
            Node node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        } else {
            Node node = map.get(key);
            updateOrder(node);
            node.value = value;
        }
    }

    public int get(int key) {
        if (map == null || !map.containsKey(key)) {
            return -1;
        } else {
            Node node = map.get(key);
            updateOrder(node);
            return node.value;
        }
    }

    private void addNode(Node node) {
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    private void updateOrder(Node node) {
        if (node == head) {//MRU, do nothing
            return;
        }

        if (node == tail) {//not head, but tail
            tail = node.prev;
            node.prev.next = null;
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
            return;
        }
        //not head, not tail
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = head;
        node.prev = null;
        head.prev = node;
        head = node;
    }

    private void removeLRUCache() {
        map.remove(tail.key);
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        Node newTail = tail.prev;
        tail.prev = null;
        newTail.next = null;
        tail = newTail;
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
