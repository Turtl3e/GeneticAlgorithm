package selections;

import algorithm.Population;
import algorithm.Specimen;

import java.util.ArrayList;

public interface Selection {
    Population preparePopulation(Population population);
}
