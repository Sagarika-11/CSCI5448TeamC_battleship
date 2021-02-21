package edu.colorado.teamc;

// TODO: implement this class (Sagarika)

import java.util.Vector;

public class Player {

    public Grid player_grid = new Grid();
    public Ship[] player_ships = new Ship[]{new Ship("minesweeper"),
            new Ship("destroyer"),
            new Ship("battleship")};

    public Grid getPlayer_grid() {
        return player_grid;
    }

    public Ship[] getPlayer_ships() {
        return player_ships;
    }

//    public Boolean setPlayer_grid(Grid player_grid, Vector<Coordinate>[] ship_coordinates, char[] ship_orientation) {
//        this.player_grid = player_grid;
//        this.player_ships = player_ships;
//        for(int i=0; i<3; i++) {
//            Boolean temp_bool = player_grid.addShip(player_ships[i], ship_coordinates[i], ship_orientation[i]);
//            if (temp_bool == Boolean.TRUE) {
//                continue;
//            }
//            else {
//                return Boolean.FALSE;
//                }
//        return Boolean.TRUE;
//        }
//    }

    }
