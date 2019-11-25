package crucifixions;

import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;

public class OX {

    int lowerIntersectionPoint;
    int higerIntersectionPoint;

    Specimen firstSpecimen;
    Specimen secondSpecimen;

    int [] newSpecimenBody1;
    int [] newSpecimenBody2;


    public OX(Specimen specimen1,Specimen specimen2){
        this.firstSpecimen=specimen1;
        this.secondSpecimen=specimen2;
        this.newSpecimenBody1=new int[specimen1.getSpecimenBody().length];
        this.newSpecimenBody2=new int[specimen1.getSpecimenBody().length];
        setIntersectionPoints();
    }

    private void setIntersectionPoints(){
        int specimensLength=firstSpecimen.getSpecimenBody().length;
        int firstPoint=0;
        int secondPoint=0;

        do{
            firstPoint= Utils.getRandomNumberExclusive(specimensLength);
            secondPoint=Utils.getRandomNumberExclusive(specimensLength);
        }while (firstPoint==secondPoint||(firstPoint==0&&secondPoint==specimensLength-1)); //TODO: Check if not include whole specimen body

        if(firstPoint>secondPoint){
            this.lowerIntersectionPoint=secondPoint;
            this.higerIntersectionPoint=firstPoint;
        }else{
            this.lowerIntersectionPoint=firstPoint;
            this.higerIntersectionPoint=secondPoint;
        }

        System.out.println("Punkty przeciecia wiekszy "+this.higerIntersectionPoint+" "+this.lowerIntersectionPoint);
    }

    public void cross(){
        this.firstSpecimen=null;
        this.secondSpecimen=null;
    }

    /*private createNewBodiesByIntersectionPoints(){

        ArrayList<Integer> restOfFirstSpecimen=new ArrayList<>();
        ArrayList<Integer> restOfSecondSpecimen=new ArrayList<>();

        if(lowerIntersectionPoint!=0){

        }

        for (int i = lowerIntersectionPoint; i <=higerIntersectionPoint ; i++) {
            this.newSpecimenBody1[i]=secondSpecimen.getSpecimenBody()[i];
            this.newSpecimenBody2[i]=firstSpecimen.getSpecimenBody()[i];
        }

        if(higerIntersectionPoint!=firstSpecimen.getSpecimenBody().length){

        }
    }*/
}
