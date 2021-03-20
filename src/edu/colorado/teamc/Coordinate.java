package edu.colorado.teamc;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;
    private int depth;
    private boolean hit;
    private boolean captain;

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
        this.depth = 0;
        hit = false;
        captain = false;
    }
    public Coordinate(int row, int col, int depth){
        this.row = row;
        this.col = col;
        this.depth = depth;
        hit = false;
        captain = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDepth() { return depth;}

    public void setDepth(int depth) { this.depth = depth;}

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        Coordinate coordinate = (Coordinate)o;
        return Objects.equals(this.row, coordinate.getRow()) &&
                Objects.equals(this.col, coordinate.getCol());
    }

    @Override
    public String toString() {
        return String.format("x: " + this.row + ", y: " + this.col);
    }

    @Override
    public int compareTo(Coordinate anotherCoordinate) {
        if(this.row == anotherCoordinate.getRow()){
            if(this.col == anotherCoordinate.getCol()){ // (0, 0) == (0, 0)
                return 0;
            }
            else if(this.col < anotherCoordinate.getCol()){ // (-1, 0) < (0, 0)
                return -1;
            }
            else{ // (1, 0) > (0, 0)
                return 1;
            }
        }
        else if(this.row < anotherCoordinate.getRow()){
            if(this.col == anotherCoordinate.getCol()){ // (0, -1) < (0, 0)
                return -1;
            }
            else if(this.col < anotherCoordinate.getCol()){ // (-1, -1) < (0, 0)
                return -1;
            }
            else{ // (-1, 0) ??? (0, -1) This should never happen in our game as Game tests that lines are either horizontal or vertical
                return 0;
            }
        }
        else if(this.row > anotherCoordinate.getRow()){
            return 1; // (1, 0) > (0, 0) and (1, 0) > (0, 100) (latter case doesn't matter for our game)
        }
        else{
            return 0; // Never executed?
        }
    }
}
