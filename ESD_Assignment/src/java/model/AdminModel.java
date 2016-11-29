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

    // Approve members
    public String approvalResult() {
        List<String> outstandingApprovals = Front.dbm.retrieveAppliedMembers();

        for (String approval : outstandingApprovals) {
            String parts[] = approval.split("<");
            Front.dbm.setMemberandUserStatus(parts[0], "APPROVED");
        }
        return "Success";
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
