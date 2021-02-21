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

    public void updateTileType(Coordinate c, Tile tiletype){
        grid[c.getRow()][c.getCol()] = tiletype;
    }

    public String playerTurn(Grid grid, Coordinate c) {
        Tile grid_tile = grid.getTileType(c);
        switch (grid_tile) {
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
        for(Coordinate coord : coordinates) {

            //Check if any coordinate goes off the board
            if(coord.getRow() < 0 || coord.getCol() < 0 || coord.getRow() > 10 || coord.getCol() > 10) {
                return false;
            } else {
                tileType = getTileType(coord);
            }
            //Check if the tile is occupied
            if(tileType != Tile.EMPTY) {
                return false;
            }
            //Check if they want to place horizontally, that the rows are the same, that previous coord is one less
            if(orientation.equals('h')) {
                if((coord.getRow() != firstCoord.getRow())) {
                    return false;
                }
            }
            //Check if they want to place vertically, that the cols are the same, that previous coord is one less
            else if(orientation.equals('v')) {
                if((coord.getCol() != firstCoord.getCol())) {
                    return false;
                }
            }
            grid[coord.getRow()][coord.getCol()] = Tile.OCCUPIED;
            ship.updateCoordinates(coord);
        }
        return true;
    }
}
