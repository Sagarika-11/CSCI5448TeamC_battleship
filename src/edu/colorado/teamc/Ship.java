package edu.colorado.teamc;
import java.util.Collections;
import java.util.Vector;

public abstract class Ship {
    private String name;
    private int length;
    private boolean isArmored;
    protected Vector<Coordinate> pieces;
    public Ship(String name, int length, boolean isArmored){
        this.name = name;
        this.length = length;
        this.isArmored = isArmored;
        pieces = new Vector<Coordinate>(length);
    }

    public String getName(){ return name; }

    public int getLength(){ return length; }

    //This should not be called anywhere outside of Grid
    public void updateCoordinates(Coordinate coordinateToAdd){
        pieces.add(coordinateToAdd);
    }

    public abstract void addCaptainsQuarters();

    // returns message with outcome of attempt to hit piece
    public abstract String hitPiece(Coordinate coordToHit);

    public void sinkShip() {
        for(Coordinate c : pieces){
            c.setHit(true);
        }
    }

    public boolean isSunk() {
        for(Coordinate coordToCheck : pieces) {
            if(!coordToCheck.isHit()) {
                return false;
            }
        }
        return true;
    }

    // write test and erase this comment...
    public boolean hasCoordinate(Coordinate c){
        for (Coordinate piece : pieces){
            if(piece.equals(c)){
                return true;
            }
        }
        return false;
    }

    public boolean isCaptainsQuarters(Coordinate c){
        for (Coordinate piece : pieces){
            if(piece.equals(c) && piece.isCaptain()){
                return true;
            }
        }
        return false;
    }

    public Vector<Coordinate> getPieces(){
        return pieces;
    }

    public void updatePieces(Vector<Coordinate> new_pieces){
        for(int i=0; i<length; i++){
            Coordinate temp_c = new_pieces.get(i);
            this.pieces.set(i , temp_c);
        }
    }
}