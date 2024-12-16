package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> , Iterable<T> {
    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
            prev = null;
            next = null;
        }

        Node() {
            data = null;
            prev = null;
            next = null;
        }
    }

    private Node frontSentinel;
    private Node backSentinel;
    private int size;

    public LinkedListDeque() {
        frontSentinel = new Node();
        backSentinel = new Node();
        size = 0;
        frontSentinel.next = backSentinel;
        backSentinel.prev = frontSentinel;
    }

    @Override
    public void addFirst(T data) {
        Node newNode = new Node(data);
        Node tmp = frontSentinel.next;
        frontSentinel.next = newNode;
        newNode.prev = frontSentinel;
        newNode.next = tmp;
        tmp.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T data) {
        Node newNode = new Node(data);
        Node tmp = backSentinel.prev;
        backSentinel.prev = newNode;
        newNode.next = backSentinel;
        newNode.prev = tmp;
        tmp.next = newNode;
        size++;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = frontSentinel.next;
        T tmp = first.data;
        frontSentinel.next = first.next;
        first.next.prev = frontSentinel;
        first.data = null;
        size--;
        return tmp;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = backSentinel.prev;
        T tmp = last.data;
        backSentinel.prev = last.prev;
        last.prev.next = backSentinel;
        backSentinel.data = null;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
        if (size == 0 || index < 0 || index >= size) {
            return null;
        }
        int i = 0;
        Node current = frontSentinel.next;
        while (i != index) {
            current = current.next;
        }
        return current.data;
    }

    public T getRecursive(int index){
        if (size == 0 || index < 0 || index > size) {
            return null;
        }
        return getRecursiveHelper(index, frontSentinel.next);
    }

    private T getRecursiveHelper(int index, Node currentNode) {
        if (index == 0) {
            return currentNode.data;
        }
        return getRecursiveHelper(index - 1, currentNode.next);
    }

    @Override
    public void printDeque() {
        for (Node p = frontSentinel.next; p != null; p = p.next) {
            System.out.println(p.data + " ");
        }
        System.out.println();
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof LinkedListDeque) {
            LinkedListDeque other = (LinkedListDeque) o;
            if (size != other.size) {
                return false;
            }
            else {
                for (int i = 0; i < size; i++) {
                    if (other.get(i) != this.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

   @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current = frontSentinel;
        public boolean hasNext() {
            return current.next != backSentinel;
        }
        public T next() {
            T tmp = current.data;
            current = current.next;
            return tmp;
        }
    }
}


