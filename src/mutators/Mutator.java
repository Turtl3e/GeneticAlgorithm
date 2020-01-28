package mutators;

import algorithm.Population;
import myUtils.Utils;

import java.util.Arrays;

public class Mutator {

    public static void  mutate(Population population, int mutationProbability){
        for (int i = 0; i <population.getPopulationSize() ; i++) {
            for (int j = 0; j <population.getTheBestSpecimen().getSpecimenLength() ; j++) {
                int random= Utils.getRandomNumberExclusive(10001);
                if (random <= mutationProbability) {
                    var genes=getGeneToCastle(population.getTheBestSpecimen().getSpecimenLength());
                    population.getSpecimen(i).castleGene(genes[0],genes[1]);
                }
            }
        }
    }

//    public static void  mutate(Population population, int mutationProbability){
//        for (int i = 0; i <population.getPopulationSize() ; i++) {
//            for (int j = 0; j <population.getTheBestSpecimen().getSpecimenLength() ; j++) {
//                int random= Utils.getRandomNumberExclusive(10001);
//                if (random <= mutationProbability) {
//                    int geneToCastle=Utils.getRandomNumberExclusive(j,population.getTheBestSpecimen().getSpecimenLength());
//                    population.getSpecimen(i).castleGene(j,geneToCastle);
//                }
//            }
//        }
//    }

    public static void mutateByInversion(Population population,int mutationProbability){
        int specimenLength=population.getTheBestSpecimen().getSpecimenLength();

        for (int i = 0; i <population.getPopulationSize() ; i++) {
            int random= Utils.getRandomNumberExclusive(10001);
            if (random <= mutationProbability) {

                var points=getGeneToCastle(specimenLength);
                int[] newArray = Arrays.copyOfRange(population.getSpecimen(i).getSpecimenBody(), points[0], points[1]);

                Utils.reverseArray(newArray);

                for (int j = points[0]; j <points[1] ; j++) {
                        population.getSpecimen(i).getSpecimenBody()[j]=newArray[j-points[0]];
                }
            }
        }

    }

    private static int [] getGeneToCastle(int length){
        int firstGene=0;
        int secondGene=0;
        do{
            firstGene= Utils.getRandomNumberExclusive(length);
            secondGene=Utils.getRandomNumberExclusive(length);
        }while (firstGene==secondGene||firstGene>secondGene); //
        return new int []{firstGene,secondGene};
    }


}
