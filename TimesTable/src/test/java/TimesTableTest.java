/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.domain.TimesTable;

/**
 *
 * @author vikke
 */
public class TimesTableTest {

    private TimesTable tt;
    
    @Before
    public void initTest() {
        tt = new TimesTable(0, 100);
    }
    
    @Test
    public void initMakesCorrectAmountOfVectors() {
        assertEquals(100, tt.getVectors().size());
    }
    
    @Test
    public void canNotInitNegativeAmountOfVectors() {
        TimesTable t1 = new TimesTable(0, -1);
        assertTrue(t1.getVectors().isEmpty());
    }
    
    @Test
    public void canIncreaseVectorCount() {
        tt.updateVectors(0, 101);
        tt.updateVectors(0, 1000);
        assertEquals(1000, tt.getVectors().size());
    }
    
    @Test
    public void canDecreaseVectorCount() {
        tt.updateVectors(0, 99);
        tt.updateVectors(0, 50);
        assertEquals(50, tt.getVectors().size());
    }
    
    @Test
    public void canNotMakeVectorCountNegative() {
        tt.updateVectors(0, 0);
        assertEquals(0, tt.getVectors().size());
        tt.updateVectors(0, -1);
        assertEquals(0, tt.getVectors().size());
    }
}
