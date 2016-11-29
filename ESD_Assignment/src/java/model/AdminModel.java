package model;

import controller.Front;

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
    public void approvalResult() {
        String[] outstandingApprovals = Front.dbm.retrieveAppliedMembers();
        
        for (int i = 0; i < outstandingApprovals.length; i++) {
            String parts[] = outstandingApprovals[i].split("<");
            Front.dbm.setMemberandUserStatus(parts[0], "APPROVED");
        }
    }

    // Approve claims
    public String claimResult() {
        return "claimResult";
        // while db has next entry
        //     getClaim.status
        //     if claimCount < 2
        //         approve
        //     else
        //         reject
        //     END
        // END
    }
}
