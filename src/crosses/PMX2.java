package crosses;

import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class PMX2 {

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
            firstChild[i] = firstSpecimen.getSpecimenBody()[i];
            secondChild[i] = secondSpecimen.getSpecimenBody()[i];
        }

        firstChild = exchangeGenes(firstSpecimen.getSpecimenBody(), firstChild, firstIntersectionPoint, secondIntersectionPoint, true);
        firstChild = exchangeGenes(firstSpecimen.getSpecimenBody(), firstChild, firstIntersectionPoint, secondIntersectionPoint, false);
        secondChild = exchangeGenes(secondSpecimen.getSpecimenBody(), secondChild, firstIntersectionPoint, secondIntersectionPoint, true);
        secondChild = exchangeGenes(secondSpecimen.getSpecimenBody(), secondChild, firstIntersectionPoint, secondIntersectionPoint, false);


        Specimen newSpec1=new Specimen();
        Specimen newSpec2=new Specimen();
        newSpec1.setSpecimenBody(firstChild);
        newSpec2.setSpecimenBody(secondChild);
//        Utils.printOneDimensionalArray(firstSpecimen.getSpecimenBody());
//        Utils.printOneDimensionalArray(newSpec1.getSpecimenBody());
//        Utils.printOneDimensionalArray(secondSpecimen.getSpecimenBody());
//        Utils.printOneDimensionalArray(newSpec2.getSpecimenBody());
//        System.out.println("X");

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
            while (Arrays.asList(child).contains(gen))
            {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
                gen = specimen[Arrays.asList(child).indexOf(gen)];
            }
            child[i] = gen;
        }
        return child;
    }
}
