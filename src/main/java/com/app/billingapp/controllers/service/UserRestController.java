/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.controllers.service;

import com.app.billingapp.controllers.*;
import com.app.billingapp.model.Roles;
import com.app.billingapp.model.User;
import com.app.billingapp.service.RoleService;
import com.app.billingapp.service.UserService;
import com.app.billingapp.service.impl.UserServiceImpl;
import com.app.billingapp.utilities.HashSecure;
import com.app.billingapp.utilities.JsonResponse;
import com.app.billingapp.validate.UserValidator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author node
 */
@RestController
public class UserRestController {
    @Autowired
    UserValidator userValidator;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    private List<User> userList = new ArrayList<User>();
    
    private HashSecure hashSecure;
    
    @RequestMapping(value = {"/rest/users/list"}, method = RequestMethod.GET)
    public ResponseEntity<List<User>> newrestAdminUser(ModelMap model){
        List<User> usersList = userService.findAllUser();
        if(usersList.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/rest/users/add"}, method = RequestMethod.GET)
    public String newUser(HttpServletRequest request, HttpServletResponse response,ModelMap model){
        User modalData = new User();
        model.addAttribute("editUser",modalData);
        return "users/ajax/add";
    }
    @RequestMapping(value = {"/rest/users/edit/{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id,ModelMap model) throws Exception{
        User modalData = userService.findById(id);
        modalData.setPasswordconfirm(hashSecure.decrypt(modalData.getPassword()));
        modalData.setPassword(hashSecure.decrypt(modalData.getPassword()));
        modalData.setCreatedAt(modalData.getCreatedAt());
        model.addAttribute("editUser",modalData);
        model.addAttribute("editing","edit");
        return "users/ajax/add";
    }
    @ModelAttribute("defaultUser")
    public User defaultUser() {
        User user = new User();
        
        return user;
    }
    @ModelAttribute("roles")
    public Map< Integer, String >  roleList(){
        Map< Integer, String > roles = new HashMap<Integer, String>();
        List<Roles> rolesModel = roleService.fildAllRole();
        for(Roles role: rolesModel){
            roles.put(role.getId(), role.getRoleName());
        }
        return roles;
    }
    @RequestMapping(value = {"/rest/users/addPost"}, method = RequestMethod.POST)
    public @ResponseBody JsonResponse newPostUser(@Valid @ModelAttribute("users") User user,BindingResult result) throws Exception {
        userValidator.validate(user, result);
        JsonResponse res = new JsonResponse();
        if(!result.hasErrors()){
            int extra = (user.getPassword().length()) % 16;
            user.setPassword(hashSecure.encrypt(user.getPassword()));
            userService.saveUser(user);
            res.setStatus("Success");
        }else{
            res.setStatus("FAIL");
            res.setResult(result.getAllErrors());
        }
       return res;
    }
}
