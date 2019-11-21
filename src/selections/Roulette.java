package selections;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;

public class Roulette {

    Population oldPopulation; //TODO: Use only specimens table.
    int maxOldScore;
    int [] scoresAfterMaxSubtraction;
    int sumScoresAfterMaxSubtraction;

    public void loadPopulation(Population population){
        this.oldPopulation=population;
        setMaxOldScore();
        this.scoresAfterMaxSubtraction=substractNumberFromEachElement(population.getScoreOfPopulationSpecimens(),maxOldScore);
        this.sumScoresAfterMaxSubtraction= Utils.getSumElementsOfArray(scoresAfterMaxSubtraction);
    }

    private void setMaxOldScore() {
        this.maxOldScore = Utils.findMaxInArray(oldPopulation.getScoreOfPopulationSpecimens());
    }

    //TODO: Wrong method name or method should be in Array class
    private int [] substractNumberFromEachElement(int [] array,int number){
        int [] arr=new int [array.length];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=number+1-array[i];
        }
        return arr;
    }

    private Specimen spinTheSpecimensAndGetWinner(){
        int random= Utils.getRandomNumberExclusive(sumScoresAfterMaxSubtraction);
        int sum=0;
        Specimen specimenToReturn=null;
        for (int i = 0; i <scoresAfterMaxSubtraction.length ; i++) {
            sum+=scoresAfterMaxSubtraction[i];
            if(sum>random){
                specimenToReturn=oldPopulation.getSpecimen(i);
                break;
            }
        }
        return specimenToReturn;
    }

    public ArrayList<Specimen> getAllWinnerSpecimens(){
        ArrayList<Specimen> specimens=new ArrayList<>();
        for (int i = 0; i <oldPopulation.getPopulationLength() ; i++) {
            specimens.add(spinTheSpecimensAndGetWinner());
        }
        return specimens;
    }
}
