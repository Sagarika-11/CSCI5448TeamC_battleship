package edu.colorado.teamc;

import java.util.Collections;

/**
 * Minesweeper is an instance of Ship that has a length of 2
 */
public class Minesweeper extends Ship{

    public Minesweeper(){
        super("Minesweeper", 2, false);
    }

    public void addCaptainsQuarters(){
        Collections.sort(pieces);
        pieces.get(0).setCaptain(true);
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
                return "You hit the captain's quarters! Ship Sunk!";
            }
        }
        return "Miss";
    }

}
