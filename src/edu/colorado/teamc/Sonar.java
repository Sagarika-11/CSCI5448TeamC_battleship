package edu.colorado.teamc;

/**
 * Sonar is an instance of weapon that lets the player see a portion of the enemy's grid centered around a
 * coordinate they choose.
 */
public class Sonar extends Weapon {
    private int numPulses = 2;

    public Sonar() { super("Sonar", false); }

    /**
     * Each player is alloted two sonar pulses per game. This method decrements the number of pulses if the player
     * uses a sonar, and sets it to unavailable if they have used both.
     */
    public void decrementSonarPulses(){
        numPulses -= 1;
        if (numPulses == 0) {
            super.setAvailability(false);
        }
    }
}
