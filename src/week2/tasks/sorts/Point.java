package week2.tasks.sorts;

public class Point implements Comparable<Point> {

    private double pointX;
    private double pointY;

    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    private boolean isPerm(Comparable arr1[],Comparable arr2 []){
        insertionSort(arr1);
        insertionSort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private int countDublicates(Comparable arr1[],Comparable arr2 []){
        insertionSort(arr1);
        insertionSort(arr2);
        int count = 0;
        for(Comparable element: arr1) {
            if(isExist(arr2,element)){
                count++;
            }
        }
        return count;
    }

    private boolean isExist(Comparable arr[], Comparable a) {
        return binarySearch(arr,a,0,arr.length-1)!=-1;
    }

    private int binarySearch(Comparable arr[], Comparable a, int start, int end) {
        if (start < end) {
            int isBigger = arr[(end - start)].compareTo(a);
            if (isBigger == 0) {
                return end - start;
            } else if (isBigger > 0) {
                binarySearch(arr, a, start, end - 1);
            } else {
                binarySearch(arr, a, start + 1, end);
            }
        }
        return -1;
    }

    @Override
    public int compareTo(Point that) {
        if (that.pointY > this.pointY) {
            return -1;
        }
        if (that.pointY < this.pointY) {
            return 1;
        }
        if (that.pointX > this.pointX) {
            return -1;
        }
        if (that.pointX < this.pointX) {
            return 1;
        }
        return 0;
    }

    private static void insertionSort(Comparable a[]) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (isLess(a, j, j - 1)) {
                    swap(a, j, j - 1);
                }
            }
        }
    }

    private static boolean isLess(Comparable a[], int i, int j) {
        return a[i].compareTo(a[j]) == -1;
    }

    private static void swap(Comparable a[], int i, int j) {
        Comparable temp = a[j];
        a[j] = a[j - 1];
        a[j - 1] = temp;
    }
}
