package timestable.dao;

import java.util.List;
import timestable.domain.User;

/**
 * Interface for FileUserDao.
 *
 * @author vikke
 */
public interface UserDao {

    User create(User user);

    List<String> getAllNames();

    User findByName(String name);
    
    void update(User user);

}
