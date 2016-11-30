package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberModel {
    public String retrieveMemberPassword(String username) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT password FROM users WHERE id ='" + username + "'");
            if (resultSet.first()) {
                return (String) resultSet.getObject(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public double retrieveMemberBalance(String username) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT balance FROM members WHERE id ='" + username + "'");
            if (resultSet.first()) {
                return Double.parseDouble(resultSet.getObject(1) + "");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<String> getClaims(String memberId) {
        List<String> claims = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM claims WHERE mem_id='" + memberId + "'");
            resultSet.first();
            int numberOfColumns = resultSet.getMetaData().getColumnCount();
            do {
                String claimString = "";
                for (int j = 1; j <= numberOfColumns; j++) {
                    claimString += resultSet.getObject(j);
                    if (j != numberOfColumns) {
                        claimString += "<";
                    }
                }

                claims.add(claimString);
            } while (resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claims;
    }
    
    public List<String> getPayments(String memberId) {
        List<String> payments = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM payments WHERE mem_id='" + memberId + "'");
            resultSet.first();
            int numberOfColumns = resultSet.getMetaData().getColumnCount();
            do {
                String paymentString = "";
                for (int j = 1; j <= numberOfColumns; j++) {
                    paymentString += resultSet.getObject(j);
                    if (j != numberOfColumns) {
                        paymentString += "<";
                    }
                }

                payments.add(paymentString);
            } while (resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return payments;
    }
}
