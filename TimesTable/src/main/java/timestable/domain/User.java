package timestable.domain;

/**
 * Simple user with username.
 *
 * @author vikke
 */
public class User {

    private String username;
    private Settings settings;

    /**
     * Generates user.
     *
     * @param username
     */
    public User(String username) {
        this.username = username;
        this.settings = new Settings();
    }

    public User(String username, Settings settings) {
        this.username = username;
        this.settings = settings;
    }

    public String getName() {
        return username;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
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
