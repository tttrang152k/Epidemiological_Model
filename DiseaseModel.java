package epidemic;

import java.io.IOException;
import java.util.List;

public class DiseaseModel {

    private DataLogger dataLogger;
    private int stepCount;

    public DiseaseModel(int populationSize, double infectionRate, double recoveryRate) throws IOException {
        // Initialize DataLogger with parameters for dynamic file naming
        dataLogger = new DataLogger(populationSize, infectionRate, recoveryRate);
        stepCount = 0;
    }

    // Method to log data for each step
    public void step(List<Person> population) {
        int susceptible = 0, infected = 0, recovered = 0;

        // Iterate through all the people and apply the "step" method
        for (Person person : population) {
            person.step(population);  // Update their state

            // Count the number of susceptible, infected, and recovered
            switch (person.getState()) {
                case 0 -> susceptible++;
                case 1 -> infected++;
                case 2 -> recovered++;
            }
        }

        // Log data after each step
        try {
            dataLogger.logData(stepCount, susceptible, infected, recovered);
        } catch (IOException e) {
            System.err.println("Error logging data: " + e.getMessage());
            e.printStackTrace();
        }

        stepCount++;
    }

    // Method to finish and close the logger
    public void finish() throws IOException {
        dataLogger.close();
    }
}
