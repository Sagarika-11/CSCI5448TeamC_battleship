package edu.colorado.teamc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    private static HashMap<String, Integer> shipSizes;
    private static Game game;

    public static void main(String[] args) {

        createHashMap();
        Scanner in = new Scanner(System.in);

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

            while (!inputShip("Minesweeper", i)) ;
            while (!inputShip("Destroyer", i)) ;
            while (!inputShip("Battleship", i)) ;
            while (!inputShip("Submarine", i)) ;
            System.out.print(game.getPlayer1().playerGrid.printGrid(false));
            System.out.print("\n");
        }



    }

    private static void createHashMap() {
        shipSizes = new HashMap<>();
        shipSizes.put("Minesweeper", 2);
        shipSizes.put("Destroyer", 3);
        shipSizes.put("Battleship", 4);
        shipSizes.put("Submarine", 5);
    }

    private static boolean inputShip(String shipName, int player) {
        Scanner in = new Scanner(System.in);

        int shipSize = shipSizes.get(shipName);

        System.out.print("Place a " + shipName + ": ");
        String[] inputCoords = in.nextLine().split(" ");

        boolean validCoords = false;
        Vector<Coordinate> coords = new Vector<>(5);

        while (!validCoords) {
            coords.clear();

            for (int i = 0; i < inputCoords.length; i++) {
                String ic = inputCoords[i];

                if (ic.length() != 2) { // not a two-number coordinate
                    System.out.print("\033[0;31m" + "Coordinates must be in the format: \"## ## ##\"\n" + "\u001B[0m");
                    return false;
                }

                int x = Character.getNumericValue(ic.charAt(0));
                int y = Character.getNumericValue(ic.charAt(1));

                if (x > 9 || y > 9) { // letters or other invalid value
                    System.out.print("\033[0;31m" + "Coordinates must be in the format: \"## ## ##\"\n" + "\u001B[0m");
                    return false;
                }

                if (shipName == "Submarine") { // depth of 1
                    coords.add(new Coordinate(x, y, 1));
                }
                else { // depth of 0
                    coords.add(new Coordinate(x, y));
                }

                validCoords = true;
            }
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
