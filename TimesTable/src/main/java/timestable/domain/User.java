package timestable.domain;

/**
 * Simple user with username.
 * 
 * @author vikke
 */
public class User {

    private String username;

    /**
     * Generates user.
     * 
     * @param username
     */
    public User(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }
}
