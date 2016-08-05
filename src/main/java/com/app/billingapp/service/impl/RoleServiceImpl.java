/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.RolesDao;
import com.app.billingapp.model.Roles;
import com.app.billingapp.model.User;
import com.app.billingapp.service.RoleService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl extends AbstractDao<Integer, User> implements RoleService{

    @Autowired
    private RolesDao rolesDao;
    @Override
    public Roles findById(int id) {
        return rolesDao.findById(id);
    }

    @Override
    public Set<Roles> fildAllRoles() {
        return rolesDao.fildAllRoles();
    }

    @Override
    public List<Roles> fildAllRole() {
        return rolesDao.fildAllRole();
    }
    
}
