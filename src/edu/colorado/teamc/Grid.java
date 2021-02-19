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

        // TODO: implement this function (John)
        // search grid and make sure it's possible to add the ship

        // TODO: Check if vector coordiantes are horizontal or vertical

        //For now, assume the vector makes a horizontal or vertical line
        for(Coordinate coord : coordinates) {
            Tile tileType = getTileType(coord);
            //Check if any coordinate goes off the board
            if(coord.getRow() < 0 || coord.getCol() < 0 || coord.getRow() > 10 || coord.getCol() > 10) {
                return false;
            }
            if(tileType != Tile.EMPTY) {
                return false;
            }
            grid[coord.getRow()][coord.getCol()] = Tile.OCCUPIED;
            ship.updateCoordinates(coord);
        }
        // if not possible return false

        // if the ship is added, update the Tile enums and return true, and then call
        // ship.updateCoordinates(Coordinate coordinate);

        return true;

    }
}
