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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
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
        User tempUser = new User();
        String tempString = "";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
            connectionName = con.getCatalog();
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM account");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            resultSet.first();
            for (int i = 1; i <= numberOfColumns; i++) {
                tempUser.setColumn(i, resultSet.getObject(i));
            }

            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
