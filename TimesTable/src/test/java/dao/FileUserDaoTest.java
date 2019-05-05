/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import timestable.dao.FileUserDao;
import timestable.domain.User;

/**
 *
 * @author vikke
 */
public class FileUserDaoTest {
    FileUserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new FileUserDao("memory");
    }
    
    @Test
    public void canAddValidUserToDatabase() {
        User user = new User("Tester");
        userDao.create(user);
        User foundUser = userDao.findByName("Tester");
        assertEquals("Tester", foundUser.getName());
    }
    
}
