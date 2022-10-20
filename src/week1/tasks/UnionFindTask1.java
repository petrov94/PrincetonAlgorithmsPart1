package week1.tasks;

import java.util.Date;

public class UnionFindTask1 {

   private int friends [];
   private int size [];
   private int friendshipRequest = 0;

    private UnionFindTask1(int size){
        this.friends = new int[size];
        this.size = new int[size];
        for(int i = 0; i < this.friends.length; i++){
            this.friends[i] = i;
            this.size[i] = i;
        }
    }

    private int root(int index){
        while(index != friends[index]){
            index = friends[index];
        }
        return index;
    }

    private void union(int firstElement, int secondElement, String timestamp){
        int firstRoot = root(firstElement);
        int secondRoot = root(secondElement);
        if(firstRoot == secondRoot){
            if(friendshipRequest==(friends.length-1)){
                System.out.println("All members are connected at " + timestamp);
            }
            System.out.println(String.format("The elements %d and %d are already connected.", firstElement, secondElement));
            return;
        }
        if(size[firstRoot]>size[secondRoot]){
            friends[secondRoot]=firstRoot;
            size[firstRoot]+=size[secondRoot];
        }else{
            friends[firstRoot]=secondRoot;
            size[secondRoot]+=size[firstRoot];
        }
        friendshipRequest++;
    }



    public static void main(String[] args) {
        UnionFindTask1 obj = new UnionFindTask1(6);
        obj.union(1,5, "2019-08-14 18:00:00");
        obj.union(2,4, "2019-08-14 18:00:01");
        obj.union(1,3, "2019-08-14 18:00:02");
        obj.union(5,2, "2019-08-14 18:00:03");
        obj.union(0,3,"2019-08-14 18:00:04");
        obj.union(2,1,"2019-08-14 18:00:05");
    }




}
