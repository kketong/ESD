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
        List<String> claims = Front.dbm.getClaims(memberId);
        for (String claim : claims) {
            
        }
        return claims;
    }
    
    
}
