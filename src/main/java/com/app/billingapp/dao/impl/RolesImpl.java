/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.RolesDao;
import com.app.billingapp.model.Roles;
import com.app.billingapp.model.User;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("rolesDao")
public class RolesImpl extends AbstractDao<Integer, Roles> implements RolesDao{

    @Override
    public Roles findById(int id) {
        return getByKey(id);
    }

    @Override
    public Set<Roles> fildAllRoles() {
        Criteria crit = createEntityCriteria();
        //crit.add(Restrictions.eq("username", name) );
        return (Set<Roles>) (Roles) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Roles> fildAllRole() {
        List<Roles> roles;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        roles = (List<Roles>) crit.list();
        return roles;
    }
    
}
