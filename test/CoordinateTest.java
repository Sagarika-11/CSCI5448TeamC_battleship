import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.colorado.teamc.Coordinate;


public class CoordinateTest {

    @Test
    void coordinateTest(){
        Coordinate c1 = new Coordinate(0,1);
        assertEquals(0, c1.getRow());
        assertEquals(1, c1.getCol());
        assertFalse(c1.getHit());
        c1.setHit(true);
        assertTrue(c1.getHit());
    }

}
