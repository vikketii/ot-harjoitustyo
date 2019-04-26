package timestable.domain;

import java.util.List;
import timestable.dao.FileUserDao;

/**
 *  Not yet finished class for sewing together TimesTable and User classes.
 * 
 * @author vikke
 */
public class TimesTableService {

    private FileUserDao userDao;
    private TimesTable timesTable;

    public TimesTableService(FileUserDao fileUserDao) {
        this.userDao = fileUserDao;

        this.testDatabase();

        // this.timesTable = new TimesTable(multiplier, totalVectors);
    }

    public void testDatabase() {
        userDao.create(new User("Omena"));
        userDao.create(new User("Banaani"));
        userDao.create(new User("Appelsiini"));
        List<User> users = userDao.getAll();

        for (User user : users) {
            System.out.println(user.getName());
        }
    }
}
