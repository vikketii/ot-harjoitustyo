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

    /**
     * Generates user with custom settings.
     * 
     * @param username
     * @param settings 
     */
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

    /**
     * Checks if two users are equal based on names.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }
}
