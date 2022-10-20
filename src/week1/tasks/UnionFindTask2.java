package week1.tasks;

public class UnionFindTask2 {

    private int elements[];
    private int size[];
    private int largeElements[];

    private UnionFindTask2(int size) {
        this.elements = new int[size];
        this.size = new int[size];
        this.largeElements = new int[size];
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i] = i;
            this.size[i] = i;
            this.largeElements[i]=i;
        }
    }

    private int root(int index) {
        while (index != elements[index]){
            index = elements[index];
        }
        return index;
    }

    private void union(int firstElement, int secondElement) {
        int firstRoot = root(firstElement);
        int secondRoot = root(secondElement);
        if (firstRoot == secondRoot) {
            System.out.println(String.format("The elements %d and %d are already connected.", firstElement, secondElement));
            return;
        }
        if (size[firstRoot] > size[secondRoot]) {
            elements[secondRoot] = firstRoot;
            size[firstRoot] += size[secondRoot];
        } else {
            elements[firstRoot] = secondRoot;
            size[secondRoot] += size[firstRoot];
        }
        if(largeElements[firstElement]>largeElements[secondElement]){
            largeElements[secondElement]=largeElements[firstElement];
            System.out.println("LargeElement "+ largeElements[firstElement] );
        }else {
            largeElements[firstElement]=largeElements[secondElement];
            System.out.println("LargeElement "+ largeElements[secondElement]);
        }
    }

    private boolean connected(int firstElement, int secondElement) {
        int firstRoot = root(firstElement);
        int secondRoot = root(secondElement);
        if (firstRoot == secondRoot) {
            System.out.println(String.format("The elements %d and %d are already connected.", firstElement, secondElement));
            return true;
        }else {
            return false;
        }
    }

    private int find(int searchIndex){
        return largeElements[searchIndex];
    }

    public static void main(String[] args) {
        UnionFindTask2 obj = new UnionFindTask2(8);
        obj.union(1,5);
        obj.union(2,1);
        obj.union(0,1);
        obj.union(4,6);
        obj.union(6,7);
        obj.union(7,3);
        System.out.println("Result   " + obj.find(0));
    }
}
