package crucifixions;

import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Pairer {

    public static ArrayList<ArrayList<Specimen>> getPairsFromList(ArrayList<Specimen> specimens){

        ArrayList<ArrayList<Specimen>> pairedList=new ArrayList<>();
        //TODO: Check if event?
        System.out.println("Even " +Utils.isEven(specimens.size()));
            for (int i = 0; i <specimens.size()/2 ; i++) {
                ArrayList<Specimen> pair=new ArrayList<>();
                pair.add(specimens.get(i));
                pair.add(specimens.get(specimens.size()-1-i));
                pairedList.add(pair);
            }
        return pairedList;
    }
}
