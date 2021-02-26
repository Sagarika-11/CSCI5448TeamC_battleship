package edu.colorado.teamc;

import java.util.Vector;

public class Grid {
    public enum Tile {
        HIT,
        EMPTY,
        OCCUPIED
    }
    private Tile[][] grid = new Tile[10][10];

    public Grid(){
        // Initialize empty grid
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                grid[i][j] = Tile.EMPTY;
            }
        }
    }

    public Tile getTileType(Coordinate c){
        return grid[c.getRow()][c.getCol()];
    }

    public void updateTileType(Coordinate c, Tile tileType){
        grid[c.getRow()][c.getCol()] = tileType;
    }

    public String attemptHit(Coordinate c) {
        Tile gridTile = getTileType(c);
        switch (gridTile) {
            case HIT:
                return "Already Hit";
            case EMPTY:
                return "Miss";
            case OCCUPIED:
                updateTileType(c, Tile.HIT);
                return "Hit";
        }
        return "Error!";
    }

    public boolean addShip(Ship ship, Vector<Coordinate> coordinates, Character orientation){
        Coordinate firstCoord = coordinates.get(0);
        Tile tileType;
        if(ship.getLength() != coordinates.size()) {
            return false;
        }
         //Check if Coordinates is valid (ONLY WORKS IF VECTOR IS LEFT->RIGHT, OR UP->DOWN)
        for(Coordinate c : coordinates) {

            //Check if any coordinate goes off the board
            if(c.getRow() < 0 || c.getCol() < 0 || c.getRow() > 10 || c.getCol() > 10) {
                return false;
            } else {
                tileType = getTileType(c);
            }
            //Check if the tile is occupied
            if(tileType != Tile.EMPTY) {
                return false;
            }
            //Check if they want to place horizontally, that the rows are the same, that previous coord is one less
            if(orientation.equals('h')) {
                if((c.getRow() != firstCoord.getRow())) {
                    return false;
                }
            }
            //Check if they want to place vertically, that the cols are the same, that previous coord is one less
            else if(orientation.equals('v')) {
                if((c.getCol() != firstCoord.getCol())) {
                    return false;
                }
            }
            grid[c.getRow()][c.getCol()] = Tile.OCCUPIED;
            ship.updateCoordinates(c);
        }
        ship.addCaptainsQuarters();
        return true;
    }

    // change to void so we can just print from this function for when we implement gui/user i/o
    // pass in boolean "hidden" to indicate whether "OCCUPIED" tiles should be hidden or not
    public String printGrid(boolean hidden){
        String gridString = "";
        String line = "";

        for(int i = 0; i < 10; i++){
            line = "";
            for(int j = 0; j < 10; j++){
                if(j == 0){
                    line = line + Integer.toString(i) + " ";
                }
                if(grid[i][j] == Tile.EMPTY || (hidden && grid[i][j] == Tile.OCCUPIED)){
                    line = line + "~";
                }
                else if(!hidden && grid[i][j] == Tile.OCCUPIED){
                    line = line + "O";
                }
                else{
                    line = line + "X";
                }
                if(j < 9){
                    line = line + " ";
                }
            }
            if(i == 0){
                gridString = gridString + "  0 1 2 3 4 5 6 7 8 9" + "\n";
            }
            gridString = gridString + line + "\n";
        }
        return gridString;
    }

}
