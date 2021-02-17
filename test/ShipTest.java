import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.colorado.teamc.Ship;

public class ShipTest {
    @Test
    void nameTest(){
        Ship ship = new Ship();
        ship.setName("Minesweeper");
        assertEquals("Minesweeper", ship.getName());
    }
    @Test
    void lengthTest(){
        Ship ship = new Ship();
        ship.setLength(5);
        assertEquals(5, ship.getLength());
    }
    @Test
    void piecesHitTest(){
        Ship ship = new Ship("Minesweeper", 2);
        assertEquals(0, ship.getPiecesHit());
    }
}
