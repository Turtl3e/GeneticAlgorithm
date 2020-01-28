package algorithm;

import myUtils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Specimen {

    private int [] specimenBody;
    private int specimenScore;

    public Specimen(){}

    public Specimen(int length,int [] array){
        specimenBody=new int [length];
        InitializeSpecimenBody(array);
        calculateSpecimenScore();
    }

    private void calculateSpecimenScore() {
        int score=0;
        for (int i = 0; i <this.specimenBody.length-1 ; i++) {
            score+=GeneticAlgorithm.getDistanceBeetwenCities(this.specimenBody[i],this.specimenBody[i+1]);
        }
        score+=GeneticAlgorithm.getDistanceBeetwenCities(this.specimenBody[this.specimenBody.length-1],this.specimenBody[0]);
        this.specimenScore=score;
    }

    private void InitializeSpecimenBody(int[] array){
        this.specimenBody=Arrays.copyOfRange(Utils.shuffleArray(array),0,specimenBody.length);
    }

    public void setSpecimenBody(int[] array) {
        this.specimenBody=array;
    }

    public int getSpecimenScore() {
        calculateSpecimenScore();
        return specimenScore;
    }

    public int[] getSpecimenBody() {
        return specimenBody;
    }

    public int getSpecimenLength(){
        return this.specimenBody.length;
    }

    public void castleGene(int firstGene,int secondGene){
       int tempGene= this.specimenBody[firstGene];
       this.specimenBody[firstGene]=this.specimenBody[secondGene];
       this.specimenBody[secondGene]=tempGene;
    }

}
