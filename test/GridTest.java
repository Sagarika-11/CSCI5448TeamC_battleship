import edu.colorado.teamc.*;
import org.junit.jupiter.api.Test;
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
        Ship mineSweeper = new Minesweeper();
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
        assertTrue(goodCoord.get(0).isCaptain()); // Check that captain's quarters was added successfully
        assertFalse(newGrid.addShip(mineSweeper, goodCoord, horizontal));
        assertFalse(newGrid.addShip(mineSweeper, badCoord, vertical));
        assertFalse(newGrid.addShip(mineSweeper, badSize, vertical));
    }

    @Test
    public void printGridTest(){
        // Check empty grid
        Grid grid = new Grid();
        String gridString = "Depth: 0\n" +
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
        assertEquals(gridString, grid.printGrid(true));
        assertEquals(gridString, grid.printGrid(false));

        // Check grid with a ship
        Ship mineSweeper = new Minesweeper();
        Vector<Coordinate> mCoord = new Vector<Coordinate>(2);
        mCoord.add(new Coordinate(1, 1));
        mCoord.add(new Coordinate(2, 1));
        grid.addShip(mineSweeper, mCoord, 'v');

        gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
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
        assertEquals(gridString,grid.printGrid(false)); // ship visible (player's grid)

        grid.attemptHit(new Coordinate(1,1)); // hit captain's q - two Xs should appear on map
        gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
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
        assertEquals(gridString, grid.printGrid(true)); // ship invisible (enemy's grid)
        grid.attemptHit(new Coordinate(1,5)); // no hit - no X on map
        assertEquals(gridString, grid.printGrid(true));

        gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
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
        assertEquals(gridString,grid.printGrid(false)); // check that player's grid is still okay

        // Add one more ship
        Ship destroyer = new Destroyer();
        Vector<Coordinate> dCoord = new Vector<Coordinate>(2);
        dCoord.add(new Coordinate(4, 1));
        dCoord.add(new Coordinate(4, 2));
        dCoord.add(new Coordinate(4,3));
        grid.addShip(destroyer, dCoord, 'h');

        gridString = "Depth: 0\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ O O O ~ ~ ~ ~ ~ ~\n" +
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
        assertEquals(gridString,grid.printGrid(false)); // check that player's grid is still okay w/ 2 ships
    }

    @Test
    public void submarineGridTest() {
        Ship submarine = new Submarine();
        Vector<Coordinate> coords = new Vector<>(2);
        coords.add(new Coordinate(4, 1,1));
        coords.add(new Coordinate(5, 1,1));
        coords.add(new Coordinate(6, 1,1));
        coords.add(new Coordinate(6, 2,1));
        coords.add(new Coordinate(7, 1,1));

        Grid grid = new Grid();
        grid.addShip(submarine, coords, 'v');
        String gridString = "Depth: 0\n" +
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
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "Depth: 1\n" +
                "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ O O ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        assertEquals(gridString, grid.printGrid(false));
    }


    @Test
    public void sonarPulsePrintGrid(){
        Grid grid = new Grid();
        Ship mineSweeper = new Minesweeper();
        Vector<Coordinate> mCoord = new Vector<Coordinate>(2);
        mCoord.add(new Coordinate(1, 1));
        mCoord.add(new Coordinate(2, 1));
        grid.addShip(mineSweeper, mCoord, 'v');

        Ship destroyer = new Destroyer();
        Vector<Coordinate> dCoord = new Vector<Coordinate>(2);
        dCoord.add(new Coordinate(4, 1));
        dCoord.add(new Coordinate(4, 2));
        dCoord.add(new Coordinate(4,3));
        grid.addShip(destroyer, dCoord, 'h');

        grid.attemptHit(new Coordinate(1,1));

        // Check sonar pulse - center of the grid

        String gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ X ~ ~ F ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ F F F ~ ~ ~ ~\n" +
                "4 ~ ~ O O F F F ~ ~ ~\n" +
                "5 ~ ~ ~ F F F ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ F ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        assertEquals(gridString, grid.printGrid(new Coordinate(4, 4)));

        // Check sonar pulse - running off the grid
        gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ F F F F F ~ ~ ~\n" +
                "1 ~ X ~ F F F ~ ~ ~ ~\n" +
                "2 ~ X ~ ~ F ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        assertEquals(gridString, grid.printGrid(new Coordinate(0, 4)));
    }
}
