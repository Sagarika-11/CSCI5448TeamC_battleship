import edu.colorado.teamc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifeboatTest {

    Game game;
    Vector<Coordinate> h;

    @BeforeEach
    void init() {
        game = new Game("Bob", "Alice");

        h = new Vector<Coordinate>(5);
        h.add(new Coordinate(0,0));
        h.add(new Coordinate(0,1));
        h.add(new Coordinate(0,2));
        h.add(new Coordinate(0,3));
        Ship battleship = new Battleship();
        game.placeShip(battleship, h, 2);
    }

    @Test
    void lifeboatTest(){
        // First sink battleship to launch the lifeboat

        // Hit captain's quarters twice to sink
        game.takeTurn(1, new Coordinate(0,2), "Bomb");
        game.takeTurn(1, new Coordinate(0,2), "Bomb");


        String gridString = game.getPlayer2().playerGrid.printGrid(false);

        String stringToMatch = "Depth: 0\n" + // will add lifeboat
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 X X X X ~ ~ ~ ~ ~ ~\n" +
                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
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
        StringBuilder sb = new StringBuilder(stringToMatch);

        // find "~" to replace at coordinate of lifeboat
        int count = 0;
        for (int i = 0; i < gridString.length(); i++) {
            if (gridString.charAt(i) == 'O') {
                // replace "~" with "O" at lifeboat location in stringToMatch
                sb.setCharAt(i, 'O');
                break;
            }
        }
        assertEquals(gridString, sb.toString());
    }
}
