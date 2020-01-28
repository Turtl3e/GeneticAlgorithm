package selections;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;

public class Roulette implements Selection {

    int maxOldScore;
    int [] scoresAfterMaxSubtraction;
    int sumScoresAfterMaxSubtraction;

    @Override
    public Population preparePopulation(Population population) {
        setMaxOldPopulationScore(population.getScoreOfPopulationSpecimens());
        this.scoresAfterMaxSubtraction=substractNumberFromEachElement(population.getScoreOfPopulationSpecimens(),maxOldScore);
        this.sumScoresAfterMaxSubtraction= Utils.getSumElementsOfArray(scoresAfterMaxSubtraction);

        ArrayList<Specimen> newPopulation=new ArrayList<>();
//        System.out.println("#####MAX SCORE "+maxOldScore+" SumScoresAfterMaxSub "+sumScoresAfterMaxSubtraction);
//        System.out.println("Scores Population");
//        Utils.printOneDimensionalArray(population.getScoreOfPopulationSpecimens());
//        System.out.println("Scores After Sub");
//        Utils.printOneDimensionalArray(scoresAfterMaxSubtraction);

        for (int i = 0; i <population.getPopulationSize() ; i++) {
            newPopulation.add(spinTheSpecimensAndGetWinner(population));
        }
        return new Population(newPopulation);
    }

    private void setMaxOldPopulationScore(int [] oldScores) {
        this.maxOldScore = Utils.findMaxInArray(oldScores);
    }

    private int [] substractNumberFromEachElement(int [] array,int number){
        int [] arr=new int [array.length];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=number+1-array[i];
        }
        return arr;
    }

    private Specimen spinTheSpecimensAndGetWinner(Population population){
        int random= Utils.getRandomNumberExclusive(sumScoresAfterMaxSubtraction);
        int sum=0;
//        System.out.println("Wylosowany numer: "+random);
        for (int i = 0; i <scoresAfterMaxSubtraction.length ; i++) {
            sum+=scoresAfterMaxSubtraction[i];
            if(sum>random){
//                System.out.println("Wylosowano i "+i +" suma jest " +sum);
//                System.out.println("Wylosowany specimen score "+population.getSpecimen(i).getSpecimenScore());
                return population.getSpecimen(i);
            }
        }
        return null;
    }
}
