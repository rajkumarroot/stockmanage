/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.model.enumtype;

/**
 *
 * @author node
 */
public enum RolesType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");
     
    String roleType;
     
    private RolesType(String userProfileType){
        this.roleType = userProfileType;
    }
     
    public String getRolesType(){
        return roleType;
    }
}
