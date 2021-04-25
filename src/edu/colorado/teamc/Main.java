package edu.colorado.teamc;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    private static HashMap<String, Integer> shipSizes;
    private static Game game;
    private static Scanner in;

    /**
     * I/O system for playing battleship. Allows both players to place their ships and take turns until
     * someone has won the game. The player can input a coordinate in the form "##" with each "#" being a value
     * from 0-9. If you want to use sonar, you can type "## sonar". To move the fleet, the player can type
     * "movefleet D", where "D" is a direction "N, E, S, W".
     *
     * @param args default command line arguments
     */
    public static void main(String[] args) {

        createHashMap();
        in = new Scanner(System.in);

        System.out.print("Welcome to Battleship!\n----------------------\n");

        // enter player names
        System.out.print("Enter player 1's name: ");
        String player1 = in.nextLine();
        System.out.print("Enter player 2's name: ");
        String player2 = in.nextLine();

        game = new Game(player1, player2);

        // place ships
        System.out.print("\nTo place a ship, enter a valid number of coordinates for each ship.\n" +
                "For example, if you want a minesweeper at (0,0), (1,0) you can type: \"00 01\"\n" +
                "----------------------------------------------------------------------------\n");

        for (int i = 1; i <= 2; i ++) { // have both players place their ships
            System.out.print("Player " + ((i==1) ? player1 : player2 ) + " place your ships:\n");

            while (!inputShip("Minesweeper", i));
            while (!inputShip("Destroyer", i));
            while (!inputShip("Battleship", i));
            while (!inputShip("Submarine", i));
            System.out.print("\n");
        }

        int turnCounter = 0;
        boolean hasWinner = false;

        System.out.print("\nTime to play!\n" +
                           "-------------");

        while (!hasWinner) {
            int player = (turnCounter % 2) + 1;

            if (singleTurn(player)) {
                turnCounter++;
            }

            // check to see if anyone won the game
            if (game.getPlayer1().checkAllSunk()) {
                System.out.print("\n" + game.getPlayer2().getPlayerName() + " has won the game!!!\n");
                return;
            }
            if (game.getPlayer2().checkAllSunk()) {
                System.out.print("\n" + game.getPlayer1().getPlayerName() + " has won the game!!!\n");
                return;
            }
        }
    }

    private static void createHashMap() {
        shipSizes = new HashMap<>();
        shipSizes.put("Minesweeper", 2);
        shipSizes.put("Destroyer", 3);
        shipSizes.put("Battleship", 4);
        shipSizes.put("Submarine", 5);
    }

    private static boolean singleTurn(int player) {

        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();

        // print enemy grid that the current player would see + player whose turn it is
        if (player == 1) {
            System.out.print("\n" + p2.getPlayerGrid().printGrid(false));
            System.out.print(p1.getPlayerName() + "'s turn: ");
        }
        else {
            System.out.print("\n" + p1.getPlayerGrid().printGrid(false));
            System.out.print(p2.getPlayerName() + "'s turn: ");
        }

        String[] input = in.nextLine().split(" ");

        if (input[0].equals("movefleet")) { // move the fleet (input: "movefleet <direction>")
            MoveFleet newMove;
            if (player == 1) {
                newMove = new MoveFleet(p1.playerGrid);
                Grid grid = newMove.execute(new Direction(input[1]));
                p1.setPlayerGrid(grid);
            }
            else {
                newMove = new MoveFleet(p2.playerGrid);
                Grid grid = newMove.execute(new Direction(input[1]));
                p2.setPlayerGrid(grid);
            }
            return true;
        }

        else if (input[0].equals("undo")) { // undo movefleet (input: "undo")
            MoveFleet newMove;
            if (player == 1) {
                newMove = new MoveFleet(p1.playerGrid);
                Grid grid = newMove.undo();
                p1.setPlayerGrid(grid);
            }
            else {
                newMove = new MoveFleet(p2.playerGrid);
                Grid grid = newMove.undo();
                p2.setPlayerGrid(grid);
            }
            return true;
        }

        if(!checkSingleCoord(input[0])) { // if coordinate is not valid
            return false;
        }

        int x = Character.getNumericValue(input[0].charAt(0));
        int y = Character.getNumericValue(input[0].charAt(1));
        Coordinate c = new Coordinate(x, y, 0);

        String msg = "";
        if (input.length == 1) { // no special weapons, just take a turn (Bomb or SpaceLaser)
            if (player == 1) {
                msg = game.takeTurn(player, c, p1.getAvailableWeapons().get(0).getName());
            }
            else {
                msg = game.takeTurn(player, c, p2.getAvailableWeapons().get(0).getName());
            }
        }
        else {
            if (input[1].equals("sonar")) { // use sonar (input: "## sonar")
                System.out.print("Using Sonar: \n");
                msg = game.takeTurn(player, c, "Sonar");
            }
        }
        System.out.print(msg);

        return true;
    }

    private static boolean checkSingleCoord(String inputCoord) {
        if (inputCoord.length() != 2) { // not a two-number coordinate
            System.out.print("\033[0;31m" + "Coordinates must be in the format: \"## ## ##\"\n" + "\u001B[0m");
            return false;
        }

        int r = Character.getNumericValue(inputCoord.charAt(0));
        int c = Character.getNumericValue(inputCoord.charAt(1));

        if (r > 9 || c > 9) { // letters or other invalid value
            System.out.print("\033[0;31m" + "Coordinates must be in the format: \"## ## ##\"\n" + "\u001B[0m");
            return false;
        }

        return true;
    }

    private static boolean inputShip(String shipName, int player) {

        int shipSize = shipSizes.get(shipName);

        System.out.print("Place a " + shipName + ": ");
        String[] inputCoords = in.nextLine().split(" ");

        boolean validCoords = false;
        Vector<Coordinate> coords = new Vector<>(5);

        while (!validCoords) {
            coords.clear();

            for (int i = 0; i < inputCoords.length; i++) {

                if(!checkSingleCoord(inputCoords[i])) {
                    return false;
                }

                int r = Character.getNumericValue(inputCoords[i].charAt(0));
                int c = Character.getNumericValue(inputCoords[i].charAt(1));

                if (shipName == "Submarine") { // depth of 1
                    coords.add(new Coordinate(r, c, 1));
                }
                else { // depth of 0
                    coords.add(new Coordinate(r, c));
                }
            }
            validCoords = true;
        }

        if (coords.size() != shipSize) { // wrong number of coordinates for specified ship
            System.out.print("\033[0;31m" + shipName + " must have " + shipSize + " coordinates\n" + "\u001B[0m");
        }
        else {
            Ship ship;
            if (shipName == "Minesweeper")
                ship = new Minesweeper();
            else if (shipName == "Destroyer")
                ship = new Destroyer();
            else if (shipName == "Battleship")
                ship = new Battleship();
            else
                ship = new Submarine();
            String msg = game.placeShip(ship, coords, player);
            System.out.print(msg + "\n");

            if (msg == "Ship placed successfully") {
                return true;
            }
        }
        return false;
    }
}
