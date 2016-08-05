/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.dao.inter;

import com.app.billingapp.model.User;
import java.util.List;

/**
 *
 * @author node
 */
public interface UserDao {
    User findById(int id);
     
    User findByName(String name);
    
    User findByEmail(String email);
    
    void saveUser(User user);
    
    List<User> findAllUser(); 
    
    void updateUser(User user);
}
