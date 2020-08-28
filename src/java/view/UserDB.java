package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Swim;
import view.DBUtil;

public class UserDB {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/user";
    private static String username = "root";
    private static String password = "Vaibhav@12";

    // private member variable to hold the connection
    private static Connection conn = null;

    public UserDB(String url, String username, String pwd) {
        this.url = url;
        this.username = username;
        this.password = pwd;
    }

    /**
     * A method that will connect to the specified JDBC driver
     *
     * @param driver driver to connect to, typically com.mysql.jdbc.Driver
     * @param url url pointing to your database server; i.e.
     * jdbc:mysql://localhost:3306/
     * @param databaseName the database you want to connect to
     * @param username the db username to connect with
     * @param password password correspond to db username
     */
    public static void createConnection() {
        try {
            Class.forName(driver);
            // get the connection from the DriverManager
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user",
                    "root", "Vaibhav@12");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: "
                    + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getSQLState());
            System.err.println("SQLException: " + e.getErrorCode());
            System.err.println("SQLException: " + e.getMessage());
        }
    }

    public static int insert(Swim user) {

        UserDB.createConnection();
        Connection connection = UserDB.conn;
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (name, age, contact, address, time, pack) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setInt(3, user.getContact());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getTime());
            ps.setString(6, user.getPack());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static int update(Swim user) {
        UserDB.createConnection();
        Connection connection = UserDB.conn;
        PreparedStatement ps = null;

        String query = "UPDATE user SET "
                + "name = ?, "
                + "age = ? "
                + "address = ? "
                + "time = ? "
                + "pack = ? "
                + "WHERE contact = ?";
        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setInt(3, user.getContact());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getTime());
            ps.setString(6, user.getPack());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static int delete(Swim user) {
        UserDB.createConnection();
        Connection connection = UserDB.conn;
        PreparedStatement ps = null;

        String query = "DELETE FROM user "
                + "WHERE contact = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getContact());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
    }

    public static boolean contactExists(int contact) {
        UserDB.createConnection();
        Connection connection = UserDB.conn;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;
        int em = 0;
        String query = "SELECT contact FROM user "
                + "WHERE contact = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, contact);
            rs = ps.executeQuery();
            while (rs.next()) {
                em = (int) rs.getObject(1);
            }
            if (em == contact) {
                exists = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
        return exists;
    }

    public static Swim selectUser(int contact) {
        UserDB.createConnection();
        Connection connection = UserDB.conn;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user "
                + "WHERE contact = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, contact);
            rs = ps.executeQuery();
            Swim user = null;
            if (rs.next()) {
                user = new Swim();
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setContact(rs.getInt("contact"));
                user.setAddress(rs.getString("address"));
                user.setTime(rs.getString("time"));
                user.setPack(rs.getString("pack"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeConnection(connection);
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
}
