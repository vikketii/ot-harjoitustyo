package timestable.domain;

import java.util.List;
import javafx.scene.paint.Color;
import timestable.dao.UserDao;

/**
 *
 * @author vikke
 */
public class TimesTableService {

    private UserDao userDao;
    private Settings settings;
    private TimesTable timesTable;

    /**
     * Initialises TimesTable class and calls addColorUsersToDatabase method
     * to add three default users to database.
     * 
     * @param userDao
     * @param settings 
     */
    public TimesTableService(UserDao userDao, Settings settings) {
        this.userDao = userDao;
        this.settings = settings;
        this.addColorUsersToDatabase();

        this.timesTable = new TimesTable(settings);
    }
    
    /**
     * Adds three default users to database.
     */
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

    /**
     * Calls TimesTable to update vectors.
     */
    public void updateTimesTable() {
        timesTable.updateVectors();
    }

    /**
     * Calls FileUserDao to find user from database.
     * 
     * @param name
     * @return Found user.
     */
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }

    /**
     * Calls FileUserDao to find all usernames from database.
     * 
     * @return List of usernames.
     */
    public List<String> getAllUsersNames() {
        return userDao.getAllNames();
    }

    /**
     * Calls FileUserDao to save user to database if the user doesn't already exist.
     * @param newUser 
     */
    public void saveUser(User newUser) {
        if (userDao.getAllNames().contains(newUser.getName())) {
            return;
        }
        userDao.create(newUser);
    }
}
