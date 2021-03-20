package edu.colorado.teamc;
import java.util.Vector;

public class Game {
    Player player1;
    Player player2;

    public Game() {
        player1 = new Player("Bob");
        player2 = new Player("Alice");
    }

    public boolean checkValidLine(Vector<Coordinate> coordinates){
        // must have at least 2 points
        if(coordinates.size() < 2){
            return false;
        }

        // calculate general form Ax + By + C = 0 (needed for vertical lines)

        double y0 = coordinates.get(0).getCol();
        double y1 = coordinates.get(1).getCol();
        double x0 = coordinates.get(0).getRow();
        double x1 = coordinates.get(1).getRow();

        double B = x1 - x0;
        double A = y0 - y1;
        double C = ((x0 - x1) * y0) + ((y1-y0) * x0);

        // check that each point is on the line
        for(Coordinate c : coordinates){
            double shouldBeZero = (A * c.getRow()) + (B * c.getCol()) + C;

            if(shouldBeZero > 0.0001 || shouldBeZero < -0.0001){
                return false;
            }
        }

        return true;

    }

    public char getOrientation(Vector<Coordinate> coordinates){
        // first check that all coordinates are in a line
        if(!checkValidLine(coordinates)){
            return 'x';
        }

        boolean horizontal = true;
        boolean vertical = true;

        // now check for horizontal (all x coordinates the same)
        for(int i = 0; i < coordinates.size() - 1; i++){
            int currRow = coordinates.get(i).getRow();
            int nextRow = coordinates.get(i+1).getRow();

            if(currRow != nextRow){
                horizontal = false;
                break;
            }
        }

        // now check for horizontal (all x coordinates the same)
        for(int i = 0; i < coordinates.size() - 1; i++){
            int currCol = coordinates.get(i).getCol();
            int nextCol = coordinates.get(i+1).getCol();

            if(currCol != nextCol){
                vertical = false;
                break;
            }
        }

        if(horizontal){
            return 'h';
        }
        if(vertical){
            return 'v';
        }

        // diagonal
        return 'x';
    }

    // placeShip will be called iteratively by the Main function as the user chooses coordinates for their ship placements.
    // The ship and coordinate objects will be created in Main.
    public String placeShip(Ship ship, Vector<Coordinate> coordinates, int player){
        // Check that the ship type aligns with coordinates
        if(ship.getName() == "minesweeper" && coordinates.size() != 2 ||
            ship.getName() == "destroyer" && coordinates.size() != 3 ||
            ship.getName() == "battleship" && coordinates.size() != 4){
            return "Invalid placement for this ship type.";
        }
        // Check that player chose good coordinates
        if(!checkValidLine(coordinates)){
            return "Coordinates must be in a line!";
        }
        char orientation = getOrientation(coordinates);
        if(orientation == 'x'){
            return "Ship must be oriented vertically or horizontally!";
        }

        Player p;
        if (player == 1) {
            p = player1;
        } else if (player == 2) {
            p = player2;
        }
        else {
            return "Invalid player option.";
        }

        // Attempt to place ship
        boolean success = p.addShipToGrid(ship, coordinates, orientation);

        if(success){
            return "Ship placed successfully";
        }
        else{
            return "Ship was not placed. Is there another ship in the way?";
        }
    }

    // Called from the main function. Hits coordinate, updates player ship status, and returns message of success/failure
    // Takes in player to hit and coordinate to hit
    public String takeTurn(int player, Coordinate c, String weaponName) {
        Player p = (player == 1) ? player1 : player2;
        Player enemy = (player == 1) ? player2 : player1;
        Weapon selectedWeapon = null;

        Vector<Weapon> availableWeapons = p.getAvailableWeapons();

        for (Weapon weapon : availableWeapons) {
            if (weaponName.equals(weapon.getName())) {
                selectedWeapon = weapon;
            }
        }

        String msg = enemy.hitPiece(c, selectedWeapon);

        // check if last hit sunk any enemy ships, if it did activate weapons
        if (!enemy.getSunkOneShip()) {
            p.checkAndActivate();
        }

        return msg;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }
}
