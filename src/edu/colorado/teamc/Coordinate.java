package edu.colorado.teamc;

public class Coordinate {
    private int row;
    private int col;
    private boolean hit;

    public Coordinate(int x, int y){
        row = x;
        col = y;
        hit = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean getHit() { return hit; }

    public void setHit(boolean hit) { this.hit = hit; }

}
