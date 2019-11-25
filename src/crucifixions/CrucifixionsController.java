package crucifixions;

import algorithm.Population;
import algorithm.Specimen;

import java.util.ArrayList;

public class CrucifixionsController {

    ArrayList<Pair> pairs=new ArrayList<>();

    public CrucifixionsController(ArrayList<Specimen> specimens){
        setPairs(Pairer.getPairsFromList(specimens));
        System.out.println("Pairs number: "+pairs.size());
    }

    private void setPairs(ArrayList<ArrayList<Specimen>> pairs) {
        for (int i = 0; i <pairs.size() ; i++) {
            this.pairs.add(new Pair(pairs.get(i)));
        }
    }

    public Pair getPair(int index){
        return pairs.get(index);
    }


    public class Pair{

        Specimen firstSpecimen;
        Specimen secondSpecimen;

        public Pair(ArrayList<Specimen> specimens){
            this.firstSpecimen=specimens.get(0);
            this.secondSpecimen=specimens.get(1);
        }

        public void crossByOX(){
            OX ox=new OX(firstSpecimen,secondSpecimen);
        }
    }
}
