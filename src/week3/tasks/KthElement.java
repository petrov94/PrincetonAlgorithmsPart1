package week3.tasks;

public class KthElement {


    private static int getKthElement(int[] a, int[] b, int k, int starta, int enda, int startb,
            int endb) {
        if (starta > a.length - 1 ) {
            return b[k/2];
        }else if(startb > b.length - 1){
            return a[k/2];
        }
        int mida = starta + (enda - starta) / 2;
        int midb = startb + (endb - startb) / 2;

        if (k == (mida + midb)) {
            if(mida>a.length-1)
                return b[k/2];
            else if(midb>b.length-1)
                return a[k/2];
            return Integer.min(a[mida], b[midb]);

        }
        if (k < (mida + midb)) {
            if (a[mida] < b[midb]) {
                return getKthElement(a, b, k, starta, mida, startb, midb - 1);
            } else {
                return getKthElement(a, b, k, starta, mida - 1, startb, midb);
            }
        }
        if (a[mida] < b[midb]) {
            return getKthElement(a, b, k, mida + 1, enda, midb, endb);
        } else {
            return getKthElement(a, b, k, mida, enda, midb  + 1, endb);
        }
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 7, 9, 11};//5
        int[] array1 = new int[]{2, 4, 6, 8, 10, 12, 14};//6
        System.out.println(getKthElement(array, array1, 0, 0,array.length - 1,0, array1.length - 1));
    }


}
