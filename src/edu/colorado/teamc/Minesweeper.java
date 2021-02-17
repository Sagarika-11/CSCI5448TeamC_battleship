package edu.colorado.teamc;

public class Minesweeper extends Ship{
    private Coordinate[] pieces = new Coordinate[2]; // length 2

    public Minesweeper(Coordinate[] coordinates){
        super("Minesweeper", 2);
    }

    public void updateCoordinates(Coordinate[] coordinates){
        for(int i = 0; i < 2; i++){
            pieces[i] = coordinates[i]; // 1 == not hit
        }
    }

    public void hitPiece(int x, int y){

    }
}
