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
        distances=getArrayFromFileLines(pathToFile);
        citiesIndex=getArrayWithCitiesIndexs(numberOfCities);


        Population population=new Population(numberOfCities,numberOfCities);
        fillPopulation(population);
        int [] scores=population.getScoreOfPopulationSpecimens();
        printOneDimensionalArray(scores);
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

}

