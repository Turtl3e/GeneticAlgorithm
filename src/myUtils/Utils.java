package myUtils;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Utils {

    public static void printTwoDimensionalArray(int [][] arrayToPrint){
        for (int i = 0; i <arrayToPrint.length ; i++) {
            for (int j = 0; j <arrayToPrint[i].length ; j++) {
                System.out.print(arrayToPrint[i][j] +"  ");
            }
            System.out.println();
        }
    }
    public static void printOneDimensionalArray(int[] arrayToPrint){
        for (int i = 0; i <arrayToPrint.length ; i++) {
            System.out.print(arrayToPrint[i] +" ");
        }
        System.out.println();
    }


    public static int findMaxInArray(int [] arr){
        int max=0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>=max){
                max=arr[i];
            }
        }
        return max;
    }

    public static int getSumElementsOfArray(int [] array){
        return IntStream.of(array).sum();
    }

    public static int getRandomNumberExclusive(int max){
        return (int)(Math.random()*max);
    }


}
