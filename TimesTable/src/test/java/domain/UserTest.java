/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.domain.User;
import timestable.domain.Settings;

/**
 *
 * @author vikke
 */
public class UserTest {

    @Test
    public void twoUsersWithSameNameAreEqual() {
        Settings settings1 = new Settings(0, 0, 0, Color.LINEN, Color.GAINSBORO);
        User user1 = new User("Tester", settings1);
        Settings settings2 = new Settings(1, 1, 1, Color.BLUE, Color.WHITE);
        User user2 = new User("Tester", settings2);
        
        assertTrue(user1.equals(user2));
    }
    
    @Test
    public void twoUsersWithDifferentNamesAreEqual() {
        User user1 = new User("Tester1");
        User user2 = new User("Tester2");
        
        assertFalse(user1.equals(user2));
    }
}
