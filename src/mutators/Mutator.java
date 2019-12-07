package mutators;

import algorithm.Population;
import myUtils.Utils;

public class Mutator {

    public static void  mutate(Population population, int mutationProbability){
        for (int i = 0; i <population.getPopulationSize() ; i++) {
            for (int j = 0; j <population.getTheBestSpecimen().getSpecimenLength() ; j++) {
                int random= Utils.getRandomNumberExclusive(10001);
                if (random <= mutationProbability) {
//                    System.out.println("MUTATE");
                    var gene=getGeneToCastle(population.getTheBestSpecimen().getSpecimenLength());
                    population.getSpecimen(i).castleGene(gene[0],gene[1]);
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
        }while (firstGene==secondGene);
        return new int []{firstGene,secondGene};
    }
}
