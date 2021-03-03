import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Ship;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {

    private Coordinate[] minesweeperCoordinates;
    private Coordinate[] destroyerCoordinates;
    private Coordinate[] battleshipCoordinates;
    private Ship minesweeper;
    private Ship destroyer;
    private Ship battleship;

    @BeforeEach
    void init(){
        minesweeperCoordinates = new Coordinate[]{new Coordinate(0,0), new Coordinate(0, 1)};
        destroyerCoordinates   = new Coordinate[]{new Coordinate(1,1), new Coordinate(2, 1),
                                 new Coordinate(3,1)};
        battleshipCoordinates  = new Coordinate[]{new Coordinate(2,2), new Coordinate(2, 3),
                                 new Coordinate(2,4), new Coordinate(2,5)};

        minesweeper = new Ship("minesweeper");
        for(Coordinate c : minesweeperCoordinates){
            minesweeper.updateCoordinates(c);
        }
        destroyer   = new Ship("destroyer");
//        for(Coordinate c : minesweeperCoordinates){
//            minesweeper.updateCoordinates(c);
//        }
        battleship  = new Ship("battleship");
        for(Coordinate c : battleshipCoordinates) {
            battleship.updateCoordinates(c);
        }
        battleship.addCaptainsQuarters();
    }
    @Test
    void constructorTest(){
        assertEquals("Minesweeper", minesweeper.getName());
        assertEquals(2, minesweeper.getLength());
        assertEquals("Destroyer", destroyer.getName());
        assertEquals(3, destroyer.getLength());
        assertEquals("Battleship", battleship.getName());
        assertEquals(4, battleship.getLength());
    }

    @Test
    void sunkTest(){
        assertFalse(minesweeper.isSunk());
        assertFalse(battleship.isSunk());

        minesweeper.hitPiece(new Coordinate(0,0));
        minesweeper.hitPiece(new Coordinate(0,1));

        assertTrue(minesweeper.isSunk());

        battleship.hitPiece(new Coordinate(2, 4));
        assertFalse(battleship.isSunk());
        battleship.hitPiece(new Coordinate(2, 4));
        assertTrue(battleship.isSunk());
    }

    @Test
    void getPiecesTest(){
        Ship n = new Ship("minesweeper");
        Vector<Coordinate> test = n.getPieces();
        assertEquals(test, n.getPieces());
    }

}
