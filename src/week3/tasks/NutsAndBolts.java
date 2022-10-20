package week3.tasks;

import java.util.Arrays;

public class NutsAndBolts {

    private static int count = 0;

    private static int partition(char a[], int l, int r, char pivot) {
        if (l >= r) {
            return l;
        }
        int li = l, p = l, ri = r;
        while (li <= ri) {
            if (a[li] < pivot) {
                swap(a, li, p);
                li++;
                p++;
            } else if (a[li] > pivot) {
                swap(a, ri, p);
                ri--;
            } else if (a[li] == pivot) {
                count++;
                li++;
            }
        }
        return p;
    }


    private static void nutsAndBolts(char a[], char b[], int l, int r) {
        if (l >= r) {
            return;
        }

        int pivota = partition(a, l, r,b[l]);
        partition(b, l, r, a[pivota]);

        nutsAndBolts(a, b, l, pivota-1);
        nutsAndBolts(a, b, pivota + 1, r);
    }


    private static void swap(char[] a, int index1, int index2) {
        char temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }


    public static void main(String[] args) {
        char nuts[] = {'@', '#', '$', '%', '^', '&'};
        char bolts[] = {'$', '%', '&', '^', '@', '#'};
        nutsAndBolts(nuts,bolts,0,nuts.length-1);
        System.out.println(count);
    }
}
