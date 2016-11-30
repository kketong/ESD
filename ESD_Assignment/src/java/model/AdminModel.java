package model;

import controller.Front;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Matheson
 */
public class AdminModel {

    // List approvals
    public List<String> getApprovals() {
        return Front.dbm.retrieveAppliedMembers();
    }

    public List<String> listPayments(String mem_id) {
        return Front.dbm.getPayments(mem_id);
    }
    
    // Approve members
    public void approvalResult(String id) {
        Front.dbm.setMemberandUserStatus(id, "APPROVED");
    }

    public List<String> listClaims(String id) {
        return Front.dbm.getClaims(id);
    }

    // Approve claims
    public void approveClaim(String id) {
        Front.dbm.setClaimStatus(id, "APPROVED");
    }
    
    public void rejectClaim(String id) {
        Front.dbm.setClaimStatus(id, "REJECTED");
    }
    
    public void endOfYearCharge() {
        List<String> statuses = Front.dbm.retrieveAllMemberStatus();
        List<String> notApplied = Front.dbm.getNotApplied();
        
        List<Float> returned = Front.dbm.getAllClaims();
        float count = 0;
        
        for (Float amount : returned) {
            count += amount;
        }
        
        float chargePerPerson = (float) count / statuses.size();
        
        Front.dbm.chargeAll(chargePerPerson);
        
        for (String username : notApplied) {
            Front.dbm.setMemberandUserStatus(username, "SUSPENDED");
        }
    }
}
