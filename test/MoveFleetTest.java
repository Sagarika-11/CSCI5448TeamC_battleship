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
        MoveFleet newMove = new MoveFleet(grid);
        grid = newMove.execute(new Direction("W"));
        Ship new_ship_test = grid.getPlayerShips().get(0);
        for(Coordinate coors : new_ship_test.getPieces()){
            System.out.println(coors.getRow());
            System.out.println(coors.getCol());
            System.out.println(" ..... ");
        }
        grid = newMove.undo();
        new_ship_test = grid.getPlayerShips().get(0);
        for(Coordinate coors : new_ship_test.getPieces()){
            System.out.println(coors.getRow());
            System.out.println(coors.getCol());
            System.out.println(" ..... ");
        }
        grid = newMove.undo();
        new_ship_test = grid.getPlayerShips().get(0);
        for(Coordinate coors : new_ship_test.getPieces()){
            System.out.println(coors.getRow());
            System.out.println(coors.getCol());
            System.out.println(" ..... ");
        }

//        System.out.println(grid.printGrid(false));
//        grid.moveFleet(new Direction("N"));
//        Ship new_ship_test = grid.getPlayerShips().get(0);
//        for(Coordinate coors : new_ship_test.getPieces()){
//            System.out.println(coors.getRow());
//            System.out.println(coors.getCol());
//        }
//        grid.moveFleet(new Direction("S"));
//        new_ship_test = grid.getPlayerShips().get(0);
//        for(Coordinate coors : new_ship_test.getPieces()){
//            System.out.println(coors.getRow());
//            System.out.println(coors.getCol());
        }


//    @Test
//    public void moveFleetTest(){
//        Ship submarine = new Submarine();
//        Vector<Coordinate> coords = new Vector<>(2);
//        coords.add(new Coordinate(4, 1,1));
//        coords.add(new Coordinate(5, 1,1));
//        coords.add(new Coordinate(6, 1,1));
//        coords.add(new Coordinate(6, 2,1));
//        coords.add(new Coordinate(7, 1,1));
//
//        Grid grid = new Grid();
//        grid.addShip(submarine, coords, 'v');
//        System.out.println(grid.printGrid(false));
//        MoveFleet newMove = new MoveFleet(grid);
//        Grid new_grid = newMove.execute(new Direction("W"));
//        System.out.println(new_grid.printGrid(false));
//        String gridString = "Depth: 0\n" +
//                "  0 1 2 3 4 5 6 7 8 9\n" +
//                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "4 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "5 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "6 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "7 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "Depth: 1\n" +
//                "  0 1 2 3 4 5 6 7 8 9\n" +
//                "0 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "1 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "2 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "3 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "4 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "5 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "6 ~ O O ~ ~ ~ ~ ~ ~ ~\n" +
//                "7 ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "8 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
//                "9 ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

//        assertEquals(gridString, grid.printGrid(false));
//    }

}
