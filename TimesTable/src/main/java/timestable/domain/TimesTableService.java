package timestable.domain;

import java.util.List;
import javafx.scene.paint.Color;
import timestable.dao.FileUserDao;

/**
 * Not yet finished class for sewing together TimesTable and User classes.
 *
 * @author vikke
 */
public class TimesTableService {

    private FileUserDao userDao;
    private Settings settings;
    private TimesTable timesTable;

    public TimesTableService(FileUserDao fileUserDao, Settings settings) {
        this.userDao = fileUserDao;
        this.settings = settings;
        this.addColorUsersToDatabase();

        this.timesTable = new TimesTable(settings);
    }

    public void addColorUsersToDatabase() {
        if (!userDao.getAllNames().contains("Sininen")) {
            Settings blue = new Settings();
            blue.setLineMainColor(Color.BLUE);
            userDao.create(new User("Sininen", blue));

            Settings red = new Settings();
            red.setLineMainColor(Color.RED);
            userDao.create(new User("Punainen", red));

            Settings green = new Settings();
            green.setLineMainColor(Color.GREEN);
            userDao.create(new User("Vihreä", green));
        }
    }

    public TimesTable getTimesTable() {
        return timesTable;
    }

    public List<Vector> getVectors() {
        return timesTable.getVectors();
    }

    public void updateTimesTable() {
        timesTable.updateVectors();
    }

    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    public List<String> getAllUsersNames() {
        return userDao.getAllNames();
    }

    public void saveUser(User newUser) {
        if (userDao.getAllNames().contains(newUser.getName())) {
            return;
        }
        userDao.create(newUser);
    }
}
