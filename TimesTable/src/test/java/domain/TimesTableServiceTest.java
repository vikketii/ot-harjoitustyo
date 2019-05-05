/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.dao.FileUserDao;
import timestable.domain.Settings;
import timestable.domain.TimesTableService;

/**
 *
 * @author vikke
 */
public class TimesTableServiceTest {
    TimesTableService tts;
    
    @Before
    public void setUp() {
        FileUserDao userDao = new FileUserDao("jdbc:sqlite::memory");
        Settings settings = new Settings();
        tts = new TimesTableService(userDao, settings);
    }
    
    @Test
    public void defaultUsersAreAdded() {
        tts.addColorUsersToDatabase();
        assertEquals("Sininen", tts.getAllUsersNames().get(0));
        assertEquals("Punainen", tts.getAllUsersNames().get(1));
        assertEquals("Vihreä", tts.getAllUsersNames().get(2));
    }
}
