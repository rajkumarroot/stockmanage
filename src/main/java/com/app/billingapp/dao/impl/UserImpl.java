/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.UserDao;
import com.app.billingapp.model.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author node
 */
@Repository("userDao")
public class UserImpl extends AbstractDao<Integer, User> implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public User findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", name) );
        return (User) crit.uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email) );
        return (User) crit.uniqueResult();
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public List<User> findAllUser() {
        List<User> users;
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        users = (List<User>) crit.list();
        return users;
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }
    
}
