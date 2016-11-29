package model;

/**
 *
 * @author Adam Matheson
 */
public class AdminModel {

    // List approvals
    public String getApprovals() {
        return "getApprovals";
        // while db has next entry
        //     getUser.status
        //     if status == APPROVAL
        //         add to list
        //     END
        // END
        // return list
    }

    // Approve members
    public String approvalResult() {
        return "approvalResult";
        // for each in getApprovals() list
        //     set user.status = MEMBER
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
