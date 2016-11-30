package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam Matheson
 */
public class AdminModel {

    // List approvals
    public List<String> getApprovals() {
        ArrayList<String> entryStrings = new ArrayList<>();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
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

        return entryStrings;
    }

    public List<String> listPayments(String mem_id) {
        List<String> payments = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM payments WHERE mem_id='" + mem_id + "'");
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return payments;
    }
    
    // Approve members
    public void approvalResult(String id) {
        setMemberandUserStatus(id, "APPROVED");
    }

    public List<String> listClaims(String id) {
        List<String> claims = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM claims WHERE mem_id='" + id + "'");
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return claims;
    }

    // Approve claims
    public void approveClaim(String id) {
        setClaimStatus(id, "APPROVED");
    }
    
    public void rejectClaim(String id) {
        setClaimStatus(id, "REJECTED");
    }
    
    public void endOfYearCharge() {
        List<String> statuses = retrieveAllMemberStatus();
        List<String> notApplied = getNotApplied();
        
        List<Float> returned = getAllClaims();
        float count = 0;
        
        for (Float amount : returned) {
            count += amount;
        }
        
        float chargePerPerson = (float) count / statuses.size();
        
        chargeAll(chargePerPerson);
        
        for (String username : notApplied) {
            setMemberandUserStatus(username, "SUSPENDED");
        }
    }
    
    private void setClaimStatus(String mem_id, String status) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE claims SET status='" + status + "' WHERE id='" + mem_id + "'");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<String> retrieveAllMemberStatus() {
        List<String> statuses = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT status FROM members WHERE status<>'APPLIED'");
            
            if (resultSet.first()) {
                do {
                    statuses.add((String) resultSet.getObject(1));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return statuses;
    }
    
    private List<String> getNotApplied() {
        List<String> statuses = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM members WHERE status<>'APPLIED'");
            
            if (resultSet.first()) {
                do {
                    statuses.add((String) resultSet.getObject(1));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return statuses;
    }
    
    private List<Float> getAllClaims() {
        List<Float> claims = new ArrayList();
        Connection con = new DatabaseModel().getDatabaseConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT amount FROM claims");

            if (resultSet.first()) {
                do {
                    claims.add((Float) resultSet.getObject(1));
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claims;
    }
    
    private void chargeAll(float charge) {
        Connection con = new DatabaseModel().getDatabaseConnection();
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement("UPDATE members SET balance=balance+'" + charge + "' WHERE status<>'APPLIED'");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Boolean setMemberandUserStatus(String username, String status) {
        Connection con = new DatabaseModel().getDatabaseConnection();
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
}
