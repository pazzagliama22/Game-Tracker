package datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.VideoGame;

/**
 * DAOSQLite Data Access Object for an SQLite database
 *
 * @author John Phillips
 * @version 0.3 on 2015-11-03
 */
public class DAOSQLite {

    protected final static String DRIVER = "org.sqlite.JDBC";
    protected final static String JDBC = "jdbc:sqlite";

    /**
     * Inserts an record into the database table. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param game the object to insert
     * @param dbPath the path to the SQLite database
     */
    public static void createRecord(VideoGame game, String dbPath) {
        String q = "insert into game (id, name, rating, date, completion, comments) values (null, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, game.getGameName());
            ps.setString(2, game.getReleaseDate());
            ps.setString(3, game.getReleaseDate());
            ps.setString(4, game.getTimeToComplete());
            ps.setString(5, game.getGameComments());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve a record given an empId.
     *
     * @param momentId the empId of the record to retrieve
     * @param dbPath the path to the SQLite database
     * @return Employee object
     */
    public static VideoGame retrieveRecordById(int id, String dbPath) {
        String q = "select id, name, rating, date, completion, comments where Id = ?";
        VideoGame game = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            game = myQuery(conn, ps).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return game;
    }

    /**
     * Retrieve all of the records in the database as a list sorted by lastName,
     * firstName.
     *
     * @param dbPath the path to the SQLite database
     * @return list of objects
     */
    public static List<VideoGame> retrieveAllRecordsByName(String dbPath) {
        String q = "select * from moment order by Id";
        List<VideoGame> list = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            list = myQuery(conn, ps);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Update a record from the database given an employee object. Note the use
     * of a parameterized query to prevent SQL Injection attacks.
     *
     * @param moment the employee record to update
     * @param dbPath the path to the SQLite database
     */
    public static void updateRecord(VideoGame moment, String dbPath) {
        String q = "update moment set momentTitle=?, momentDate=?, momentTime=?, momentDesc=?, momentUrl=? where momentId = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, moment.getGameName());
            ps.setString(2, moment.getReleaseDate());
            ps.setString(3, moment.getReleaseDate());
            ps.setString(4, moment.getTimeToComplete());
            ps.setString(5, moment.getGameComments());
            ps.setInt(6, moment.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete a record from the database given its id. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param id the id of the record to delete
     * @param dbPath the path to the SQLite database
     */
    public static void deleteRecord(int id, String dbPath) {
        String q = "delete from moment where momentId = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new employee table.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void createTable(String dbPath) {
        String q = "create table game ("
                + "id integer not null primary key autoincrement, "
                + "name varchar(20) not null, "
                + "rating varchar(20) not null, "
                + "date varchar(20) not null, "
                + "completion varchar(20) not null, "
                + "comments varchar(20) not null)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Drops the employee table erasing all of the data.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void dropTable(String dbPath) {
        final String q = "drop table if exists moment";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the table with sample data records.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void populateTable(String dbPath) {
        /*Moment p;
        p = new Moment(0, "Doe", "Jane", "5706621234", 50000.00);
        DAOSQLite.createRecord(p, dbPath);
        p = new Moment(0, "Doe", "John", "5706621234", 40000.00);
        DAOSQLite.createRecord(p, dbPath);
        p = new Moment(0, "Smith", "Bob", "5706623333", 52000.00);
        DAOSQLite.createRecord(p, dbPath);
        p = new Moment(0, "Jones", "Sue", "5706624444", 55000.00);
        DAOSQLite.createRecord(p, dbPath);
        p = new Moment(0, "Brown", "Ada", "5706625555", 35000.00);
        DAOSQLite.createRecord(p, dbPath);*/
        // We don't populate this automatically.
    }

    /**
     * A helper method that executes a prepared statement and returns the result
     * set as a list of objects.
     *
     * @param conn a connection to the database
     * @param ps a prepared statement
     * @return list of objects from the result set
     */
    protected static List<VideoGame> myQuery(Connection conn, PreparedStatement ps) {
        List<VideoGame> list = new ArrayList();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                String rating = rs.getString("rating");
                String date = rs.getString("date");
                String completion = rs.getString("completion");
                String comments = rs.getString("comments");
                VideoGame p = new VideoGame(id, name, rating, date, completion, comments);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Creates a connection to the SQLite database.
     *
     * @param dbPath the path to the SQLite database
     * @return connection to the database
     */
    protected static Connection getConnectionDAO(String dbPath) {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC + ":" + dbPath);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
