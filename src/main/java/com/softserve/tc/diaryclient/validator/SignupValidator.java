package com.softserve.tc.diaryclient.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.tc.diaryclient.entity.SignupForm;

@Component
public class SignupValidator implements Validator{
        public boolean supports(Class<?> clazz) {
                return SignupForm.class.isAssignableFrom(clazz);
        }

        public void validate(Object target, Errors errors) {
                SignupForm signupForm = (SignupForm) target;
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty", "Please enter a username");
                String username = signupForm.getUsername();
                if ((username.length()) > 20) {
                        errors.rejectValue("username", "username.tooLong", "Please enter value less than or equal 20 characters");
                }
                
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Please provide a password");
                if (!(signupForm.getPassword()).equals(signupForm
                                .getConfirmPassword())) {
                        errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Please enter the same password as above");
                }
                
                if( !EmailValidator.getInstance().isValid( signupForm.getEmail() ) ){
                        errors.rejectValue("email", "email.notValid", "Your email address must be in the format of name@domain.com");
                }
        }
}