package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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

            while (!resultSet.isLast()) {
                if (username.equals((String) resultSet.getObject(1))) {
                    if (password.equals((String) resultSet.getObject(2))) {
                        return (String) resultSet.getObject(3);
                    } else {
                        return "Invalid Password";
                    }
                }
                
                resultSet.next();
            }
            
            return "User not found";
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

    public String[] retrieveAllEntries(String tableName) {
        String[] entryStrings = new String[1];
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            resultSet.last();
            int numberOfRows = resultSet.getRow();
            resultSet.first();

            entryStrings = new String[numberOfRows];
            for (int i = 0; i < entryStrings.length; i++) {
                entryStrings[i] = "";
                for (int j = 1; j <= numberOfColumns; j++) {
                    entryStrings[i] += resultSet.getObject(j);
                    if (j != numberOfColumns) {
                        entryStrings[i] += "<";
                    }
                }
                resultSet.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return entryStrings;
    }

    public String[] retrieveAppliedMembers() {
        ArrayList<String> entryStrings = new ArrayList<>();
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM members WHERE status='APPLIED'");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            resultSet.first();

            do {
                String tempString = "";
                for (int j = 1; j <= numberOfColumns; j++) {
                    tempString += resultSet.getObject(j);
                    if (j != numberOfColumns) {
                        tempString += "<";
                    }
                }
                entryStrings.add(tempString);
            } while (resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Convert arraylist to string array
        String[] returnArray = new String[entryStrings.size()];
        for (int i = 0; i < entryStrings.size(); i++) {
            returnArray[i] = entryStrings.get(i);
        }

        return returnArray;
    }

    public String retrieveMemberStatus(String username) {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT status FROM members WHERE id='" + username + "'");
            resultSet.first();
            return resultSet.getObject(1) + "";
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String retrieveMemberDOR(String username) {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT dor FROM members WHERE id='" + username + "'");
            resultSet.first();
            return resultSet.getObject(1) + "";
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Boolean setMemberandUserStatus(String username, String status) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE members SET status='" + status + "' WHERE id='" + username + "'");
            ps.executeUpdate();
            ps.close();

            ps = con.prepareStatement("UPDATE users SET status='" + status + "' WHERE id='" + username + "'");
            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean memberActiveForSixMonths(String username) {
        String memberDOR = retrieveMemberDOR(username);
        String[] dorStrings = Date.valueOf(memberDOR).toString().split("-");
        String[] checkDateStrings = Date.valueOf(LocalDate.now()).toString().split("-");
        int yearDiff = Integer.parseInt(checkDateStrings[0]) - Integer.parseInt(dorStrings[0]);
        int monthDiff = Integer.parseInt(checkDateStrings[1]) - Integer.parseInt(dorStrings[1]) + (yearDiff * 12);

        if (monthDiff > 6) {
            return true;
        } else if (monthDiff == 6) {
            if (Integer.parseInt(checkDateStrings[2]) >= Integer.parseInt(dorStrings[2])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean memberMadeLessThanTwoClaims(String username) {
        String[] checkDateStrings = Date.valueOf(LocalDate.now()).toString().split("-");
        String currentYear = checkDateStrings[0];
        int claimCount = 0;

        //Get all claims made my this user, if there are less than 2 return true, else return false
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT date FROM claims WHERE mem_id='" + username + "'");

            if (resultSet.first()) {
                do {
                    if (resultSet.getObject(1).toString().split("-")[0].equals(currentYear)) {
                        claimCount++;
                    }
                } while (resultSet.next());
            }

            if (claimCount < 2) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Boolean createNewClaim(String memberID, String claimDate, String claimDescription, double claimAmount) {
        PreparedStatement ps = null;
        //Auto-increment claimID
        //Set status to APPLIED

        //Can the user make a claim?
        //Check if the user status is approved i.e. are they a member?
        if (retrieveMemberStatus(memberID).equals("APPROVED")) {
            //Check if the account was registered more than 6 months ago
            if (memberActiveForSixMonths(memberID)) {
                System.out.println("Member Active for 6 months: create new claim");
                //Check if they have made less than 2 claims within the current year
                if (memberMadeLessThanTwoClaims(memberID)) {
                    try {
                        ps = con.prepareStatement("INSERT INTO claims VALUES (NULL,'" + memberID
                                + "','" + claimDate + "','" + claimDescription + "','SUBMITTED'," + (claimAmount + "") + ")");
                        ps.executeUpdate();
                        ps.close();
                        System.out.println("1 row added.");
                        return true;
                    } catch (SQLException ex) {
                        System.out.println("Couldn't Insert");
                        Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }

                }
            }
        }
        return false;
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

    public List<Integer> getClaimIds(String memberId) {
        List<Integer> claimIds = new ArrayList();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM claims");
            resultSet.first();

            while (!resultSet.isLast()) {
                if (memberId.equals((String) resultSet.getObject(2))) {
                    claimIds.add((int) resultSet.getObject(1));
                }

                resultSet.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claimIds;
    }

    public List<Integer> getPaymentIds(String memberId) {
        List<Integer> paymentIds = new ArrayList();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM payments");
            resultSet.first();

            while (!resultSet.isLast()) {
                if (memberId.equals((String) resultSet.getObject(2))) {
                    paymentIds.add((int) resultSet.getObject(1));
                }

                resultSet.next();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return paymentIds;
    }
}
