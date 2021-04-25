import edu.colorado.teamc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Game game;
    Vector<Coordinate> h;
    Vector<Coordinate> v;
    Vector<Coordinate> m;
    Vector<Coordinate> s;

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

        v = new Vector<Coordinate>(5);
        v.add(new Coordinate(2,1));
        v.add(new Coordinate(3,1));
        v.add(new Coordinate(4,1));
        Ship destroyer = new Destroyer();
        game.placeShip(destroyer, v, 2);

        m = new Vector<Coordinate>(5);
        m.add(new Coordinate(5,5));
        m.add(new Coordinate(5,6));
        Ship minesweeper = new Minesweeper();
        game.placeShip(minesweeper, m, 2);

        s = new Vector<Coordinate>(5);
        s.add(new Coordinate(0,1,1));
        s.add(new Coordinate(1,1,1));
        s.add(new Coordinate(2,1,1));
        s.add(new Coordinate(3,1,1));
        s.add(new Coordinate(1,0,1));
        Ship submarine = new Submarine();
        game.placeShip(submarine, s, 2);
    }

    @Test
    void bombTest() {
//        System.out.print(game.getPlayer2().playerGrid.printGrid(false));
        // Take turn (Bomb already activated)
        String stringToMatch = "Hit!";
        String messageString = game.takeTurn(1, new Coordinate(0,0), "Bomb");
        assertEquals(stringToMatch, messageString);
    }

    @Test
    void spaceLaserTest(){
        // First sink a ship to activate SpaceLaser
//        System.out.print(game.getPlayer2().playerGrid.printGrid(false));
        game.takeTurn(1, new Coordinate(5,6), "Bomb");
        game.takeTurn(1, new Coordinate(5,5), "Bomb");

        String stringToMatch = "Hit!Hit!";
        String gridString = game.takeTurn(1, new Coordinate(0,1), "SpaceLaser");
        assertEquals(stringToMatch, gridString);
    }

    @Test
    void sonarTest(){
        // First sink a ship to activate sonar
//        System.out.print(game.getPlayer2().playerGrid.printGrid(false));
        game.takeTurn(1, new Coordinate(5,6), "Bomb");
        game.takeTurn(1, new Coordinate(5,5), "Bomb");

        String stringToMatch = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 O O O ~ ~ ~ ~ ~ ~ ~\n" +
                "1 F F ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 F ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ X X ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 F O F ~ ~ ~ ~ ~ ~ ~\n" +
                "1 O O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 F ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        String gridString = game.takeTurn(1, new Coordinate(0,0), "Sonar");
        assertEquals(stringToMatch, gridString);
    }
}
