package edu.colorado.teamc;
import java.util.Collections;
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
        pieces.add(coordinateToAdd);
    }

    //This should not be called anywhere outside of Grid. Call after for-loop in addShip
    public void addCaptainsQuarters(){
        Collections.sort(pieces);
        if(this.name.equals("Minesweeper")){
            pieces.get(0).setCaptain(true);
        }
        else if(this.name.equals("Destroyer")){
            pieces.get(1).setCaptain(true);
        }
        else{
            pieces.get(2).setCaptain(true);
        }
    }

    // returns message with outcome of attempt to hit piece
    public String hitPiece(Coordinate coordToHit){
        for(Coordinate c : pieces){
            if(c.equals(coordToHit) && !c.isCaptain()){
                c.setHit(true);
                return "Hit!";
            }
            else if(c.equals(coordToHit) && c.isCaptain()){
                this.sinkShip();
                return "You hit the captain's quarters! Ship sunk.";
            }
        }
        return "Miss";
    }

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

    public Vector<Coordinate> getPieces(){
        return pieces;
    }
}