import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.colorado.teamc.Grid;
import edu.colorado.teamc.Coordinate;
public class GridTest {

    @Test
    public void testEmptyGrid(){
        Grid emptyGrid = new Grid();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                // System.out.print(emptyGrid.getTileType(i, j));
                assertEquals("EMPTY", emptyGrid.getTileType(new Coordinate (i, j)));
            }
            System.out.println("");
        }
    }
}
