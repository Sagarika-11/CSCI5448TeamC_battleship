package edu.colorado.teamc;

import java.util.Vector;

public class Ship {
    private String name;
    private int length;
    private Vector<Coordinate> pieces;
    public Ship(String shipType) {
        switch (shipType) {
            case "minesweeper":
                name = "Minesweeper";
                length = 2;
                break;
            case "destroyer":
                name = "Destroyer";
                length = 3;
                break;
            case "battleship":
                name = "Battleship";
                length = 4;
                break;
            default:
                // TODO: error: invalid length
                break;
        }
        pieces = new Vector<Coordinate>(length);

    }

    public String getName(){ return name; }

    public int getLength(){ return length; }

    //This should not be called anywhere outside of Grid
    public void updateCoordinates(Coordinate coordinateToAdd){
        System.out.println(coordinateToAdd.getRow());
        System.out.println(coordinateToAdd.getCol());
        pieces.add(coordinateToAdd);
    }

    public void hitPiece(Coordinate coord){
        coord.setHit(true);
    }

    public boolean isSunk() {
        for(Coordinate coordToCheck : pieces) {
            if(!coordToCheck.getHit()) {
                return false;
            }
        }
        return true;
    }
}