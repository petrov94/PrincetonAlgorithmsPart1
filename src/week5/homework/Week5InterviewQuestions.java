package week5.homework;

import java.util.HashMap;
import java.util.List;

public class Week5InterviewQuestions {


    private static void fourSum(int [] numbers){
        HashMap<Integer, List<Integer>> nums = new HashMap<>();
        for(int firstIndex= 0 ; firstIndex<numbers.length-1; firstIndex++){
            for(int second = firstIndex+1; second<numbers.length;second++){
                Integer n = numbers[firstIndex]+ numbers[second];
                if(nums.containsKey(n)){
                    System.out.printf("%s + %s = %s + %s%n", numbers[firstIndex],numbers[second],nums.get(n).get(0),nums.get(n).get(1));
                }else {
                    nums.put(n,List.of(numbers[firstIndex], numbers[second]));
                }
            }
        }
    }

    public static void main(String [] args){
        fourSum(new int[]{ 1,2,3,4,5,6,7,8,9,10 });
    }

}
