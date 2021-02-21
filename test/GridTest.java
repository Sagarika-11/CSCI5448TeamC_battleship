import edu.colorado.teamc.Ship;
import org.junit.jupiter.api.Test;

import edu.colorado.teamc.Grid;
import edu.colorado.teamc.Coordinate;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testEmptyGrid(){
        Grid emptyGrid = new Grid();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                // System.out.print(emptyGrid.getTileType(i, j));
                assertEquals("EMPTY", emptyGrid.getTileType(new Coordinate (i, j)));
            }
            System.out.println("");
        }
    }

    @Test
    public void testAddShip() {
        Grid newGrid = new Grid();
        Ship mineSweeper = new Ship("minesweeper");
        Vector<Coordinate> mineCoord = new Vector<Coordinate>(2);
        mineCoord.add(new Coordinate(1, 1));
        mineCoord.add(new Coordinate(2, 1));
        Character vertical = 'v';
        Character horizontal = 'h';

        assertTrue(newGrid.addShip(mineSweeper, mineCoord, vertical));
        assertFalse(newGrid.addShip(mineSweeper, mineCoord, horizontal));
    }
}
