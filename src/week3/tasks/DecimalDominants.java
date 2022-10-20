package week3.tasks;

public class DecimalDominants {


    private static void partition(int a[], int l, int r) {
        int times = a.length / 10;
        int li = l, p = l, ri = r, lasti = l, count = 0;
        while (li <= ri) {
            if (a[li] < a[p]) {
                swap(a, li, p);
                li++;
                p++;
            } else if (a[li] > a[p]) {
                swap(a, ri, p);
                ri--;
            } else if (a[li] == a[p]) {
                if(li!=p) {
                    if (count > times) {
                        System.out.println(a[li]);
                        count = 0;
                        p = lasti + 1;
                        li = p;
                    }
                    count++;
                }
                li++;
            }
        }
    }




    private static void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 7, 3, 11, 8, 3, 4, 10};
        partition(array, 0, array.length - 1);
    }
}
