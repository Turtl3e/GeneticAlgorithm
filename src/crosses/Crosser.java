package crosses;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Crosser {

    //TODO: Move to function calling
    static int crossProbability= 850;

    public static Population crossPopulationByOX(Population population){

        ArrayList<Specimen> newPopulation=new ArrayList<>();

        for (int i = 0; i <population.getPopulationSize() ; i+=2) {
            int random=Utils.getRandomNumberExclusive(1001);

            if (random <= crossProbability){
                ArrayList<Specimen> crossedSpecimen=crossPair(population.getSpecimen(i),population.getSpecimen(i+1));
                newPopulation.addAll(crossedSpecimen);
            }else{
                newPopulation.add(population.getSpecimen(i));
                newPopulation.add(population.getSpecimen(i+1));
            }

        }
        return new Population(newPopulation);
    }


    private static ArrayList<Specimen> crossPair(Specimen firstSpecimen,Specimen secondSpecimen){
        int [] intersectionPoints=getIntersectionPoints(firstSpecimen.getSpecimenLength());
        Specimen newFirstSpecimen=new Specimen();
        Specimen newSecondSpecimen=new Specimen();

//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX First Spec");
//        Utils.printOneDimensionalArray(firstSpecimen.getSpecimenBody());
//
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX Second Spec");
//        Utils.printOneDimensionalArray(secondSpecimen.getSpecimenBody());

        newFirstSpecimen.setSpecimenBody(getNewBodyByIntersectionPoint(firstSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]));
        newSecondSpecimen.setSpecimenBody(getNewBodyByIntersectionPoint(secondSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]));

        int [] restOfFirstSpec=getRestOfCopyOfRange(firstSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]);
        int [] restOfSecondSpec=getRestOfCopyOfRange(secondSpecimen.getSpecimenBody(),intersectionPoints[0],intersectionPoints[1]);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXREST1");
//        Utils.printOneDimensionalArray(restOfFirstSpec);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXREST2");
//        Utils.printOneDimensionalArray(restOfSecondSpec);

        fillBodyBy(newFirstSpecimen.getSpecimenBody(),restOfSecondSpec,intersectionPoints);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX1");
//        Utils.printOneDimensionalArray(newFirstSpecimen.getSpecimenBody());
        fillBodyBy(newSecondSpecimen.getSpecimenBody(),restOfFirstSpec,intersectionPoints);
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX2");
//        Utils.printOneDimensionalArray(newSecondSpecimen.getSpecimenBody());

//        System.out.println("##################################Crossed PAIR###########################");
//        Utils.printOneDimensionalArray(firstSpecimen.getSpecimenBody());
//        Utils.printOneDimensionalArray(secondSpecimen.getSpecimenBody());
        return new ArrayList<>(){{add(newFirstSpecimen);add(newSecondSpecimen);}};
    }

    private static int [] getIntersectionPoints(int length){
        int firstPoint=0;
        int secondPoint=0;
        do{
            firstPoint= Utils.getRandomNumberExclusive(length);
            secondPoint=Utils.getRandomNumberExclusive(length);
        }while (firstPoint==secondPoint||(firstPoint==0&&secondPoint==length-1)||firstPoint>secondPoint);

//         System.out.println("Punkty przeciecia mniejszy " +firstPoint +" Wiekszy "+secondPoint);
         return new int []{firstPoint,secondPoint};
    }

    private static void fillBodyBy(int [] bodyToFill,int [] fillBy ,int [] intersectionPoints){

        for (int i = intersectionPoints[1]+1; i <bodyToFill.length ; i++) {
            for (int j = 0; j <fillBy.length ; j++) {
                int pointToWrite=fillBy[j];
                if(!Arrays.stream(bodyToFill).anyMatch(bodyPart -> bodyPart == pointToWrite)){
                    bodyToFill[i]=pointToWrite;
                    break;
                }
            }
        }
        for (int i = 0; i <intersectionPoints[0]; i++) {
            for (int j = 0; j <fillBy.length ; j++) {
                int pointToWrite=fillBy[j];
                if(!Arrays.stream(bodyToFill).anyMatch(bodyPart -> bodyPart == pointToWrite)){
                    bodyToFill[i]=pointToWrite;
                    break;
                }
            }
        }
    }

    //TODO: rename
    private static int [] getRestOfCopyOfRange(int [] arr,int fromIndex, int toIndex){
        int [] firstPart= Arrays.copyOfRange(arr,0,toIndex+1);
        int [] secondPart=Arrays.copyOfRange(arr,toIndex+1,arr.length); //moze -1
        int [] rest= Utils.concatArray(secondPart,firstPart);
        return rest;
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
