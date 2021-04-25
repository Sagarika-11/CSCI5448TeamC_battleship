package edu.colorado.teamc;

import java.util.Vector;

/**
 * Grid contains 10x10 tiles and stores all of the player's ships. Each player has their own separate grid.
 */
public class Grid {
    public enum Tile {
        HIT,
        MISS,
        EMPTY,
        OCCUPIED,
        MINE
    }
    private Tile[][][] grid = new Tile[10][10][2];
    private Vector<Ship> playerShips = new Vector<Ship>(3);

    /**
     * Initializes an empty grid
     */
    public Grid() {
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                grid[i][j][0] = Tile.EMPTY;
                grid[i][j][1] = Tile.EMPTY;
            }
        }
    }

    /**
     * Gets the tile type (Tile enum) on the grid
     *
     * @param c input coordinate
     * @return tile type
     */
    public Tile getTileType(Coordinate c){
        return grid[c.getRow()][c.getCol()][c.getDepth()];
    }

    public void updateTileType(Coordinate c, Tile tileType){
        grid[c.getRow()][c.getCol()][c.getDepth()] = tileType;
    }

    public Vector<Ship> getPlayerShips() { return playerShips; }

    /**
     * Checks the grid tile and updates the grid accordingly by looping through each of the ships
     * and checking if the input coordinate is in any of the ships. For example, if a tile is occupied,
     * the tile will be update to "HIT" with the grid updating accordingly.
     *
     * @param c input coordinate
     * @return message in string-format (hit, miss, etc.)
     */
    public String attemptHit(Coordinate c) {
        Tile gridTile = getTileType(c);
        String shipMsg = "";
        switch (gridTile) {
            case HIT:
                // Check if it's the captain's quarters
                for(Ship ship : playerShips){
                    if(ship.hasCoordinate(c)){
                        if(ship.isCaptainsQuarters(c) && !ship.isSunk()) {
                            // Hit ship
                            shipMsg = ship.hitPiece(c);
                            if (shipMsg.equals("You hit the captain's quarters! Ship Sunk!")) {
                                // Update grid
                                for (Coordinate tile : ship.getPieces()) {
                                    updateTileType(tile, Tile.HIT);
                                }
                                return shipMsg;
                            }
                        }
                        else {
                            return "You already hit this coordinate! Turn wasted...";
                        }
                    }
                }
                return "Hit!";
            case EMPTY:
                updateTileType(c, Tile.MISS);
                return "Miss";
            case MISS:
                return "You already missed this coordinate! Turn wasted...";
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

    /**
     * Attempts to place a ship with the specified input coordinates. The function will return false if the
     * coordinates are not valid (not in a line, off the grid, not the right number for the ship, etc.).
     * If the coordinates are valid, the ship is placed on the grid (with a captain's quarters if the ship
     * calls for one).
     *
     * @param ship input ship
     * @param coordinates vector of coordinates that the user input
     * @param orientation orientation (horizontal or vertical)
     * @return boolean indicating whether the ship was placed succesfully
     */
    public boolean addShip(Ship ship, Vector<Coordinate> coordinates, Character orientation) {
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

            //Check if Ship Landed ona mine
            if (tileType == Tile.MINE) {
                ship.hitPiece(c);
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

    /**
     * Returns a grid string that can displays which coordinates have been hit, missed, etc.
     *
     * @param hidden boolean that indicates whether "OCCUPIED" tiles should be hidden or not
     * @return grid in string format
     */
    public String printGrid(boolean hidden) {
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
                    } else if (grid[i][j][k] == Tile.MISS) {
                        line = line + "M";

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


    /**
     * Overloaded sonar pulse function; does not need the "hidden" boolean as this will only be called
     * to show the enemy grid.
     *
     * @param center coordinate for center of sonar pulse
     * @return
     */

    public String printGrid(Coordinate center) {
        String gridString = "";
        String line = "";
        boolean rowVisible;
        boolean colVisible;

        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < 10; i++){
                line = "";
                for(int j = 0; j < 10; j++){

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

    /**
     * Moves the entire fleet in a specified direction
     *
     * @param d direction
     * @return boolean indicating success/failure
     */
    public boolean moveFleet(Direction d) {
        for(Ship ship : playerShips) {
            if(!ship.isSunk()){
                for(Coordinate c : ship.getPieces()){
                    Coordinate c_new = d.moveCoordinate(c);
                    if(!c_new.isValid()){
                        System.out.print("Could not move the fleet... turn wasted!\n");
                        return false;
                    }
                }
            }
        }
        int i = 0;
        for(Ship ship : playerShips) {
            if(!ship.isSunk()) {
                Vector<Coordinate> temp_pieces = new Vector<>(ship.getLength());
                for(Coordinate c : ship.getPieces()){
                    Coordinate c_new = d.moveCoordinate(c);
                    temp_pieces.add(c_new);
                    }

                ship.updatePieces(temp_pieces);
                playerShips.set(i, ship);
            }
            i = i+1;
        }
        return true;
    }
}


