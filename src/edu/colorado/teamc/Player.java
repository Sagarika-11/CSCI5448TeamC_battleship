package edu.colorado.teamc;

import java.util.Vector;

public class Player {

    private String name;
    public Grid playerGrid = new Grid();
    //public Vector<Ship> playerShips = new Vector<Ship>(3);
    private int sonarPulsesLeft = 2;
    private Vector<Weapon> arsenal;
    private boolean sunkOneShip = false;

    public Player(String name) {
        this.name = name;
        this.arsenal = new Vector<Weapon>(3);
        this.arsenal.add(new Bomb());
        this.arsenal.add(new SpaceLaser());
        this.arsenal.add(new Sonar());
    }

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    // We can comment this back in if we need it. Otherwise, I'm too lazy to write a test for it and we don't use it
//    public Vector<Ship> getPlayerShips() {
//        return playerShips;
//    }

    public boolean getSunkOneShip() { return sunkOneShip; }

    // return message depending on if captain's quarters were hit or not
    public String hitPiece(Coordinate c, Weapon w) {
        String gridMsg = "";
        if (w instanceof Bomb) {
            gridMsg = playerGrid.attemptHit(c);
        }
        else if (w instanceof SpaceLaser) {
            gridMsg = playerGrid.attemptHit(c);
            Coordinate underwaterCoord = new Coordinate(c.getRow(), c.getCol(), 1);
            gridMsg += playerGrid.attemptHit(underwaterCoord);
        }
        else if (w instanceof Sonar) {
            gridMsg = playerGrid.printGrid(c);
            ((Sonar) w).decrementSonarPulses();
        }
        else {
            // error
        }

        return gridMsg;
    }

    public boolean addShipToGrid(Ship ship, Vector<Coordinate> coordinates, char orientation){
        boolean success = playerGrid.addShip(ship, coordinates, orientation);
        // SHOULDN'T HAVE TO DO THIS AFTER REFACTORING
//        if(success){
//            playerShips.add(ship);
//        }
        return success;
    }

    public Vector<Weapon> getAvailableWeapons() {
        Vector<Weapon> weapons = new Vector<Weapon>();
        for (int i = 0; i < arsenal.size(); i++) {
            Weapon w = arsenal.get(i);
            if (w.getAvailability()) {
                weapons.add(w);
            }
        }
        return weapons;
    }

    public void checkSunk() {
        Vector<Ship> playerShips = playerGrid.getPlayerShips();

        // check if any ships are sunk
        for (int i = 0; i < playerShips.size(); i++) {
            if (playerShips.get(i).isSunk()) {
                sunkOneShip = true;
                break;
            }
            else {

            }
        }
    }

    public void activate() {
        for (int i = 0; i < arsenal.size(); i++) {
            Weapon w = arsenal.get(i);
            if (w instanceof Bomb) {
                w.setAvailability(false);
            }
            else if (w instanceof SpaceLaser) {
                w.setAvailability(true);
            }
            else if (w instanceof Sonar) {
                w.setAvailability(true);
            }
        }
    }

    public boolean launchLifeboat() {
        Vector<Ship> playerShips = playerGrid.getPlayerShips();

        // check if any ships are sunk
        for (int i = 0; i < playerShips.size(); i++) {
            Ship ship = playerShips.get(i);
            if (ship.getName() == "Battleship" && ship.isSunk()) { // if battleship is sunk return true
                return true;
            }
        }
        return false;
    }
}
