import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Grid;
import edu.colorado.teamc.Player;
import edu.colorado.teamc.Ship;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void PlayerTestGridInitial() {
        Player player = new Player("Bob");
        Grid playerGrid = player.getPlayerGrid();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Coordinate coordinates = new Coordinate(i, j);
                assertEquals(Grid.Tile.EMPTY, playerGrid.getTileType(coordinates));
            }
        }
    }

//    @Test
//    void hitPieceTest(){
//        Player player = new Player("Alice");
//        String msg = player.hitPiece(new Coordinate(9,9, 0)); // testing "Miss"
//        assertEquals("Miss", msg);
//    }

//    @Test
//    public void PlayerTestGridSetup() {
//        Player player = new Player();
//        Grid playerGrid = player.getPlayerGrid();
//        Vector<Ship> playerShips = player.getPlayerShips();
//        Vector<Coordinate>[] shipCoordinates = new Vector[3];
//        for(int i = 0; i < shipCoordinates.length; i++) {
//            shipCoordinates[i] = new Vector<Coordinate>();
//        }
//        char[] shipOrientations = {'v', 'h', 'v'};
//        for (int i = 0; i < 3; i++) {
//            shipCoordinates[i] = getShipCoordinates(playerShips.get(i));
//        }
//        assertTrue(player.setPlayerGrid(playerGrid, shipCoordinates, shipOrientations));
//    }

//    @Test
//    public void playerTurnTest() {
//        Player player = new Player();
//        Grid playerGrid = player.getPlayerGrid();
//        String turnResp = playerGrid.playerTurn(playerGrid, new Coordinate(0,0));
//        assertEquals("Miss", turnResp);
//        assertFalse("Error!".equals(turnResp));
//    }

//    private Vector<Coordinate> getShipCoordinates(Ship ship) {
//        // change this function when implementing I/O
//        Vector<Coordinate> locateShip = new Vector<Coordinate>(ship.getLength());
//        switch (ship.getName()) {
//            case "Minesweeper":
//                locateShip.add(new Coordinate(1, 1));
//                locateShip.add(new Coordinate(2, 1));
//                return locateShip;
//            case "Destroyer":
//                locateShip.add(new Coordinate(2, 2));
//                locateShip.add(new Coordinate(2, 3));
//                locateShip.add(new Coordinate(2, 4));
//                return locateShip;
//            case "Battleship":
//                locateShip.add(new Coordinate(5, 5));
//                locateShip.add(new Coordinate(6, 5));
//                locateShip.add(new Coordinate(7, 5));
//                locateShip.add(new Coordinate(8, 5));
//                return locateShip;
//        }
//        return locateShip;
//    }
}
