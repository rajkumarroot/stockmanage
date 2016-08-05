/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.inter;

import com.app.billingapp.model.Roles;
import java.util.List;
import java.util.Set;

/**
 *
 * @author node
 */
public interface RolesDao {
    Roles findById(int id);
    Set<Roles> fildAllRoles();
    List<Roles> fildAllRole();
}
