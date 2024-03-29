package edu.colorado.teamc;

/**
 * Weapon is an abstract class, where Weapon can be a Bomb, SpaceLaser, or Sonar.
 */
public abstract class Weapon {
    private String name;
    private boolean available;

    public Weapon(String name, boolean available) {
        this.name = name;
        this.available = available;
    }

    public String getName() { return name; }

    public boolean getAvailability() { return available; }

    public void setAvailability(boolean b) { available = b; }
}
