/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.service.impl;

import com.app.billingapp.dao.AbstractDao;
import com.app.billingapp.dao.inter.UserDao;
import com.app.billingapp.model.User;
import com.app.billingapp.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author node
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractDao<Integer, User> implements UserService{

    @Autowired
    private UserDao dao;
    
    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public User findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public List<User> findAllUser() {
        return dao.findAllUser();
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
    
}
