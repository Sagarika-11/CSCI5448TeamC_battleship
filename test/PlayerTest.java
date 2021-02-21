import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Grid;
import edu.colorado.teamc.Player;
import edu.colorado.teamc.Ship;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void PlayerTest_GridInitial() {
        Player Pl1 = new Player();
        Grid pl_grid = Pl1.getPlayer_grid();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Coordinate coords = new Coordinate(i, j);
                System.out.print(pl_grid.getTileType(coords));
                assertEquals("EMPTY", pl_grid.getTileType(coords));
            }
//            System.out.println("");
        }
    }

    @Test
    public void PlayerTest_Grid() {
        Player Pl1 = new Player();
        Grid pl_grid = Pl1.getPlayer_grid();
        Ship[] pl_ships = Pl1.getPlayer_ships();
        Vector<Coordinate>[] ship_coords = new Vector[3];
        for(int i = 0; i < ship_coords.length; i++) {
            ship_coords[i] = new Vector<Coordinate>();
        }
        char[] ship_orientations = {'V', 'H', 'V'};
        for (int i = 0; i < 3; i++) {
            ship_coords[i] = getShip_coordinates(pl_ships[i]);
        }
//        assertTrue(Pl1.setPlayer_grid(pl_grid, ship_coords, ship_orientations));
    }

    @Test
    public void playerTurnTest() {
        Player Pl1 = new Player();
        Grid pl_grid = Pl1.getPlayer_grid();
        String turn_resp = pl_grid.playerTurn(pl_grid, new Coordinate(0,0));
        assertEquals("Miss", turn_resp);
        assertFalse("Error!".equals(turn_resp));
    }

    private Vector<Coordinate> getShip_coordinates(Ship ship) {
        // change this function when implementing I/O
        Vector<Coordinate> locate_ship = new Vector<Coordinate>(ship.getLength());
        switch (ship.getName()) {
            case "Minesweeper":
                locate_ship.add(new Coordinate(0, 0));
                locate_ship.add(new Coordinate(1, 0));
            case "Destroyer":
                locate_ship.add(new Coordinate(2, 2));
                locate_ship.add(new Coordinate(2, 3));
                locate_ship.add(new Coordinate(2, 4));
            case "Battleship":
                locate_ship.add(new Coordinate(5, 1));
                locate_ship.add(new Coordinate(6, 1));
                locate_ship.add(new Coordinate(7, 1));
                locate_ship.add(new Coordinate(8, 1));
        }
        return locate_ship;
    }
}
