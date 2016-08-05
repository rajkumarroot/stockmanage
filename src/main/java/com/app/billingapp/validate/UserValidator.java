/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.billingapp.validate;

import com.app.billingapp.model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author rajkumarr
 */
@Component
public class UserValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher; 
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error-name", "*Full name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error-username", "*Username is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error-password", "*Password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordconfirm", "error-passwordconfirm", "*Confirm password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error-email", "*Email is required.");
        if (!(user.getEmail() != null && user.getEmail().isEmpty())) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "error-email",
                        "*Invalid email address");
            }
        }
        
        if ((!(user.getPassword()!= null && user.getPassword().isEmpty())) && (!(user.getPasswordconfirm()!= null && user.getPasswordconfirm().isEmpty()))) {
            
            if (!user.getPassword().matches(user.getPasswordconfirm())) {
                errors.rejectValue("passwordconfirm", "error-passwordconfirm",
                        "*Password not matching");
            }
        }
        
          
        if (user.getRoleId().getId()==0) {
            errors.rejectValue("roleId.id", "error-roleId",
                    "*Role is required");
        }
        
    }

}
