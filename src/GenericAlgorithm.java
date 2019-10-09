import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GenericAlgorithm {

    String pathToFile= "src/berlin52.txt";

    public void getArrayFromFileLines() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToFile));
        int arraySize=Integer.parseInt(lines.remove(0));
        String [][] distances=new String[arraySize][arraySize];

        for (int i = 0; i <lines.size() ; i++) {
            var temporaryArray=lines.get(i).split(" ");
            for (int j = 0; j <temporaryArray.length ; j++) {
                distances[i][j]=temporaryArray[j];
                distances[j][i]=temporaryArray[j];
            }
        }
        printTwoDimensionalArray(distances);
    }


    private void printTwoDimensionalArray(String [][] arrayToPrint){
        for (int i = 0; i <arrayToPrint.length ; i++) {
            for (int j = 0; j <arrayToPrint[i].length ; j++) {
                System.out.print(arrayToPrint[i][j] +" ");
            }
            System.out.println();
        }
    }




     /*public void loadFileLineByLine() throws IOException {
        BufferedReader fileToRead = null;
        try {
            fileToRead = new BufferedReader(new FileReader(pathToFile));
            String line = fileToRead.readLine();
            while (line != null) {
                System.out.println(line);
                System.out.println("XD");
                line = fileToRead.readLine();
            }
        } finally {
            if (fileToRead != null) {
                fileToRead.close();
            }
        }
    }*/
}
