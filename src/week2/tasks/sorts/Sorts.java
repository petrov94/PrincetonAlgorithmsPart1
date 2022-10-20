package week2.tasks.sorts;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import javax.naming.PartialResultException;

public class Sorts {

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 4, 1, 10};
        quicksort3way(array,0,array.length-1);
        Arrays.stream(array).forEach(el -> System.out.println(el));
    }

    private static void selectionSort(int a[]) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }

    private static void insertionSort(int a[]) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }

    private static void shellSort(int a[]) {
        int N = a.length;
        int steps = 0;
        while (steps < N / 3) {
            steps = 3 * steps + 1;
        }

        while (steps > 0) {
            for (int i = steps; i < N; i++) {
                for (int j = i; j >= steps; j -= steps) {
                    if (a[j] < a[j - steps]) {
                        int temp = a[j];
                        a[j] = a[j - steps];
                        a[j - steps] = temp;
                    }
                }
            }
            steps /= 3;
        }
    }

    private static void mergeSort(int a[]) {
        int[] secondArr = new int[a.length];
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
        for (int i = li; i <= hi; i++) {
            a2[i] = a[i];
        }
        int fi = li, si = mid + 1;
        for (int i = li; i <= hi; i++) {
            if (si > hi) {
                a[i] = a2[fi++];
            } else if (fi > mid) {
                a[i] = a2[si++];
            } else if (a2[fi] < a2[si]) {
                a[i] = a2[fi++];
            } else if (a2[fi] > a2[si]) {
                a[i] = a2[si++];
            } else {
                a[i] = a2[fi++];
            }
        }
    }


    private static void mergeBottomUp1(int a[]) {
        int N = a.length;
        int[] arr2 = new int[N];
        for (int i = 1; i < N; i = 2 * i) {
            for (int j = 0; j < (N - i); j += 2 * i) {
                merge(a, arr2, j, j + i, Math.min(j + 2 * i, N - 1));
            }
        }
    }

    private static void mergeBottomUp(int a[]) {
        int N = a.length;
        int[] aux = new int[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) // lo: subarray index
            {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    private static void quickSort(int a[]) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(int a[], int low, int high) {
        if (low >= high) {
            return;
        }
        int median = medianOf3(a, low, low + (high - low) / 2, high);
        int temp = a[low];
        a[low] = a[median];
        a[median] = temp;
        int pivot = partition(a, low, high);
        sort(a, low, pivot - 1);
        sort(a, pivot + 1, high);
    }

    private static int partition(int[] a, int low, int high) {
        int li = low + 1, hi = high;
        while (true) {

            while (li < high && a[low] > a[li]) {
                if (li == high) {
                    break;
                }
                li++;
            }

            while (hi > low && a[low] < a[hi]) {
                if (hi == low) {
                    break;
                }
                --hi;
            }

            if (li < hi) {
                int temp = a[li];
                a[li] = a[hi];
                a[hi] = temp;
            } else {
                break;
            }

        }

        int temp = a[low];
        a[low] = a[hi];
        a[hi] = temp;

        return hi;
    }


    private static int quickSelect(int a[], int element) {

        int li = 0, hi = a.length - 1;

        while (hi > li) {
            int j = partition(a, li, hi);
            if (element < j) {
                hi = j - 1;
            } else if (element > j) {
                li = j + 1;
            }
            if (j == element) {
                return a[j];
            }
        }

        return a[element];
    }

    private static void quicksort3way(int a[], int l, int r) {
        if(l>=r) {
            return;
        }
        int li = l, pe = l, hi = r;
        while (li <= hi) {
            if(a[li]<a[pe]){
                swap(a,li,pe);
                pe++;
                li++;
            }else if(a[li]>a[pe]){
                swap(a,li,hi);
                hi--;
            }else if(a[li]==a[pe]){
                li++;
            }
        }
        quicksort3way(a,l,li-1);
        quicksort3way(a, li+1,r);
    }

    private static void swap(int[] a,int index1, int index2){
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    private static int medianOf3(int a[], int low, int mid, int high) {
        if (a[low] > a[mid] && a[low] < a[high]) {
            return low;
        } else if (a[mid] > a[high] && a[mid] < a[low]) {
            return mid;
        } else {
            return high;
        }
    }


}
