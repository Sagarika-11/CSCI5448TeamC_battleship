package edu.colorado.teamc;

import java.util.Vector;

enum Tile {
    HIT,
    EMPTY,
    OCCUPIED
}

public class Grid {
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

    public boolean addShip(Ship ship, Vector<Coordinate> coordinates, Character orientation){
        Coordinate firstCoord = coordinates.get(0);
        Coordinate previousCoord = firstCoord;
        //Check if Coordinates is valid (ONLY WORKS IF VECTOR IS LEFT->RIGHT, OR UP->DOWN)
        for(Coordinate coord : coordinates) {
            Tile tileType = getTileType(coord);
            //Check if any coordinate goes off the board
            if(coord.getRow() < 0 || coord.getCol() < 0 || coord.getRow() > 10 || coord.getCol() > 10) {
                return false;
            }
            //Check if the tile is occupied
            if(tileType != Tile.EMPTY) {
                return false;
            }
            //Check if they want to place horizontally, that the rows are the same, that previous coord is one less
            if(orientation.equals('h')) {
                if((coord.getRow() != firstCoord.getRow()) || (coord.getCol()-1 != previousCoord.getCol()))
                    return false;
            }
            //Check if they want to place vertically, that the cols are the same, that previous coord is one less
            else if(orientation.equals('v')) {
                if((coord.getCol() != firstCoord.getCol()) || (coord.getRow()-1 != previousCoord.getRow()))
                    return false;
            }
            grid[coord.getRow()][coord.getCol()] = Tile.OCCUPIED;
            ship.updateCoordinates(coord);
            previousCoord = coord;
        }
        return true;
    }
}
