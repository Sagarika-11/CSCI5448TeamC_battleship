import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Ship;

// TODO: update tests

public class ShipTest {

    private Coordinate[] coordinates;
    private Ship minesweeper;
    private Ship destroyer;
    private Ship battleship;

    @BeforeAll
    void init(){
        coordinates = {new Coordinate(0,0), new Coordinate(0, 1)};
        minesweeper = new Ship("Minesweeper", coordinates);
        destroyer = new Ship("Destroyer", coordinates);
        battleship = new Ship("Battleship", coordinates);
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
