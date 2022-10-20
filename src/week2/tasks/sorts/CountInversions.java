package week2.tasks.sorts;

import java.util.Arrays;

public class CountInversions {

    private static int count = 0;

    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 1, 3, 5};//1//2
        mergeSort(array);
        System.out.println(count);
//        Arrays.stream(array).forEach(el -> System.out.println(el));
    }

    private static void mergeSort(int a[]) {
        int[] aux = new int[a.length];
        count(a, aux, 0, a.length - 1);
    }

    private static void count(int a[], int[] aux, int li, int hi) {
        int mid = li + ((hi - li) / 2);
        if (li >= hi) {
            return;
        }
        count(a, aux, li, mid);
        count(a, aux, mid + 1, hi);
        subCount(a, aux, li, mid, hi);
    }

    private static void subCount(int a[], int[] aux, int li, int mid, int hi) {
        for (int i = li; i <= hi; i++) {
            aux[i] = a[i];
        }

        int l = li, h = mid + 1;
        for (int i = li; i <= hi; i++) {
            if (h > hi) {
                a[i] = aux[l++];
            } else if (l > mid) {
                a[i] = aux[h++];
            } else if (aux[l] > aux[h]) {
                count += (mid + 1 - l);
                a[i] = aux[h++];
            } else if (aux[l] < aux[h]) {
                a[i] = aux[l++];
            } else if (aux[l] == aux[h]) {
                a[i] = aux[l++];
            }
        }
    }

}

