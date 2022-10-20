package week2.tasks;

import java.util.Stack;

public class Queue{

    private static Stack<Integer> inbox = new Stack<>();
    private static Stack<Integer> outbox = new Stack<>();

    public static void main(String[] args) {
        enque(1);
        enque(2);
        enque(3);
        enque(4);
        System.out.println(denque());
        System.out.println(denque());
        System.out.println(denque());
        System.out.println(denque());
    }

    private static void enque(int element){
        inbox.push(element);
    }

    private static int denque(){
        migrate();
        return outbox.pop();
    }

    private static void migrate(){
        if(outbox.empty()){
           while (!inbox.empty()){
               int element = inbox.pop();
               outbox.push(element);
           }
        }
    }

}
