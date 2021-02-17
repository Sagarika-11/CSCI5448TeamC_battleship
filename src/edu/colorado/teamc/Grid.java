package edu.colorado.teamc;

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

    public String getTileType(Coordinate c){
        switch(grid[c.getRow()][c.getCol()]){
            case EMPTY:
                return "EMPTY";
        }
        return null;
    }

    public boolean addShip(Ship ship, Coordinate coordinate){

        // TODO: implement this function (John)
        // search grid and make sure it's possible to add the ship

        // if not possible return false

        // if the ship is added, update the Tile enums and return true, and then call
        // ship.updateCoordinates(Coordinate coordinate);

        return false;

    }
}
