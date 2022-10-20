package week2.tasks;

public class StackMax {

    private static int[] elements = new int[10];
    private static int[] maxElements = new int[10];
    private static int count = 0;

    private static void push(int newElement) {
        if (count == elements.length) {
            resize(elements.length * 2);
            resize(maxElements.length * 2);
        }
        if (peekMax() < newElement) {
            maxElements[count] = newElement;
        } else {
            maxElements[count] = peekMax();
        }
        elements[count] = newElement;
        count++;
    }

    private static int pop() {
        if (count == (elements.length / 4)) {
            resize(elements.length / 2);
        }
        count--;
        int relement = elements[count];
        if (relement == peekMax()) {
            maxElements[count] = 0;
        }
        return relement;
    }

    private static int getMaxElement() {
        return peekMax();
    }


    private static int peekMax() {
        return count ==0 ? maxElements[0] : maxElements[count - 1];
    }

    private static void resize(int size) {
        int[] copy = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
        for (int i = 0; i < maxElements.length; i++) {
            copy[i] = maxElements[i];
        }
        maxElements = copy;
    }

    public static void main(String[] args) {
        push(5);
        push(4);
        push(10);
        push(11);
        System.out.println(getMaxElement());
        push(3);
        push(6);
        System.out.println(getMaxElement());
        System.out.println(pop());//6
        System.out.println(pop());//3
        System.out.println(pop());//11
        System.out.println(pop());//10
        System.out.println(getMaxElement());//5
        push(8);
        push(9);
        System.out.println(getMaxElement());//5
        pop();
        System.out.println(getMaxElement());//5


    }
}
