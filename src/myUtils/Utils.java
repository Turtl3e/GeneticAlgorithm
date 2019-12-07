package myUtils;

import algorithm.Specimen;

import java.util.*;
import java.util.stream.IntStream;

public class Utils {

    public static void printTwoDimensionalArray(int [][] arrayToPrint){
        System.out.println(Arrays.toString(arrayToPrint));
    }

    public static void printOneDimensionalArray(int[] arrayToPrint){
        System.out.println(Arrays.toString(arrayToPrint));
    }

    public static void printOneDimensionalArray(List<Integer> arrayToPrint){
        for (int i = 0; i <arrayToPrint.size() ; i++) {
            System.out.print(arrayToPrint.get(i) +" ");
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

    public static int[] concatArray(int [] array1, int [] array2){
        int [] result=new int[array1.length+array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    public static void fillArrayBy(int [] array,int numberToFill){
        Arrays.fill(array,numberToFill);
    }

    public static int getSumElementsOfArray(int [] array){
        return IntStream.of(array).sum();
    }

    public static int getRandomNumberExclusive(int max){
        return (int)(Math.random()*max);
    }

    public static boolean isEven(int number){
        return !(number%2>0);
    }


}
