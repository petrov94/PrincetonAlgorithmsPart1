package week1.tasks;

public class UnionFindTask3 {

    private int elements[];
    private int size [];

    private UnionFindTask3(int size){
        this.elements = new int[size];
        this.size = new int[size];
        for(int i = 0; i < this.elements.length; i++){
            this.elements[i] = i;
            this.size[i] = i;
        }
    }

    private int root(int index){
        while(index != elements[index]){
            index = elements[index];
        }
        return index;
    }

    private void union(int firstElement, int secondElement){
        int firstRoot = root(firstElement);
        int secondRoot = root(secondElement);
        if(firstRoot == secondRoot){
            return;
        }
        if(size[firstRoot]>size[secondRoot]){
            elements[secondRoot]=firstRoot;
            size[firstRoot]+=size[secondRoot];
        }else{
            elements[firstRoot]=secondRoot;
            size[secondRoot]+=size[firstRoot];
        }
    }

    private void remove(int element){
        union(element, element+1);
    }

    private int findSuccessor(int element){
        int successor = element;
        while(element != elements[element]){
            if(successor>=elements[element]) return elements[element];
            element = elements[element];
        }
        return successor;
    }

    public static void main(String[] args) {
        UnionFindTask3 obj = new UnionFindTask3(6);
        obj.union(1,5);
        obj.union(2,4);
        obj.union(1,3);
        obj.union(5,2);
        obj.union(0,3);
        obj.union(2,1);
        obj.remove(2);
        System.out.println(obj.findSuccessor(2)+"   "+obj.root(2));
    }

}
