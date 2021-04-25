package edu.colorado.teamc;
import java.util.Vector;

/**
 * Abstract class for Minesweeper, Destroyer, Battleship, Submarine, and Lifeboat
 */
public abstract class Ship {
    private String name;
    private int length;
    private boolean isArmored;
    protected Vector<Coordinate> pieces;
    public Ship(String name, int length, boolean isArmored){
        this.name = name;
        this.length = length;
        this.isArmored = isArmored;
        pieces = new Vector<>(length);
    }

    public String getName(){ return name; }

    public int getLength(){ return length; }

    // This should not be called anywhere outside of Grid
    public void updateCoordinates(Coordinate coordinateToAdd){
        pieces.add(coordinateToAdd);
    }

    public abstract void addCaptainsQuarters();

    /**
     * Abstract class for hitting a piece (each ship might have slightly different logic, like captain's quarters)
     *
     * @param coordToHit coordinate
     * @return message with outcome of attempt to hit piece
     */
    public abstract String hitPiece(Coordinate coordToHit);

    /**
     * Set all coordinates to "HIT"
     */
    public void sinkShip() {
        for(Coordinate c : pieces){
            c.setHit(true);
        }
    }

    /**
     *
     * @return check if ship has sunk
     */
    public boolean isSunk() {
        for(Coordinate coordToCheck : pieces) {
            if(!coordToCheck.isHit()) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param c coordinate
     * @return if piece equals coordinate
     */
    public boolean hasCoordinate(Coordinate c){
        for (Coordinate piece : pieces){
            if(piece.equals(c)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param c coordinate
     * @return if coordinate is part of the captains quarter's
     */
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

    /**
     * Updates the pieces of the ship with a vector of coordinates.
     *
     * @param new_pieces vector of coordinates
     */
    public void updatePieces(Vector<Coordinate> new_pieces){
        for(int i=0; i<length; i++){
            Coordinate temp_c = new_pieces.get(i);
            this.pieces.set(i , temp_c);
        }
    }
}