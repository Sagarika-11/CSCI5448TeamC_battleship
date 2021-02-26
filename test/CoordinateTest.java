import org.junit.jupiter.api.Test;
import edu.colorado.teamc.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    @Test
    void coordinateTest(){
        Coordinate c = new Coordinate(0,1);
        assertEquals(0, c.getRow());
        assertEquals(1, c.getCol());
        assertFalse(c.isHit());
        c.setHit(true);
        c.setCaptain(true);
        assertTrue(c.isHit());
        assertTrue(c.isCaptain());
    }

    @Test
    void equalsTest(){
        Coordinate c1 = new Coordinate(5,7);
        Coordinate c2 = new Coordinate(5, 7);

        assertEquals(c1, c2);

        Coordinate c3 = new Coordinate(0,0);

        assertNotEquals(c1, c3);
        assertNotEquals(c2, c3);
    }

    @Test
    void compareToTest(){
        Coordinate c1 = new Coordinate(0,0);
        Coordinate c2 = new Coordinate(0,1);
        assertEquals(-1, c1.compareTo(c2));
        Coordinate c3 = new Coordinate(1, 0);
        assertEquals(1,c3.compareTo(c1));
    }

    @Test
    void toStringTest(){
        Coordinate c1 = new Coordinate(0,0);
        assertEquals("x: 0, y: 0", c1.toString());
    }
}
