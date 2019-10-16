import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenericAlgorithm {

    static String pathToFile= "src/berlin52.txt";
    static int numberOfCities;
    static int [][] distances;

    public static void main(String[] args) throws IOException{
        distances=getArrayFromFileLines(pathToFile);
        int [] citiesIndex=getArrayWithIndexs(numberOfCities);
        int [][] population=getPopulationWithNumberOfSpecimensAndTheirLength(citiesIndex,5,numberOfCities);
        printTwoDimensionalArray(distances);
        printTwoDimensionalArray(population);
        int [] popultionSpecimensScores=getScoreOfPopulationSpecimens(population);

        printOneDimensionalArray(popultionSpecimensScores);


    }

    private static int[][] getArrayFromFileLines(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        numberOfCities=Integer.parseInt(lines.remove(0));
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

    private static int [] getArrayWithIndexs(int toNumber){
        int[] array=new int[toNumber];
        for (int i = 0; i <toNumber ; i++) {
            array[i]=i;
        }
        return array;
    }

    private static int [] getTheSpecimen(int []array,int specimensLength){
        List<Integer>list =Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return Arrays.copyOfRange(list.stream().mapToInt(i->i).toArray(),0,specimensLength);
    }

    private static int [][] getPopulationWithNumberOfSpecimensAndTheirLength(int[] citiesIndex,int numberOfSpecimens, int specimensLength){
            int [][] population=new int[numberOfSpecimens][specimensLength];
        for (int i = 0; i <population.length ; i++) {
            population[i]=getTheSpecimen(citiesIndex,specimensLength);
        }
        return population;
    }

    private static int[] getScoreOfPopulationSpecimens(int [][] population){
        int []scores=new int[population.length];

        for (int i = 0; i <population.length ; i++) {
            scores[i]=getScoreOfSpecimen(population[i]);
        }
        return scores;
    }

    private static int getScoreOfSpecimen(int [] specimen){
        int score=0;
        for (int i = 0; i <specimen.length-1 ; i++) {
            score+=getDistanceBeetwenCities(specimen[i],specimen[i+1]);
        }
        score+=getDistanceBeetwenCities(specimen[specimen.length-1],specimen[0]);
        return score;
    }

    private static int getDistanceBeetwenCities(int cities1, int cities2){
        return distances[cities1][cities2];
    }

    //Helpers
    private static void printTwoDimensionalArray(int [][] arrayToPrint){
        for (int i = 0; i <arrayToPrint.length ; i++) {
            for (int j = 0; j <arrayToPrint[i].length ; j++) {
                System.out.print(arrayToPrint[i][j] +"  ");
            }
            System.out.println();
        }
    }
    private static void printOneDimensionalArray(int[] arrayToPrint){
        for (int i = 0; i <arrayToPrint.length ; i++) {
                System.out.print(arrayToPrint[i] +" ");
        }
    }

    /*private static int [] getRandomizedArray(int []array){
        List<Integer>list =Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list.stream().mapToInt(i->i).toArray();
    }*/

    //Older code
    
    /*private static int[] getScoreOfPopulationSpecimens(int [][] population){
        int []scores=new int[population.length];

        for (int i = 0; i <population.length ; i++) {
            int score=0;
            for (int j = 0; j <population[i].length-1 ; j++) {
                score+=getDistanceBeetwenCities(population[i][j],population[i][j+1]);
            }
            score+=getDistanceBeetwenCities(population[i][population[i].length-1],population[i][0]);
            scores[i]=score;
        }
        return scores;
    }*/
}

