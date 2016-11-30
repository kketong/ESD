package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreationModel {
    public String registerNewMember(String name, String address, String dob) {
        //Set name to lower case so that the is is all lower case
        name = name.toLowerCase();
        String[] nameSplit = name.split(" ");
        String id = nameSplit[0].charAt(0) + "-" + nameSplit[1];
        String dor = Date.valueOf(LocalDate.now()).toString();

        String[] passwordSplit = dob.split("-");
        //Reverse date of birth
        dob = passwordSplit[2] + "-" + passwordSplit[1] + "-" + passwordSplit[0];
        //Generate password from date of birth
        String password = passwordSplit[0] + passwordSplit[1] + passwordSplit[2].substring(2, 4);

        //Capitalise each word in name
        name = "";
        for (int i = 0; i < nameSplit.length; i++) {
            name += nameSplit[i].substring(0, 1).toUpperCase() + nameSplit[i].substring(1, nameSplit[i].length());
            if (i != nameSplit.length - 1) {
                name += " ";
            }
        }
        
        //Insert into members table
        String[] str = new String[]{id, name, address, dob, dor, "APPLIED", "10"};
        insert("members", str);
        //Insert into users table

        str = new String[]{id, password, "APPLIED"};
        insert("users", str);

        return id;
    }
    
    public String verifyCredentials(String username, String password) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE id='" + username + "' AND password='" + password + "'");
            resultSet.first();
            if (resultSet.wasNull()) {
                return "Invalid Credentials.";
            } else {
                return (String) resultSet.getObject(3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Invalid Credentials.";
    }

    private void insert(String tableName, String[] str) {
        Connection con = new DatabaseModel().getDatabaseConnection();
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
            Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Boolean createNewClaim(String memberID, String claimDescription, double claimAmount) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        PreparedStatement ps = null;
        String claimDate = Date.valueOf(LocalDate.now()) + "";

        //Can the user make a claim?
        //Check if the user status is approved i.e. are they a member?
        if (retrieveMemberStatus(memberID).equals("APPROVED")) {
            //Check if the account was registered more than 6 months ago
            if (memberActiveForSixMonths(memberID)) {
                System.out.println("Member Active for 6 months: create new claim");
                //Check if they have made less than 2 claims within the current year
                //if (memberMadeLessThanTwoClaims(memberID)) {
                try {
                    ps = con.prepareStatement("INSERT INTO claims VALUES (NULL,'" + memberID
                            + "','" + claimDate + "','" + claimDescription + "','SUBMITTED'," + claimAmount + ")");
                    ps.executeUpdate();
                    ps.close();
                    System.out.println("1 row added to claims.");
                    return true;
                } catch (SQLException ex) {
                    System.out.println("Couldn't Insert into claims");
                    Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }

                //}
            }
        }
        return false;
    }
    
    public Boolean createNewPayment(String memberID, double paymentAmount) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        String paymentType = "FEE";
        PreparedStatement ps = null;
        String paymentDateTime = (LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8));

        try {
            ps = con.prepareStatement("INSERT INTO payments VALUES (NULL,'" + memberID
                    + "','" + paymentType + "','" + paymentAmount + "','" + paymentDateTime + "')");
            ps.executeUpdate();
            ps.close();
            System.out.println("1 row added to payments.");
            return true;
        } catch (SQLException ex) {
            System.out.println("Couldn't Insert into payments");
            Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String retrieveMemberStatus(String username) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT status FROM members WHERE id='" + username + "'");
            resultSet.first();
            return resultSet.getObject(1) + "";
        } catch (SQLException ex) {
            Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private Boolean memberActiveForSixMonths(String username) {
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
    
    private String retrieveMemberDOR(String username) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT dor FROM members WHERE id='" + username + "'");
            resultSet.first();
            return resultSet.getObject(1) + "";
        } catch (SQLException ex) {
            Logger.getLogger(CreationModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
