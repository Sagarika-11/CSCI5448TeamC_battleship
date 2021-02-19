import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Ship;

// TODO: update tests

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
        destroyerCoordinates   = new Coordinate[]{new Coordinate(0,0), new Coordinate(0, 1),
                                 new Coordinate(0,2), new Coordinate(0,3)};
        battleshipCoordinates  = new Coordinate[]{new Coordinate(0,0), new Coordinate(0, 1),
                                 new Coordinate(0,2), new Coordinate(0,3), new Coordinate(0,4)};

        minesweeper = new Ship(minesweeperCoordinates);
        destroyer   = new Ship(destroyerCoordinates);
        battleship  = new Ship(battleshipCoordinates);
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

        minesweeper.hitPiece(0,0);
        minesweeper.hitPiece(0,1);

        assertTrue(minesweeper.isSunk());
    }

}
