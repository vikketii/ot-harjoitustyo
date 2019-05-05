package timestable.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import timestable.domain.Settings;
import timestable.domain.User;

/**
 * DAO for managing database connection.
 *
 * @author vikke
 */
public class FileUserDao implements UserDao {

    private String filename;

    /**
     * Makes connection to the database file.
     *
     * @return Connection to database
     */
    private Connection connect() {
        String url = "jdbc:sqlite:./" + filename;

        // Temporal database for testing purposes
        if (filename.contains("memory")) {
            url = "jdbc:sqlite:./test.db";
        }

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Connects to database and creates it if it does not exist. Creates
     * Users-table to database.
     *
     * @param filename Filename SQLite will use
     */
    public FileUserDao(String filename) {
        this.filename = filename;

        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " totalvectors integer NOT NULL,\n"
                + " multiplier real NOT NULL,\n"
                + " speed integer NOT NULL,\n"
                + " linecolor text NOT NULL,\n"
                + " bgcolor text NOT NULL\n"
                + ");";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lists all usernames from database.
     *
     * @return
     */
    public List<String> getAllNames() {
        String sql = "SELECT name From Users;";
        List<String> names = new ArrayList<>();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                names.add(rs.getString("name"));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    /**
     * Finds user by name from database. Returns default user if nothing found.
     *
     * @param name
     * @return Found user.
     */
    public User findByName(String name) {
        String sql = "SELECT * FROM Users WHERE name = ?";
        User user = new User(name);

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            Settings settings = new Settings(
                    rs.getInt("totalvectors"),
                    rs.getDouble("multiplier"),
                    rs.getInt("speed"),
                    Color.valueOf(rs.getString("linecolor")),
                    Color.valueOf(rs.getString("bgcolor")));

            pstmt.close();
            conn.close();

            user.setSettings(settings);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    /**
     * Inserts new username to database.
     *
     * @param user
     * @return Given user
     */
    public User create(User user) {
        String sql = "INSERT INTO"
                + " Users(name,totalvectors,multiplier,speed,linecolor,bgcolor)"
                + " VALUES(?,?,?,?,?,?)";

        Settings settings = user.getSettings();

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, settings.getTotalVectors());
            pstmt.setDouble(3, settings.getMultiplier());
            pstmt.setInt(4, settings.getSpeed());
            pstmt.setString(5, settings.getLineMainColor().toString());
            pstmt.setString(6, settings.getBgMainColor().toString());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    /**
     * Finds user based on name and updates user information in database.
     *
     * @param user
     */
    public void update(User user) {
        String sql = "UPDATE Users SET totalvectors = ? , "
                + "multiplier = ? , "
                + "speed = ? , "
                + "linecolor = ? , "
                + "bgcolor = ? "
                + "WHERE name = ?";

        Settings settings = user.getSettings();

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, settings.getTotalVectors());
            pstmt.setDouble(2, settings.getMultiplier());
            pstmt.setInt(3, settings.getSpeed());
            pstmt.setString(4, settings.getLineMainColor().toString());
            pstmt.setString(5, settings.getBgMainColor().toString());
            pstmt.setString(6, user.getName());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
