package edu.colorado.teamc;

import java.util.Vector;

/**
 * Player class that holds a player's grid and arsenal (available weapons).
 */
public class Player {

    private String name;
    public Grid playerGrid = new Grid();
    private Vector<Weapon> arsenal;
    private boolean sunkOneShip = false;

    public Player(String name) {
        this.name = name;
        this.arsenal = new Vector<>(3);
        this.arsenal.add(new Bomb());
        this.arsenal.add(new SpaceLaser());
        this.arsenal.add(new Sonar());
    }

    public String getPlayerName() {
        return name;
    }

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    public void setPlayerGrid(Grid grid) { playerGrid = grid; }

    public boolean getSunkOneShip() { return sunkOneShip; }

    /**
     * Returns message specified in attemptHit, or depending on if captain's quarters were hit or not.
     * If a player uses sonar, it will return a grid at the specified coordinate showing the ships there.
     *
     * @param c coordinate
     * @param w weapon
     * @return message depending on if captain's quarters were hit or not
     */
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

        return gridMsg;
    }

    /**
     * Attempts to add a ship to the player's grid.
     *
     * @param ship input ship
     * @param coordinates vector of coordinate
     * @param orientation horintal or vertical
     * @return boolean for success/failure
     */
    public boolean addShipToGrid(Ship ship, Vector<Coordinate> coordinates, char orientation){
        boolean success = playerGrid.addShip(ship, coordinates, orientation);

        return success;
    }

    /**
     * @return the player's available weapons
     */
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

    /**
     * Checks if any of the player's ships have been sunk.
     */
    public void checkSunk() {
        Vector<Ship> playerShips = playerGrid.getPlayerShips();

        for (int i = 0; i < playerShips.size(); i++) {
            if (playerShips.get(i).isSunk()) {
                sunkOneShip = true;
                break;
            }
        }
    }

    /**
     * Checks if all of the player's ships have been sunk. This is used to determine a winner/loser.
     *
     * @return boolean
     */
    public boolean checkAllSunk() {
        Vector<Ship> playerShips = playerGrid.getPlayerShips();

        // check if a;; ships are sunk
        for (int i = 0; i < playerShips.size(); i++) {
            if (!playerShips.get(i).isSunk()) {
                return false;
            }
        }
        return true;
    }

    /**
     * When a player sinks a ship, we need to deactivate Bomb and activate SpaceLaser and Sonar.
     */
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

    /**
     * Checks if Battleship has been sunk. If it is, we know we need to launch a lifeboat (return true).
     *
     * @return boolean for success/failure
     */
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
