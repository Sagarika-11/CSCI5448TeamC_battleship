import edu.colorado.teamc.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MoveFleetTest {

    @Test
    public void moveCoordinateTest(){
        Coordinate c = new Coordinate(8,1,1);
        Direction dir = new Direction("E");
        Coordinate new_c = dir.moveCoordinate(c);
        Coordinate c_test = new Coordinate(8,0,1);
        assertEquals(c_test, new_c);
        Coordinate c_2 = dir.moveCoordinate(new_c);
        assertFalse(c_2.isValid());
    }

    @Test
    public void moveShipTest(){
        Ship mineSweeper = new Minesweeper();
        Vector<Coordinate> mCoord = new Vector<Coordinate>(2);
        mCoord.add(new Coordinate(1, 5));
        mCoord.add(new Coordinate(2, 5));
        Grid grid = new Grid();
        grid.addShip(mineSweeper, mCoord, 'v');
        //moving ship east
        MoveFleet newMove = new MoveFleet(grid);
        grid = newMove.execute(new Direction("E"));
        Ship new_ship_test = grid.getPlayerShips().get(0);
        Vector<Coordinate> coors = new_ship_test.getPieces();
        assertEquals(1, coors.get(0).getRow());
        assertEquals(4, coors.get(0).getCol());
        assertEquals(2, coors.get(1).getRow());
        assertEquals(4, coors.get(1).getCol());
        //undo
        grid = newMove.undo();
        new_ship_test = grid.getPlayerShips().get(0);
        Vector<Coordinate> coors1 = new_ship_test.getPieces();
        assertEquals(1, coors1.get(0).getRow());
        assertEquals(5, coors1.get(0).getCol());
        assertEquals(2, coors1.get(1).getRow());
        assertEquals(5, coors1.get(1).getCol());
        }

}
