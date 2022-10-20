package week2.tasks.sorts;

import java.util.Arrays;

public class MergeSortWithSmallAux {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 4, 6, 7, 8};
        mergeSort(array);
        Arrays.stream(array).forEach(el -> System.out.println(el));
    }

    private static void mergeSort(int a[]) {
        int[] secondArr = new int[a.length/2];
        sort(a, secondArr, 0, a.length - 1);
    }

    private static void sort(int a[], int a2[], int li, int hi) {
        if (li >= hi) {
            return;
        }
        int mid = li + ((hi - li) / 2);
        sort(a, a2, li, mid);
        sort(a, a2, mid + 1, hi);
        if (a[mid] < a[mid + 1]) {
            return;
        }
        merge(a, a2, li, mid, hi);
    }

    private static void merge(int a[], int a2[], int li, int mid, int hi) {
        for (int i = li; li<a2.length && i < a2.length; i++) {
            a2[i] = a[i];
        }
        int fi = li, si = mid + 1;
        for (int i = li; i <= hi; i++) {
            if (si > hi) {
                a[i] = a2[fi++];
            } else if (fi > mid) {
                a[i] = a[si++];
            } else if (a2[fi] < a[si]) {
                a[i] = a2[fi++];
            } else if (a2[fi] > a[si]) {
                a[i] = a[si++];
            } else {
                a[i] = a2[fi++];
            }
        }
    }

}
