package crosses;

import algorithm.Specimen;
import myUtils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class PMX {

    public static ArrayList<Specimen> crossPair(Specimen firstSpecimen, Specimen secondSpecimen) {

        int specimensLenght=firstSpecimen.getSpecimenLength();

        int[] firstChild = new int[specimensLenght];
        int[] secondChild = new int[specimensLenght];

        for (int i = 0; i < specimensLenght; i++)
        {
            firstChild[i] = -1;
            secondChild[i] = -1;
        }

        int firstIntersectionPoint = Utils.getRandomNumberExclusive(0,specimensLenght-1);
        int secondIntersectionPoint = Utils.getRandomNumberExclusive(firstIntersectionPoint+1,specimensLenght);

        for (int i = firstIntersectionPoint; i <= secondIntersectionPoint; i++)
        {
            firstChild[i] = secondSpecimen.getSpecimenBody()[i];
            secondChild[i] = firstSpecimen.getSpecimenBody()[i];
        }

        firstChild = exchangeGenes(firstSpecimen.getSpecimenBody(), firstChild, firstIntersectionPoint, secondIntersectionPoint, true);
        firstChild = exchangeGenes(firstSpecimen.getSpecimenBody(), firstChild, firstIntersectionPoint, secondIntersectionPoint, false);
        secondChild = exchangeGenes(secondSpecimen.getSpecimenBody(), secondChild, firstIntersectionPoint, secondIntersectionPoint, true);
        secondChild = exchangeGenes(secondSpecimen.getSpecimenBody(), secondChild, firstIntersectionPoint, secondIntersectionPoint, false);


        Specimen newSpec1=new Specimen();
        Specimen newSpec2=new Specimen();
        newSpec1.setSpecimenBody(firstChild);
        newSpec2.setSpecimenBody(secondChild);

        return new ArrayList<>(){{add(newSpec1);add(newSpec2);}};
    }

    private static int[] exchangeGenes(int[] specimen, int[] child, int firstIntersectionPoint, int secondIntersectionPoint, boolean fromBeginning)
    {
        int from = 0;
        int to = firstIntersectionPoint;

        if (!fromBeginning)
        {
            from = secondIntersectionPoint+1;
            to = child.length;
        }

        for (int i = from; i < to; i++)
        {
            int gen = specimen[i];
            while (contains(child,gen))
            {
                gen = specimen[getArrayIndex(child,gen)];
            }
            child[i] = gen;
        }
        return child;
    }

    public static int getArrayIndex(int[] arr,int value) {
        for(int i=0;i<arr.length;i++){

            if(arr[i]==value){
               return i;
            }
        }
        return -1;
    }

    public static boolean contains(int[] arr,int value) {
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return true;
            }
        }
        return false;
    }
}
