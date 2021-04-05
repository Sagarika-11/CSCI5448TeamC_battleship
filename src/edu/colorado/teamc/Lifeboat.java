package edu.colorado.teamc;

import java.util.Collections;

public class Lifeboat extends Ship{

    public Lifeboat(){
        super("Lifeboat", 1, false);
    }

    public void addCaptainsQuarters(){
        Collections.sort(pieces);
        pieces.get(0).setCaptain(true);
    }

    // returns message with outcome of attempt to hit piece
    public String hitPiece(Coordinate coordToHit){
        for(Coordinate c : pieces){
            if(c.equals(coordToHit)){
                c.setHit(true);
                return "Hit!";
            }
        }
        return "Miss";
    }
}
