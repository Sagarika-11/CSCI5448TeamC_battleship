package edu.colorado.teamc;

import java.util.Collections;

public class Submarine extends Ship{
    private int captainsHitCounter;
    private boolean submerged;

    public Submarine(){
        super("Submarine", 5, false);
        this.captainsHitCounter = 0;
    }
    public void addCaptainsQuarters(){
        Collections.sort(pieces);
        pieces.get(1).setCaptain(true);
    }

    public void setSubmerged(boolean submerge){
        this.submerged = submerge;
    }

    public boolean getSubmerged(){
        return this.submerged;
    }

    public String hitPiece(Coordinate c) {return "yuh";}
}
