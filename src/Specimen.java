import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Specimen {

    int [] specimenBody;
    int specimenScore;

    public Specimen(int length,int [] array){
        specimenBody=new int [length];
        setSpecimenBody(array);
        setSpecimenScore();
    }

    private void setSpecimenScore() {
        int score=0;
        for (int i = 0; i <this.specimenBody.length-1 ; i++) {
            this.specimenScore+=GeneticAlgorithm.getDistanceBeetwenCities(this.specimenBody[i],this.specimenBody[i+1]);
        }
        this.specimenScore+=GeneticAlgorithm.getDistanceBeetwenCities(this.specimenBody[this.specimenBody.length-1],this.specimenBody[0]);
    }

    private void setSpecimenBody(int[] array) {
        //TODO: Maybe add flag if suffle or not.
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        this.specimenBody=Arrays.copyOfRange(list.stream().mapToInt(i->i).toArray(),0,this.specimenBody.length);
    }

    public int getSpecimenScore() {
        return specimenScore;
    }

    public int[] getSpecimenBody() {
        return specimenBody;
    }

}
