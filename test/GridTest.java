import edu.colorado.teamc.Ship;
import org.junit.jupiter.api.Test;

import edu.colorado.teamc.Grid;
import edu.colorado.teamc.Coordinate;
import java.util.Vector;
import edu.colorado.teamc.Grid.Tile;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void testEmptyGrid(){
        Grid emptyGrid = new Grid();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                // System.out.print(emptyGrid.getTileType(i, j));
                //assertEquals(Tile.EMPTY, emptyGrid.getTileType(new Coordinate (i, j)));
            }
            System.out.println("");
        }
    }

    @Test
    public void testAddShip() {
        Grid newGrid = new Grid();
        Ship mineSweeper = new Ship("minesweeper");
        Vector<Coordinate> goodCoord = new Vector<Coordinate>(2);
        Vector<Coordinate> badCoord = new Vector<Coordinate>(2);
        Vector<Coordinate> badSize = new Vector<Coordinate>(3); //Check if returns false when length != # coords
        goodCoord.add(new Coordinate(1, 1));
        goodCoord.add(new Coordinate(2, 1));

        //Check negative coordinates
        badCoord.add(new Coordinate(-1, 1));
        badCoord.add(new Coordinate(-2, 1));

        //Check bad length coordinates
        badSize.add(new Coordinate(1, 1));
        badSize.add(new Coordinate(2, 1));
        badSize.add(new Coordinate(3, 1));
        Character vertical = 'v';
        Character horizontal = 'h';

        assertTrue(newGrid.addShip(mineSweeper, goodCoord, vertical));
        assertFalse(newGrid.addShip(mineSweeper, goodCoord, horizontal));
        assertFalse(newGrid.addShip(mineSweeper, badCoord, vertical));
        assertFalse(newGrid.addShip(mineSweeper, badSize, vertical));
    }
}
