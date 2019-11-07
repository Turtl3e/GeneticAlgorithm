import java.util.ArrayList;

public class Population {

    private int populationLength;
    private int populationCurrentLength=0;
    private int properSpecimenLength;
    private ArrayList<Specimen >specimens;
    private int [] specimensScores;

    public Population(int maxLength, int specimensLength){
        this.specimens=new ArrayList<>();
        this.populationLength=maxLength;
        this.properSpecimenLength=specimensLength;
        this.specimensScores=new int[this.populationLength];
    }

    public void addSpecimen(Specimen specimenToAdd){
        //TODO: Check if speciment has correct length
        if(canAddSpecimen()){
            this.specimens.add(specimenToAdd);
            this.populationCurrentLength++;
        }
    }

    private boolean canAddSpecimen(){
        return populationCurrentLength<populationLength;
    }

    public int[] getScoreOfPopulationSpecimens(){
        calculateSpecimensScores();
        return this.specimensScores;
    }

    private void calculateSpecimensScores(){
        for (int i = 0; i <this.specimens.size() ; i++) {
            this.specimensScores[i]=this.specimens.get(i).getSpecimenScore();
        }
    }

    public int getPopulationLength() {
        return populationLength;
    }

    public int getProperSpecimenLength() {
        return properSpecimenLength;
    }

    public Specimen getSpecimen(int index){
        return this.specimens.get(index);
    }

    public ArrayList<Specimen> getSpecimens() {
        return specimens;
    }
}
