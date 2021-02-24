package edu.colorado.teamc;

import java.util.Vector;

public class Player {

    public Grid playerGrid = new Grid();
    public Ship[] playerShips = new Ship[]{new Ship("minesweeper"),
            new Ship("destroyer"),
            new Ship("battleship")};

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }

    public boolean setPlayerGrid(Grid playerGrid, Vector<Coordinate>[] shipCoordinates, char[] shipOrientation) {
        this.playerGrid = playerGrid;

        for(int i=0; i<3; i++) {
            boolean temp_bool = playerGrid.addShip(playerShips[i], shipCoordinates[i], shipOrientation[i]);
            if (temp_bool == true) {
                continue;
            }
            else {
                return false;
                }
        }
        return true;
    }

    }
