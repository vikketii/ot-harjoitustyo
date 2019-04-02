
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.domain.Vector;

/**
 *
 * @author vikke
 */
public class VectorTest {
    
     @Test
     public void twoDifferentVectorsWithSameValuesAreEqual() {
         Vector v1 = new Vector(1, 1, 2, 2);
         Vector v2 = new Vector(1, 1, 2, 2);
         
         assertEquals(v2, v1);
     }
     
     @Test
     public void twoDifferentVectorsWithDifferentValuesAreNotEqual() {
         Vector v1 = new Vector(1, 1, 2, 2);
         Vector v2 = new Vector(1, 1, 2, 3);
         
         assertNotEquals(v2, v1);
     }
}
