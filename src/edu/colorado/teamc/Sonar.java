package edu.colorado.teamc;

public class Sonar extends Weapon {
    private int numPulses = 2;

    public Sonar() { super("Sonar", false); }

    public int getSonarPulses(){
        return numPulses;
    }

    public void decrementSonarPulses(){
        numPulses -= 1;
        if (numPulses == 0) {
            super.setAvailability(false);
        }
    }
}
