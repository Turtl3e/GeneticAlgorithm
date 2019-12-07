package selections;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament implements Selection {

    static double selectivePressure=0.085;

    @Override
    public Population preparePopulation(Population population) {

        ArrayList<Specimen> newPopulation=new ArrayList<>();

        for (int i = 0; i <population.getPopulationSize() ; i++) {
            ArrayList<Specimen> specimens= drawSpecimensForTournamentsWithPressure(population.getSpecimens());
            var theBestSpec=findTheBestSpecimen(specimens);
            newPopulation.add(theBestSpec);
        }
        return new Population(newPopulation);
    }


    private ArrayList<Specimen> drawSpecimensForTournamentsWithPressure(ArrayList<Specimen> population){
        int specimensToDraw=(int)(population.size()*selectivePressure);
        ArrayList<Specimen> drawedSpecimens= new ArrayList<>();

        for (int i = 0; i <specimensToDraw ; i++) {
            // TODO: GetRandomSpecimen to add();
            var drawedSpecimen=getRandomSpecimenFromPopulation(population);
            drawedSpecimens.add(drawedSpecimen);

        }
        return drawedSpecimens;
    }

    public static Specimen findTheBestSpecimen(ArrayList<Specimen> specimens){
        Specimen winner= Collections.min(specimens,Comparator.comparing(specimen->specimen.getSpecimenScore()));
        return winner;
    }

    private static Specimen getRandomSpecimenFromPopulation(ArrayList<Specimen> population){
        int random=Utils.getRandomNumberExclusive(population.size());
        return population.get(random);
    }
}
