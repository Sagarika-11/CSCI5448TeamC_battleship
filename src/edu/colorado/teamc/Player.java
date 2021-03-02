package edu.colorado.teamc;

import java.util.Vector;

public class Player {

    public Grid playerGrid = new Grid();
    public Vector<Ship> playerShips = new Vector<Ship>(3);

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    // We can comment this back in if we need it. Otherwise, I'm too lazy to write a test for it and we don't use it
//    public Vector<Ship> getPlayerShips() {
//        return playerShips;
//    }

    // return message depending on if captain's quarters were hit or not
    public String hitPiece(Coordinate c){
        String shipMsg;
        String gridMsg = playerGrid.attemptHit(c);
        if (gridMsg.equals("Hit")) {
            // Need to update the piece on the player's ship.
            for (Ship ship : playerShips) {
                shipMsg = ship.hitPiece(c);
                if (shipMsg.equals("You hit the captain's quarters! Ship sunk.")) {
                    // need to make every tile an X.. this is a little messy
                    for(Coordinate piece : ship.getPieces()){
                        playerGrid.attemptHit(piece);
                    }
                }
                return shipMsg;
            }
        }
        return gridMsg;
    }

    public boolean addShipToGrid(Ship ship, Vector<Coordinate> coordinates, char orientation){
        boolean success = playerGrid.addShip(ship, coordinates, orientation);
        if(success){
            playerShips.add(ship);
        }
        return success;
    }

//    public boolean setPlayerGrid(Grid playerGrid, Vector<Coordinate>[] shipCoordinates, char[] shipOrientation) {
//        this.playerGrid = playerGrid;
//
//        for(int i=0; i<3; i++) {
//            boolean temp_bool = playerGrid.addShip(playerShips[i], shipCoordinates[i], shipOrientation[i]);
//            if (temp_bool == true) {
//                continue;
//            }
//            else {
//                return false;
//            }
//        }
//        return true;
//    }

    }
