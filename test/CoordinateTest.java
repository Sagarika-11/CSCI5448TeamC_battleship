import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.colorado.teamc.Coordinate;

public class CoordinateTest {

    @Test
    void coordinateTest(){
        Coordinate c = new Coordinate(0,1);
        assertEquals(0, c.getRow());
        assertEquals(1, c.getCol());
        assertFalse(c.getHit());
        c.setHit(true);
        assertTrue(c.getHit());
    }
}
