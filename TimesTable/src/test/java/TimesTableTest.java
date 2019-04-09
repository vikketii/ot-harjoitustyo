/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        tt = new TimesTable();
    }
    
    @Test
    public void initMakesCorrectAmountOfVectors() {
        tt.init(0, 100);
        assertEquals(100, tt.getVectors().size());
    }
    
    @Test
    public void canNotInitNegativeAmountOfVectors() {
        tt.init(0, -1);
        assertTrue(tt.getVectors().isEmpty());
    }
}
