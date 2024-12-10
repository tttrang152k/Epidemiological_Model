package epidemic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataLogger {

    private BufferedWriter writer;

    // Modified constructor to accept parameters for dynamic file naming
    public DataLogger(int populationSize, double infectionRate, double recoveryRate) throws IOException {
        // Construct the filename using parameters
        String fileName = String.format("simulation_log_%d_%.2f_%.2f.csv",
                populationSize,
                infectionRate,
                recoveryRate);
        
        writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("Step,Susceptible,Infected,Recovered\n");
    }

    // Method to log data for each step
    public void logData(int step, int susceptible, int infected, int recovered) throws IOException {
        writer.write(step + "," + susceptible + "," + infected + "," + recovered + "\n");
    }

    // Close the writer after finishing the simulation
    public void close() throws IOException {
        writer.close();
    }
}
