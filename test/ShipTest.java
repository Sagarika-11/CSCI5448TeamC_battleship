import edu.colorado.teamc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        minesweeper = new Minesweeper();
        for(Coordinate c : minesweeperCoordinates){
            minesweeper.updateCoordinates(c);
        }
        destroyer   = new Destroyer();
        for(Coordinate c : destroyerCoordinates){
            destroyer.updateCoordinates(c);
        }
        battleship  = new Battleship();
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

        destroyer.addCaptainsQuarters();
        destroyer.hitPiece(new Coordinate(2,1));
        destroyer.hitPiece(new Coordinate(2,1));
        assertTrue(destroyer.isSunk());

        battleship.hitPiece(new Coordinate(2, 4));
        assertFalse(battleship.isSunk());
        battleship.hitPiece(new Coordinate(2, 4));
        assertTrue(battleship.isSunk());
    }

    @Test
    void getPiecesTest(){
        Ship n = new Minesweeper();
        Vector<Coordinate> test = n.getPieces();
        assertEquals(test, n.getPieces());
    }

    @Test
    void addCaptainsTest(){
        minesweeper.addCaptainsQuarters();
        assertTrue(minesweeper.isCaptainsQuarters(new Coordinate(0,0)));
        destroyer.addCaptainsQuarters();
        assertTrue(destroyer.isCaptainsQuarters(new Coordinate(2,1)));
        battleship.addCaptainsQuarters();
        assertTrue(battleship.isCaptainsQuarters(new Coordinate(2,4)));
    }

    @Test
    void hitPieceTest(){
        minesweeper.hitPiece(new Coordinate(0,1));
        Coordinate pieceHit = minesweeper.getPieces().get(1);
        assertTrue(pieceHit.isHit());

        destroyer.hitPiece(new Coordinate(1,1));
        pieceHit = destroyer.getPieces().get(0);
        assertTrue(pieceHit.isHit());

        battleship.hitPiece(new Coordinate(2,2));
        pieceHit = battleship.getPieces().get(0);
        assertTrue(pieceHit.isHit());
    }

    @Test
    void hasCoordinateTest(){
        assertTrue(minesweeper.hasCoordinate(new Coordinate(0,0)));
        assertFalse(minesweeper.hasCoordinate(new Coordinate(9,9)));
    }

}
