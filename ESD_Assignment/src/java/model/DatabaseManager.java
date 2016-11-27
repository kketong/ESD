package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private Connection con = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String connectionName;

    public DatabaseManager(String dbName) {
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
            connectionName = con.getCatalog();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean createNewUser() {
        //Successfully creater a new user
        return true;
    }

    public User getUser(String username) {
        try {
            String query = "SELECT * FROM account";

            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {

        }
        User tempUser = new User(username, username, username, username, username, username, username, 0, 0, 0);
        return tempUser;
    }

    public User getClaim(String username) {
        return null;
    }

    public User getPayment(String username) {
        return null;
    }

    public String getConnectionName() {
        return connectionName;
    }
}
