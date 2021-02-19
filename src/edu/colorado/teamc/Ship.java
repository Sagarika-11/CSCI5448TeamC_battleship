package edu.colorado.teamc;

public class Ship {
    private String name;
    private int length;
    private Coordinate[] pieces;

    public Ship(Coordinate[] coordinates) {
        if (coordinates.length == 2) {
            name = "Minesweeper";
        }
        else if (coordinates.length == 3) {
            name = "Destroyer";
        }
        else if (coordinates.length == 4) {
            name = "Battleship";
        }
        else {
            // TODO: error: invalid length
        }
        length = coordinates.length;
        pieces = new Coordinate[length];

        for (int i = 0; i < length; i++){
            pieces[i] = coordinates[i];
        }
    }

    public String getName(){ return name; }

    public int getLength(){ return length; }

    public void updateCoordinates(Coordinate[] coordinates){
        for (int i = 0; i < length; i++){
            pieces[i] = coordinates[i];
        }
    }

    public void hitPiece(int x, int y){
        for (int i = 0; i < length; i++) {
            if (pieces[i].getRow() == x && pieces[i].getCol() == y) {
                pieces[i].setHit(true);
            }
        }
    }

    public boolean isSunk() {
        for(int i = 0; i < length; i++) {
            if (!pieces[i].getHit()) {
                return false;
            }
        }
        return true;
    }
}