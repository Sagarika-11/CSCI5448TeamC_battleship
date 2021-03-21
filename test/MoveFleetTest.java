import edu.colorado.teamc.Coordinate;
import edu.colorado.teamc.Direction;
import org.junit.jupiter.api.Test;

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


}
