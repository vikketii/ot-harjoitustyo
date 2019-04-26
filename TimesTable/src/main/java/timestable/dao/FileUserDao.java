package timestable.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import timestable.domain.User;

/**
 * DAO for managing database connection.
 * 
 * @author vikke
 */
public class FileUserDao implements UserDao {

    private String filename;

    private Connection connect() {
        String url = "jdbc:sqlite:./" + filename;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * Connects to database and creates it if it does not exist.
     * Creates Users-table to database.
     * 
     * @param filename Filename SQLite will use
     */
    public FileUserDao(String filename) {
        this.filename = filename;

        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL\n"
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
     * Lists all users from database.
     * @return 
     */
    public List<User> getAll() {
        String sql = "SELECT id, name From Users;";
        List<User> users = new ArrayList<>();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.add(new User(rs.getString("name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    /**
     * Inserts new username to database.
     * @param user 
     * @return Given user
     */
    public User create(User user) {
        String sql = "INSERT INTO Users(name) VALUES(?);";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
