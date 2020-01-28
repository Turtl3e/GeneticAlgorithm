package algorithm;

import myUtils.Utils;
import selections.Tournament;

import java.util.ArrayList;

public class Population {

    private int populationSize;

    ArrayList<Specimen> specimens;
    private int [] specimensScores;
    private Specimen theBestSpecimen;

    public Population(ArrayList<Specimen> specimens) {
        this.specimens = specimens;
        this.populationSize=specimens.size();
        this.specimensScores=new int[populationSize];
        this.calculateSpecimensScores();
        this.setBestSpecimen();
    }

    private void setBestSpecimen() {
        this.theBestSpecimen=Tournament.findTheBestSpecimen(this.specimens);
    }

    public Specimen getTheBestSpecimen() {
        return theBestSpecimen;
    }

    public int[] getScoreOfPopulationSpecimens(){
        return this.specimensScores;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    private void calculateSpecimensScores(){
        for (int i = 0; i <this.specimens.size() ; i++) {
            this.specimensScores[i]=this.specimens.get(i).getSpecimenScore();
        }
    }

    public void print(){
        this.specimens.stream().forEach((specimen -> Utils.printOneDimensionalArray(specimen.getSpecimenBody())));
    }

    public Specimen getSpecimen(int index){
        return specimens.get(index);
    }

    public ArrayList<Specimen> getSpecimens() {
        return specimens;
    }
}
