package week2.homework;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] elements;
    private int size;


    // construct an empty randomized queue
    public RandomizedQueue() {
        this.elements = (Item[]) new Object[10];
        this.size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size == (elements.length / 4)) {
            resize(elements.length / 2);
        }

        int index = randomIndex();
        Item item = elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else {
            elements[index] = elements[size - 1];
            elements[size - 1] = null;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[randomIndex()];
    }

    private int randomIndex() {
        return StdRandom.uniform(size);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomDequeIterator();
    }

    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        if (elements.length < size) {
            size = elements.length;
        }
        for (int i = 0; i < size; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }

    private class RandomDequeIterator<Item> implements Iterator<Item> {

        private int index = 0;
        private Item[] r;

        public RandomDequeIterator() {
            r = (Item[]) new Object[size];
            copyQueue();
            StdRandom.shuffle(r);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return r[index++];
        }

        public void copyQueue() {
            for (int i = 0; i < size; i++) {
                r[i] = (Item) elements[i];
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();
        rqueue.enqueue(1);
        rqueue.enqueue(2);
        rqueue.enqueue(3);
        rqueue.enqueue(4);
        rqueue.enqueue(5);
        rqueue.enqueue(6);
        rqueue.enqueue(7);
        rqueue.enqueue(8);
        rqueue.enqueue(8);
        rqueue.enqueue(8);
        rqueue.enqueue(8);
        rqueue.enqueue(8);
        rqueue.enqueue(8);
        rqueue.enqueue(8);

        rqueue.enqueue(8);

        rqueue.dequeue();
        rqueue.dequeue();
        rqueue.dequeue();
        rqueue.dequeue();
        rqueue.dequeue();
        rqueue.dequeue();
        rqueue.dequeue();

        Iterator<Integer> it = rqueue.iterator();
        while (it.hasNext()) {
            int elt = it.next();
            System.out.println(elt);
        }
    }

}
