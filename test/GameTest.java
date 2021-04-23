import edu.colorado.teamc.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Game game;
    Vector<Coordinate> h;
    Vector<Coordinate> v;
    Vector<Coordinate> d;
    Vector<Coordinate> inv;

    @BeforeEach
    void init(){
        game = new Game("Bob", "Alice");

        // horizontal line
        h = new Vector<Coordinate>(5);
        h.add(new Coordinate(0,0));
        h.add(new Coordinate(0,1));
        h.add(new Coordinate(0,2));

        // vertical line
        v = new Vector<Coordinate>(5);
        v.add(new Coordinate(0,0));
        v.add(new Coordinate(1,0));
        v.add(new Coordinate(2,0));

        // diagonal line
        d = new Vector<Coordinate>(5);
        d.add(new Coordinate(0,0));
        d.add(new Coordinate(1,1));
        d.add(new Coordinate(2,2));
        d.add(new Coordinate(3,3));

        // not a line
        inv = new Vector<Coordinate>(4);
        inv.add(new Coordinate(0,0));
        inv.add(new Coordinate(1,1));
        inv.add(new Coordinate(2,0));
    }

    @Test
    void validLineTest(){
        // horizontal line
        assertTrue(game.checkValidLine(h));

        // vertical line
        assertTrue(game.checkValidLine(v));

        // diagonal line (not necessary for game, shits and giggles i guess)
        assertTrue(game.checkValidLine(d));

        // not a line
        assertFalse(game.checkValidLine(inv));
    }

    @Test
    void orientationTest(){
        // horizontal line
        assertEquals('h', game.getOrientation(h));

        // vertical line
        assertEquals('v', game.getOrientation(v));

        // diagonal line
        assertEquals('x',game.getOrientation(d));

        // not a line
        assertEquals('x',game.getOrientation(inv));
    }

    @Test
    void placeShipTest(){
        // invalid placement (not a line)
        Ship destroyer = new Destroyer();
        String msg = game.placeShip(destroyer, inv, 1);
        assertEquals("Coordinates must be in a line!", msg);

        // valid vertical placement
        msg = game.placeShip(destroyer, v, 1);
        assertEquals("Ship placed successfully", msg);

        // invalid placement (two ships on top of each other)
        msg = game.placeShip(destroyer, h, 1);
        assertEquals("Ship was not placed. Is there another ship in the way?", msg);
    }

    @Test
    void hitCoordinateTest(){
        Ship destroyer = new Destroyer();
        String msg = game.placeShip(destroyer, v, 1);
        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();
        assertEquals(game.getPlayer2(), p2); // shh...
        System.out.println(p1.getPlayerGrid().printGrid(false)); // see ships for testing purposes

        // Testing Hit
        msg = game.takeTurn(2, new Coordinate(0,0), "Bomb");
        assertEquals("Hit!",msg);
//        System.out.println(msg);
//        System.out.println(p1.getPlayerGrid().printGrid(false)); // see ships for testing purposes

        // Testing Miss
        msg = game.takeTurn(2, new Coordinate(9,9), "Bomb");
        assertEquals("Miss",msg);

        // Testing Already Hit; just print Hit anyway
        msg = game.takeTurn(2, new Coordinate(0,0), "Bomb");
        assertEquals("Hit!",msg);

        // Testing Captains Quarters - First hit (armored)
        msg = game.takeTurn(2, new Coordinate(1,0), "Bomb");
        assertEquals("You hit the captain's quarters! Ship critically damaged!",msg);
        String gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "Depth: 1\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        assertEquals(gridString, p1.getPlayerGrid().printGrid(false)); // see ships for testing purposes

        // Second hit - unarmored
        msg = game.takeTurn(2, new Coordinate(1,0), "Bomb");
        System.out.print(msg + "\n");
        assertEquals("You hit the captain's quarters! Ship Sunk!",msg);
        gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "Depth: 1\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        StringBuilder sb = new StringBuilder(gridString);
        String playerGrid = p1.getPlayerGrid().printGrid(false);

        // find "~" to replace at coordinate of lifeboat
        for (int i = 0; i < playerGrid.length(); i++) {
            if (playerGrid.charAt(i) == 'O') {
                // replace "~" with "O" at lifeboat location in stringToMatch
                sb.setCharAt(i, 'O');
                break;
            }
        }
        assertEquals(gridString, playerGrid);
    }
}
