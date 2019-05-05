/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.domain.Settings;
import timestable.domain.TimesTable;

/**
 *
 * @author vikke
 */
public class TimesTableTest {

    private TimesTable tt;
    private Settings settings;
    
    @Before
    public void initTest() {
        settings = new Settings(100, 2, 1, Color.BLUE, Color.WHITE);
        tt = new TimesTable(settings);
    }
    
    @Test
    public void initMakesCorrectAmountOfVectors() {
        assertEquals(100, tt.getVectors().size());
    }
    
    @Test
    public void canNotInitNegativeAmountOfVectors() {
        TimesTable t1 = new TimesTable(new Settings(-1, 0, 0, Color.LINEN, Color.GAINSBORO));
        assertTrue(t1.getVectors().isEmpty());
    }
    
    @Test
    public void canIncreaseVectorCount() {
        settings.setTotalVectors(110);
        tt.updateVectors();
        assertEquals(110, tt.getVectors().size());
    }
    
    @Test
    public void canDecreaseVectorCount() {
        settings.setTotalVectors(90);
        tt.updateVectors();
        assertEquals(90, tt.getVectors().size());
    }
    
    @Test
    public void canNotMakeVectorCountNegative() {
        settings.setTotalVectors(0);
        tt.updateVectors();
        assertEquals(0, tt.getVectors().size());
        settings.setTotalVectors(-1);
        tt.updateVectors();
        assertEquals(0, tt.getVectors().size());
    }
}
