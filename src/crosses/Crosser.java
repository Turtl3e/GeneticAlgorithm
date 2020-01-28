package crosses;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Crosser {

    public static Population crossPopulation(Population population, int crossProbability, Type type) {
        ArrayList<Specimen> newPopulation=new ArrayList<>();

        for (int i = 0; i <population.getPopulationSize() ; i+=2) {
            int random= Utils.getRandomNumberExclusive(1001);

            if (random <= crossProbability){
                ArrayList<Specimen> crossedSpecimens=new ArrayList<>();
                if(type==OX.class){
                    crossedSpecimens=OX.crossPair(population.getSpecimen(i),population.getSpecimen(i+1));
                }else if (type==PMX.class){
                    crossedSpecimens=PMX.crossPair(population.getSpecimen(i),population.getSpecimen(i+1));
                }
                newPopulation.addAll(crossedSpecimens);
            }else{
                newPopulation.add(population.getSpecimen(i));
                newPopulation.add(population.getSpecimen(i+1));
            }
        }
        return new Population(newPopulation);
    }
}
