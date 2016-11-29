/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Front;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edwin
 */
public class MemberModel {
    
    public List getClaims(String memberId) {
        List<Integer> claimIds = Front.dbm.getClaimIds(memberId);
        List claims = new ArrayList();
        for (int id : claimIds) {
            claims.add(Front.dbm.getClaimById(Integer.toString(id)));
        }
        return claims;
    }
    
    
}
