package crosses;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class OX {

    public static ArrayList<Specimen> crossPair(Specimen firstSpecimen,Specimen secondSpecimen){

        int [] intersectionPoints=getIntersectionPoints(firstSpecimen.getSpecimenLength());
        Specimen newFirstSpecimen=new Specimen();
        Specimen newSecondSpecimen=new Specimen();

        newFirstSpecimen.setSpecimenBody(getNewBodyByIntersectionPoint(firstSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]));
        newSecondSpecimen.setSpecimenBody(getNewBodyByIntersectionPoint(secondSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]));

        int [] restOfFirstSpec=getRestOfCopyOfRange(firstSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]);
        int [] restOfSecondSpec=getRestOfCopyOfRange(secondSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]);

        fillBodyBy(newFirstSpecimen.getSpecimenBody(),restOfSecondSpec,intersectionPoints);
        fillBodyBy(newSecondSpecimen.getSpecimenBody(),restOfFirstSpec,intersectionPoints);

        return new ArrayList<>(){{add(newFirstSpecimen);add(newSecondSpecimen);}};
    }

    private static int [] getIntersectionPoints(int length){ // including 0 and 51 when 52 length;
        int firstPoint=0;
        int secondPoint=0;
        do{
            firstPoint= Utils.getRandomNumberExclusive(length);
            secondPoint=Utils.getRandomNumberExclusive(length);
        }while (firstPoint==secondPoint||(firstPoint==0&&secondPoint==length-1)||firstPoint>secondPoint);

//         System.out.println("Punkty przeciecia mniejszy " +firstPoint +" Wiekszy "+secondPoint);
        return new int []{firstPoint,secondPoint};
    }

    public static void fillBodyBy(int[] bodyToFill, int[] fillBy,int [] intersectionPoints){

        int bodyToFillIterator = intersectionPoints[1]+1;
        int fillByIterator = 0;

        if(bodyToFillIterator>=bodyToFill.length){bodyToFillIterator=0;}

        do {
            if (Utils.arrayContains(bodyToFill,fillBy[fillByIterator])) {
                fillByIterator++;
            }
            else{
                bodyToFill[bodyToFillIterator] = fillBy[fillByIterator];
                fillByIterator++;
                bodyToFillIterator++;
            }
            if(bodyToFillIterator == bodyToFill.length){
                bodyToFillIterator = 0;
            }

        } while (bodyToFillIterator != intersectionPoints[0]);
    }

    //TODO: rename
    private static int [] getRestOfCopyOfRange(int [] arr,int fromIndex, int toIndex){
        int [] firstPart = Arrays.copyOfRange(arr,0,toIndex+1);
        int [] secondPart = Arrays.copyOfRange(arr,toIndex+1,arr.length); //moze -1
        return Utils.concatArray(secondPart,firstPart);
    }

    private static int [] getNewBodyByIntersectionPoint(int [] oldBody,int lowerIntersectionPoint, int higerIntersectionPoint){
        int [] result=new int[oldBody.length];
        Utils.fillArrayBy(result,-1);
        for (int i = lowerIntersectionPoint; i <=higerIntersectionPoint ; i++) {
            result[i]=oldBody[i];
        }
        return result;
    }
}
