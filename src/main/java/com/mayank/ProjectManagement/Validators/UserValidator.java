package com.mayank.ProjectManagement.Validators;

import com.mayank.ProjectManagement.Entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if(user.getPassword()!=null && user.getPassword().length()<6){
            errors.rejectValue("password" , "length" , "Length should be at least 6 character long.");
        }
        if(user.getConfirmPassword()!=null && !user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirm password" , "match" , "password should match");
        }

    }
}
