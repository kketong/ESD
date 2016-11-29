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
    public String[] getApprovals() {
        return Front.dbm.retrieveAppliedMembers();
    }

    // Approve members
    public String approvalResult() {
        String[] outstandingApprovals = Front.dbm.retrieveAppliedMembers();

        for (int i = 0; i < outstandingApprovals.length; i++) {
            String parts[] = outstandingApprovals[i].split("<");
            Front.dbm.setMemberandUserStatus(parts[0], "APPROVED");
        }
        return "Success";
    }

    public List<String> listClaims(String id) {
        return Front.dbm.getClaims(id);
    }

    // Approve claims
    public void approveClaims(String id) {
        
    }
    
    public void rejectClaim(String id) {
        
    }
}
