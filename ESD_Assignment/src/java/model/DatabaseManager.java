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
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
            connectionName = dbName;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean createNewUser() {
        //Successfully created a new user
        return true;
    }

    public User getUser(String username) {
        User tempUser = new User();
        String tempString = "";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + connectionName, "root", "");
            connectionName = con.getCatalog();
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            Boolean isFound = false;
            resultSet.first();
            while (!isFound) {
                tempUser.setUserID(resultSet.getObject(1) + "");
                tempUser.setUserPassword(resultSet.getObject(2) + "");
                tempUser.setUserStatus(resultSet.getObject(3) + "");
                if (tempUser.getUserID().equals(username)) {
                    isFound = true;
                }
                if (resultSet.isLast()) {
                    break;
                } else {
                    resultSet.next();
                }
            }

            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempUser;
    }

    public Member getClaim(String username) {
        return null;
    }

    public Member getPayment(String username) {
        return null;
    }

    public String getConnectionName() {
        return connectionName;
    }
}
