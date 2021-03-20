import edu.colorado.teamc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {

    Game game;
    Vector<Coordinate> h;
    Vector<Coordinate> v;

    @BeforeEach
    void init() {
        game = new Game();

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
    }


    @Test
    void sonarTest(){
        Ship battleship = new Battleship();
        game.placeShip(battleship, v, 1);
        Player p1 = game.getPlayer1();

        String stringToMatch = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 O F F ~ ~ ~ ~ ~ ~ ~\n" +
                "1 O F ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
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
