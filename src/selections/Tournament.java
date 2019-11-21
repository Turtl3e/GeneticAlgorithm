package selections;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament {

    Population oldPopulation;
    double selectivePressure=0.1;


    public void loadPopulation(Population oldPopulation) {
        this.oldPopulation = oldPopulation;
    }

    public double getSelectivePressure() {
        return selectivePressure;
    }

    public void setSelectivePressure(double selectivePressure) {
        this.selectivePressure = selectivePressure;
    }

    ArrayList<Specimen> drawSpecimensForTournamentsWithPressure(){
        int specimensToDraw=(int)(oldPopulation.getPopulationLength()*selectivePressure);
        ArrayList<Specimen> drawedSpecimens= new ArrayList<>();

        for (int i = 0; i <specimensToDraw ; i++) {
            drawedSpecimens.add(getRandomSpecimen());
        }
        return drawedSpecimens;
    }

    private Specimen getRandomSpecimen(){
        int random=Utils.getRandomNumberExclusive(this.oldPopulation.getPopulationLength());
        return oldPopulation.getSpecimen(random);
    }

    public ArrayList<Specimen> getNewPopulationByTournaments(){
        ArrayList<Specimen> population=new ArrayList<>();

        for (int i = 0; i <oldPopulation.getPopulationLength() ; i++) {
            ArrayList<Specimen> specimens= drawSpecimensForTournamentsWithPressure();
            population.add(getWinnerOfTournament(specimens));
        }
        return population;
    }

    private Specimen getWinnerOfTournament(ArrayList<Specimen> specimens){
        Specimen winner= Collections.max(specimens,Comparator.comparing(specimen->specimen.getSpecimenScore()));
        return winner;
    }
}
