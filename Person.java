package epidemic;

import java.util.List;

import repast.simphony.random.RandomHelper;

public class Person {

    private int state; // 0 - Susceptible, 1 - Infected, 2 - Recovered
    private double infectionRate;
    private double recoveryRate;

    public Person(double infectionRate, double recoveryRate) {
        this.state = 0; // Default state is Susceptible
        this.infectionRate = infectionRate;
        this.recoveryRate = recoveryRate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    // Step through the individual's behavior (infection or recovery)
    public void step(List<Person> population) {
        if (state == 1) { // Infected
            infectOthers(population);  // Attempt to infect others
            recoverIfNeeded();  // Check if the person should recover
        }
    }

    // Method to infect others in the population
    private void infectOthers(List<Person> population) {
        for (Person other : population) {
            if (other.state == 0 && RandomHelper.nextDoubleFromTo(0, 1) < infectionRate) {
                other.state = 1; // Infect susceptible people
            }
        }
    }

    // Method to recover the person based on the recovery rate
    private void recoverIfNeeded() {
        if (RandomHelper.nextDoubleFromTo(0, 1) < recoveryRate) {
            state = 2; // Person recovers
        }
    }
}
