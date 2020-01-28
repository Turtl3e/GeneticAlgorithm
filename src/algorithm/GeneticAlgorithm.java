package algorithm;

import crosses.Crosser;
import crosses.PMX;
import crosses.PMX2;
import mutators.Mutator;
import myUtils.Utils;
import selections.Roulette;
import selections.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class GeneticAlgorithm {

    static String pathToFile= "src/pr1002.txt";            //populacja 100, pmx 45%, mutacja 25%, tournament size 10
//    static String pathToFile= "src/berlin52.txt";
    static int numberOfCities;
    static int [][] distances;
    static int [] citiesIndex;
    static Specimen theBestSpecimen;

    public static void main(String[] args) throws IOException {
        distances=getDistancesArray(getArrayFromFileLines(pathToFile));
        citiesIndex=getArrayWithCitiesIndexs(numberOfCities);

        Population population=new Population(prepareBasePopulation(100,numberOfCities)); //1002-150
        Tournament tournament= new Tournament();
        Roulette roulette=new Roulette();
        theBestSpecimen=population.getTheBestSpecimen();

        for (int i = 0; i <100000 ; i++) {
            var selected=tournament.preparePopulation(population);
//            var selected=roulette.preparePopulation(population);

            var crossedPop= Crosser.crossPopulation(selected,450, PMX2.class); //920 berling // 1002-450
            population=crossedPop;

           // Mutator.mutate(population,135); //135 berlin
            Mutator.mutateByInversion(population,250); //250-1002
            if(population.getTheBestSpecimen().getSpecimenScore()<theBestSpecimen.getSpecimenScore()){
                theBestSpecimen=population.getTheBestSpecimen();
                System.out.println("Iteracja:" +i +" "+theBestSpecimen.getSpecimenScore());
            }

        }
        Utils.printOneDimensionalArray(theBestSpecimen.getSpecimenBody());

    }

    private static List<String> getArrayFromFileLines(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        numberOfCities=Integer.parseInt(lines.remove(0));
        return lines;
    }

    private static int [][] getDistancesArray(List<String>lines){
        int [][] distances=new int[numberOfCities][numberOfCities];

        for (int i = 0; i <lines.size() ; i++) {
            var temporaryArray=lines.get(i).split(" ");
            for (int j = 0; j <temporaryArray.length ; j++) {
                distances[i][j]=Integer.parseInt(temporaryArray[j]);
                distances[j][i]=Integer.parseInt(temporaryArray[j]);
            }
        }
        return distances;
    }

    private static ArrayList<Specimen> prepareBasePopulation(int populationLength, int specimensLegth){
        ArrayList<Specimen> population=new ArrayList<>();
        for (int i = 0; i <populationLength ; i++) {
            population.add(new Specimen(specimensLegth,citiesIndex));
        }
        return population;
    }

    public static int getDistanceBeetwenCities(int cities1, int cities2){
        return distances[cities1][cities2];
    }

    private static int [] getArrayWithCitiesIndexs(int toNumber){
        int[] array=new int[toNumber];
        for (int i = 0; i <toNumber ; i++) {
            array[i]=i;
        }
        return array;
    }
}

