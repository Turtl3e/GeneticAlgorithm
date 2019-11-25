package algorithm;

import crucifixions.CrucifixionsController;
import myUtils.Utils;
import selections.Roulette;
import selections.Tournament;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class GeneticAlgorithm {

    static String pathToFile= "src/berlin52.txt";
    static int numberOfCities;
    static int [][] distances;
    static int [] citiesIndex;

    public static void main(String[] args) throws IOException{
        //TODO: Exclude Cities/Distances class?
        distances=getDistancesArray(getArrayFromFileLines(pathToFile));
        citiesIndex=getArrayWithCitiesIndexs(numberOfCities);

        Roulette roulette=new Roulette();
        Tournament tournament=new Tournament();

        Population population=new Population(10,10);
        fillPopulation(population);


        for (int i = 0; i <5 ; i++) {
            population.setTheBestSpecimen(Tournament.findTheBestSpecimen(population.getSpecimens()));
            System.out.println("########BEST SPECIMEN#########   " +population.getTheBestSpecimen().getSpecimenScore());
            Utils.printOneDimensionalArray(population.getTheBestSpecimen().getSpecimenBody());
            System.out.println("########Population scores#########   ");
            Utils.printOneDimensionalArray(population.getScoreOfPopulationSpecimens());
            System.out.println("#################   ");
            roulette.loadPopulation(population);
            tournament.loadPopulation(population);

            population.print();
            population.setSpecimens(roulette.getAllWinnerSpecimens());
            population.setSpecimens(tournament.getNewPopulationByTournaments());

            CrucifixionsController crucifixionsController=new CrucifixionsController(population.getSpecimens());
            crucifixionsController.getPair(1).crossByOX();
        }
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

    private static void fillPopulation(Population population){
        for (int i = 0; i <population.getPopulationLength() ; i++) {
            population.addSpecimen(new Specimen(population.getProperSpecimenLength(),citiesIndex));
        }
    }

    //static
    //Exclude?
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

