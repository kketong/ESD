package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseModel {
    public Connection getDatabaseConnection() {
        Connection con = null;
        String dbName = "xyz_assoc";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return con;
    }
}
