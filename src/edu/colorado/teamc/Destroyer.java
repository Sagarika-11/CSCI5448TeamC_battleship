package edu.colorado.teamc;

import java.util.Collections;

/**
 * Destroyer is an instance of Ship that has a length of 3
 */
public class Destroyer extends Ship {

    private int captainsHitCounter;

    public Destroyer(){
        super("Destroyer", 3, true);
        this.captainsHitCounter = 0;
    }
    public void addCaptainsQuarters(){
        Collections.sort(pieces);
        pieces.get(1).setCaptain(true);
    }

    // returns message with outcome of attempt to hit piece
    public String hitPiece(Coordinate coordToHit){
        for(Coordinate c : pieces){
            if(c.equals(coordToHit) && !c.isCaptain()){
                c.setHit(true);
                return "Hit!";
            }
            else if(c.equals(coordToHit) && c.isCaptain() && this.captainsHitCounter == 1){
                this.captainsHitCounter++;
                this.sinkShip();
                return "You hit the captain's quarters! Ship Sunk!";
            } else if(c.equals(coordToHit) && c.isCaptain() && this.captainsHitCounter < 1) {
                this.captainsHitCounter++;
                return "You hit the captain's quarters! Ship critically damaged!";
            }
        }
        return "Miss";
    }
}
