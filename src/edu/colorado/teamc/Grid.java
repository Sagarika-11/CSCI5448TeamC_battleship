package edu.colorado.teamc;

import java.util.Vector;

public class Grid {
    public enum Tile {
        HIT,
        EMPTY,
        OCCUPIED
    }
    private Tile[][][] grid = new Tile[10][10][2];
    private Vector<Ship> playerShips = new Vector<Ship>(3);

    public Grid(){
        // Initialize empty grid
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                grid[i][j][0] = Tile.EMPTY;
                grid[i][j][1] = Tile.EMPTY;
            }
        }
    }

    public Tile getTileType(Coordinate c){
        return grid[c.getRow()][c.getCol()][c.getDepth()];
    }

    public void updateTileType(Coordinate c, Tile tileType){
        grid[c.getRow()][c.getCol()][c.getDepth()] = tileType;
    }

    public Vector<Ship> getPlayerShips() { return playerShips; }

    public String attemptHit(Coordinate c) {
        Tile gridTile = getTileType(c);
        String shipMsg = "";
        switch (gridTile) {
            case HIT:
                // Check if it's the captain's quarters
                for(Ship ship : playerShips){
                    if(ship.hasCoordinate(c) && ship.isCaptainsQuarters(c)){
                        // Hit ship
                        shipMsg = ship.hitPiece(c);
                        if (shipMsg.equals("You hit the captain's quarters! Ship Sunk!")) {
                            // Update grid
                            for(Coordinate tile : ship.getPieces()){
                                updateTileType(tile, Tile.HIT);
                            }
                            return shipMsg;
                        }
                    }
                }
                return "Hit!";
            case EMPTY:
                return "Miss";
            case OCCUPIED:
                // Find ship to hit
                for(Ship ship : playerShips){
                    if(ship.hasCoordinate(c)){
                        // Hit ship
                        shipMsg = ship.hitPiece(c);
                        if (shipMsg.equals("You hit the captain's quarters! Ship Sunk!")) {
                            // Update grid
                            for(Coordinate tile : ship.getPieces()){
                                updateTileType(tile, Tile.HIT);
                            }
                        }
                    }
                }
                updateTileType(c, Tile.HIT);
                return shipMsg;
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
            if(!(ship instanceof Submarine)) {
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
            }

            grid[c.getRow()][c.getCol()][c.getDepth()] = Tile.OCCUPIED;
            ship.updateCoordinates(c);
        }
        ship.addCaptainsQuarters();
        playerShips.add(ship);
        return true;
    }

    // change to void so we can just print from this function for when we implement gui/user i/o
    // pass in boolean "hidden" to indicate whether "OCCUPIED" tiles should be hidden or not
    public String printGrid(boolean hidden){
        String gridString = "";
        String line = "";

        for(int k = 0; k < 2; k++) {
            gridString = gridString + "Depth: "+Integer.toString(k)+"\n";
            for(int i = 0; i < 10; i++) {
                line = "";
                for (int j = 0; j < 10; j++) {
                    if (j == 0) {
                        line = line + Integer.toString(i) + " ";
                    }
                    if (grid[i][j][k] == Tile.EMPTY || (hidden && grid[i][j][k] == Tile.OCCUPIED)) {
                        line = line + "~";
                    } else if (!hidden && grid[i][j][k] == Tile.OCCUPIED) {
                        line = line + "O";
                    } else {
                        line = line + "X";
                    }
                    if (j < 9) {
                        line = line + " ";
                    }
                }
                if (i == 0) {
                    gridString = gridString + "  0 1 2 3 4 5 6 7 8 9" + "\n";
                }
                gridString = gridString + line + "\n";
            }
        }

        return gridString;
    }


    // overloaded sonar pulse function
    // does not need the "hidden" boolean as this will only be called to show the enemy grid
    public String printGrid(Coordinate center){
        String gridString = "";
        String line = "";
        boolean rowVisible = false;
        boolean colVisible = false;

        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < 10; i++){
                line = "";
                for(int j = 0; j < 10; j++){
//                colVisible = false;
//                rowVisible = false;


                    // Print coordinates
                    if(j == 0){
                        line = line + Integer.toString(i) + " ";
                    }

                    // Decide where to reveal map
                    if( (i >= center.getRow() - 1 && i <= center.getRow()) || (i <= center.getRow() + 1 && i >= center.getRow()) ){
                        rowVisible = true;
                    }
                    else{
                        rowVisible = false;
                    }
                    if( (j >= center.getCol() - 1 && j <= center.getCol()) || (j <= center.getCol() + 1 && j >= center.getCol()) ){
                        colVisible = true;
                    }
                    else {
                        colVisible = false;
                    }
                    if ( (j == center.getCol() + 2 && i == center.getRow()) || (j == center.getCol() - 2 && i == center.getRow())
                            || (j == center.getCol() && i == center.getRow() - 2 ) || (j == center.getCol() && i == center.getRow() + 2 )  ){
                        colVisible = true;
                        rowVisible = true;
                    }

                    boolean showTile = rowVisible && colVisible;

                    // Determine the tile to print

                    if(grid[i][j][k] == Tile.EMPTY && showTile){
                        line = line + "F"; // "greyed out box" placeholder
                    }
                    else if ((grid[i][j][k] == Tile.EMPTY && !showTile) || (!showTile && grid[i][j][k] == Tile.OCCUPIED)){
                        line = line + "~";
                    }
                    else if(showTile && grid[i][j][k] == Tile.OCCUPIED){
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
        }

        return gridString;
    }
}
