package crosses;

import algorithm.Population;
import algorithm.Specimen;
import myUtils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class PMX {

    public static Population crossByPMX(Population population,int crossProbability){

        ArrayList<Specimen> newPopulation=new ArrayList<>();

        for (int i = 0; i <population.getPopulationSize() ; i+=2) {
            int random= Utils.getRandomNumberExclusive(1001);

            if (random <= crossProbability){
                ArrayList<Specimen> crossedSpecimen=crossPair(population.getSpecimen(i),population.getSpecimen(i+1));
                newPopulation.addAll(crossedSpecimen);
            }else{
                newPopulation.add(population.getSpecimen(i));
                newPopulation.add(population.getSpecimen(i+1));
            }
        }
        return new Population(newPopulation);
    }


    private static ArrayList<Specimen> crossPair(Specimen specimen, Specimen specimen1) {

        int pairLength=specimen.getSpecimenLength();

        int[] children1 = new int[pairLength];
        int[] children2 = new int[pairLength];

        int firstIntersectionPoint = Utils.getRandomNumberExclusive(0,pairLength);
        int secondIntersectionPoint = Utils.getRandomNumberExclusive(firstIntersectionPoint,pairLength);
//        System.out.println("Punkty przeciecia " + firstIntersectionPoint +" " +secondIntersectionPoint);

        for (int i = firstIntersectionPoint; i <= secondIntersectionPoint; i++)
        {
            children1[i] = specimen.getSpecimenBody()[i];
            children2[i] = specimen1.getSpecimenBody()[i];
        }

        // wymiana genów (gen = wszystko poza środkowym przedziałem)
        // true = wymiana od 0 do początku przedziału
        // false = wymiana od końca przedziału do końca wiersza

        children1 = wymienGeny(specimen.getSpecimenBody(), children1, firstIntersectionPoint, secondIntersectionPoint, true);
        children1 = wymienGeny(specimen.getSpecimenBody(), children1, firstIntersectionPoint, secondIntersectionPoint, false);
        children2 = wymienGeny(specimen1.getSpecimenBody(), children2, firstIntersectionPoint, secondIntersectionPoint, true);
        children2 = wymienGeny(specimen1.getSpecimenBody(), children2, firstIntersectionPoint, secondIntersectionPoint, false);

        Specimen newSpecimen1= new Specimen();
        Specimen newSpecimen2 = new Specimen();

        newSpecimen1.setSpecimenBody(children1);
        newSpecimen2.setSpecimenBody(children2);
        return new ArrayList<>(){{add(newSpecimen1);add(newSpecimen2);}};
    }

    //TODO: Refactor->Exclude methods
    private static int[] wymienGeny(int[] rodzic, int[] potomek, int ppp, int dpp, boolean fromBegining)
    {
        int _od = 0;
        int _do = ppp;

        if (!fromBegining)
        {
            _od = dpp + 1;
            _do = potomek.length;
        }

        for (int i = _od; i < _do; i++)
        {
            int gen = rodzic[i];
            while (Utils.arrayContains(potomek,gen)) //TODO: Instead of while we should use if?
            {
                gen = rodzic[Arrays.asList(potomek).indexOf(gen)];
            }
            potomek[i] = gen;
        }
        return potomek;
    }
}
