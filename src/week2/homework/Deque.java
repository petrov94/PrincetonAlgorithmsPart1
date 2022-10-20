package week2.homework;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Deque<Item> implements Iterable<Item> {

    private class Node {

        private final Item value;
        private Node next;
        private Node previous;

        Node(Item value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

    }

    private Node firstElement;
    private Node lastElement;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        this.firstElement = null;
        this.lastElement = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return firstElement == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item==null) throw new IllegalArgumentException();
        if (firstElement == null) {
            firstElement = new Node(item, null, null);
            lastElement = firstElement;
        } else {
            Node oldFirst = firstElement;
            firstElement = new Node(item, oldFirst, null);
            oldFirst.previous=firstElement;
        }
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item==null) throw new IllegalArgumentException();
        if (firstElement == null) {
            Node newNode = new Node(item, null, null);
            firstElement = newNode;
            lastElement = newNode;
        }else {
            Node newLast = new Node(item, null, lastElement);
            lastElement.next = newLast;
            lastElement = newLast;
        }
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Optional.ofNullable(firstElement).orElseThrow(NoSuchElementException::new);
        Node reele = firstElement;
        if (firstElement.next != null) {
            firstElement = firstElement.next;
            firstElement.previous=null;
        } else {
            lastElement = null;
            firstElement = null;
        }
        --size;
        return reele.value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Optional.ofNullable(firstElement).orElseThrow(NoSuchElementException::new);
        Node reele = lastElement;
        if (lastElement.previous != null) {
            lastElement = lastElement.previous;
            lastElement.next=null;
        } else {
            lastElement = null;
            firstElement = null;
        }
        --size;
        return reele.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node current = firstElement;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node cur = current;
            current = current.next;
            return (Item) cur.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.isEmpty();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.removeLast();
        deque.addFirst(6);
        deque.removeLast();
        deque.isEmpty();
        deque.removeLast();
        Iterator<Integer> itr = deque.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}
