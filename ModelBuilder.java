package epidemic;

import java.util.ArrayList;
import java.util.List;

public class ModelBuilder {

    private int populationSize;
    private double infectionRate;
    private double recoveryRate;

    public ModelBuilder(int populationSize, double infectionRate, double recoveryRate) {
        this.populationSize = populationSize;
        this.infectionRate = infectionRate;
        this.recoveryRate = recoveryRate;
    }

    public List<Person> build() {
        List<Person> population = new ArrayList<>();

        // Create agents, all susceptible initially
        for (int i = 0; i < populationSize - 1; i++) { // All but one agent are susceptible
            Person person = new Person(infectionRate, recoveryRate);
            population.add(person);
        }

        // Create one initial infected person (this ensures only one is infected)
        Person initialInfected = new Person(infectionRate, recoveryRate);
        initialInfected.setState(1); // Set state to Infected
        population.add(initialInfected);

        return population;
    }
}
