package model;

import controller.Front;
import java.util.Arrays;
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
}
