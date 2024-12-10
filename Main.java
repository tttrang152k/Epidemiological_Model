package epidemic;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Define different simulation parameters
            int populationSize = 700; // Population size
            double infectionRate = 0.02; // Infection rate (β)
            double recoveryRate = 0.05; // Recovery rate (γ)
            int steps = 50; // Number of simulation steps (ticks)

            // Run the simulation with the current parameters
            runSimulation(populationSize, infectionRate, recoveryRate, steps);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runSimulation(int populationSize, double infectionRate, double recoveryRate, int steps) throws IOException {
        // Initialize DiseaseModel for logging
        DiseaseModel diseaseModel = new DiseaseModel(populationSize, infectionRate, recoveryRate);

        // Initialize ModelBuilder and create the population
        ModelBuilder modelBuilder = new ModelBuilder(populationSize, infectionRate, recoveryRate);
        List<Person> population = modelBuilder.build(); // Get the population list

        // Simulate the steps
        for (int step = 0; step < steps; step++) {
            // Execute a step for each person in the population
            diseaseModel.step(population);
        }

        // Finish and close the logger
        diseaseModel.finish();

        System.out.println("Simulation complete for Population Size " + populationSize + ", Infection Rate " + infectionRate + ", Recovery Rate " + recoveryRate);
    }
}
