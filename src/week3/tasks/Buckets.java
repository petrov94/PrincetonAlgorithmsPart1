package week3.tasks;
import java.util.Arrays;

public class Buckets {

    public enum Pebble {
        RED, WHITE, BLUE
    }

    private Pebble[] pebbles;

    Buckets(Pebble[] pebbles) {
        this.pebbles = pebbles;
    }

    int color(int i) {
        return pebbles[i].ordinal();
    }

    void swap(int i, int j) {
        Pebble temp = pebbles[i];
        pebbles[i] = pebbles[j];
        pebbles[j] = temp;
    }

    void sort() {
        int red = 0, blue = pebbles.length - 1, white = 0;
        while (white <= blue) {
            int pebble = color(white);
            if (pebble < Pebble.WHITE.ordinal()) {
                swap(red++, white++);
            } else if (pebble > Pebble.WHITE.ordinal()) {
                swap(white, blue--);
            }
            if (pebble == Pebble.WHITE.ordinal()) white++;
        }
    }

    public static void main(String[] args) {
        Pebble[] array = new Pebble[]{Pebble.WHITE, Pebble.BLUE, Pebble.RED, Pebble.WHITE,Pebble.WHITE,Pebble.WHITE,Pebble.WHITE,Pebble.WHITE,
                Pebble.BLUE, Pebble.RED,Pebble.RED,Pebble.RED,Pebble.RED};
        new Buckets(array).sort();
        Arrays.stream(array).forEach(System.out::println);
    }
}


