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
        assertTrue(goodCoord.get(0).isCaptain()); // Check that captain's quarters was added successfully
        assertFalse(newGrid.addShip(mineSweeper, goodCoord, horizontal));
        assertFalse(newGrid.addShip(mineSweeper, badCoord, vertical));
        assertFalse(newGrid.addShip(mineSweeper, badSize, vertical));
    }

    @Test
    public void printGridTest(){
        // Check empty grid
        Grid grid = new Grid();
        String gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
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
        Ship mineSweeper = new Ship("minesweeper");
        Vector<Coordinate> mCoord = new Vector<Coordinate>(2);
        mCoord.add(new Coordinate(1, 1));
        mCoord.add(new Coordinate(2, 1));
        grid.addShip(mineSweeper, mCoord, 'v');

        gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        assertEquals(gridString,grid.printGrid(false)); // ship visible (player's grid)

        grid.attemptHit(new Coordinate(1,1)); // hit - X should appear on map
        gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
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

        gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        assertEquals(gridString,grid.printGrid(false)); // check that player's grid is still okay

        // Add one more ship
        Ship destroyer = new Ship("destroyer");
        Vector<Coordinate> dCoord = new Vector<Coordinate>(2);
        dCoord.add(new Coordinate(4, 1));
        dCoord.add(new Coordinate(4, 2));
        dCoord.add(new Coordinate(4,3));
        grid.addShip(destroyer, dCoord, 'h');

        gridString = "  0 1 2 3 4 5 6 7 8 9\n" +
                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "1 ~ X ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "2 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "4 ~ O O O ~ ~ ~ ~ ~ ~\n" +
                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        assertEquals(gridString,grid.printGrid(false)); // check that player's grid is still okay w/ 2 ships
    }
}
