package model;

import java.sql.*;
import java.time.LocalDate;
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

    public String registerNewMember(String name, String address, String dob) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + connectionName, "root", "");
            statement = con.createStatement();

            String[] nameSplit = name.split(" ");
            String id = nameSplit[0].charAt(0) + "-" + nameSplit[1];
            String dor = Date.valueOf(LocalDate.now()).toString();

            String[] passwordSplit = dob.split("-");
            String password = passwordSplit[2] + passwordSplit[1] + passwordSplit[0].substring(2, 4);

            //Insert into members table
            String[] str = new String[]{id, name, address, dob, dor, "APPLIED", "0"};
            insert("members", str);
            //Insert into users table

            str = new String[]{id, password, "APPLIED"};
            insert("users", str);

            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String verifyCredentials(String username, String password) {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            resultSet.first();

            while (true) {
                if (username.equals((String) resultSet.getObject(1))) {
                    if (password.equals((String) resultSet.getObject(2))) {
                        return (String) resultSet.getObject(3);
                    } else {
                        return "Invalid Password";
                    }
                }

                if (resultSet.isLast()) {
                    return "User not found";
                } else {
                    resultSet.next();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void insert(String tableName, String[] str) {
        PreparedStatement ps = null;
        String valuesString = "";

        for (int i = 0; i < str.length; i++) {
            valuesString += "?";
            if (i != str.length - 1) {
                valuesString += ",";
            }
        }

        try {
            ps = con.prepareStatement("INSERT INTO " + tableName + " VALUES (" + valuesString + ")", PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < str.length; i++) {
                ps.setString(i + 1, str[i]);
            }
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] retrieveUsers() {
        String[] userStrings = new String[1];
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            resultSet.last();
            int numberOfRows = resultSet.getRow();
            resultSet.first();

            userStrings = new String[numberOfRows];
            for (int i = 0; i < userStrings.length; i++) {
                userStrings[i] = "";
                for (int j = 1; j <= numberOfColumns; j++) {
                    userStrings[i] += resultSet.getObject(j);
                    if (j != numberOfColumns) {
                        userStrings[i] += ".";
                    }
                }
                resultSet.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userStrings;
    }

    //Status user, set member status and user status, 

//    public User getUser(String username) {
//        User tempUser = new User();
//        String tempString = "";
//
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + connectionName, "root", "");
//            statement = con.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM users");
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int numberOfColumns = metaData.getColumnCount();
//
//            Boolean isFound = false;
//            resultSet.first();
//            while (!isFound) {
//                tempUser.setUserID(resultSet.getObject(1) + "");
//                tempUser.setUserPassword(resultSet.getObject(2) + "");
//                tempUser.setUserStatus(resultSet.getObject(3) + "");
//                if (tempUser.getUserID().equals(username)) {
//                    isFound = true;
//                }
//                if (resultSet.isLast()) {
//                    break;
//                } else {
//                    resultSet.next();
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return tempUser;
//    }

    public String getConnectionName() {
        return connectionName;
    }
}
