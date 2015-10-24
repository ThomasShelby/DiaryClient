package com.softserve.tc.diaryclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.entity.SignupForm;
import com.softserve.tc.diaryclient.validator.SignupValidator;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
@RequestMapping("/signup")
public class SignupController {
	
        @Autowired
        private SignupValidator signupValidator;
        
    	@Autowired
    	DiaryServicePortProvider diaryServicePortProvider;
        
        @RequestMapping(method = RequestMethod.GET)
        public String signup(ModelMap model) {
                SignupForm signupForm = new SignupForm();
                model.put("signupForm", signupForm);
                return "signup";
        }

        @RequestMapping(method = RequestMethod.POST)
        public ModelAndView processSignup(SignupForm signupForm, BindingResult result) {
                signupValidator.validate(signupForm, result);
                ModelAndView model = new ModelAndView();
                if (result.hasErrors()) {
                    model.addObject("title", "Registration failed");
                    model.addObject("msg", "Registration failed");
                    model.addObject("errortext", "FAIL ;(");
                    model.setViewName("error");
                    return model;
                }
                User user = new User();
                user.setNickName(signupForm.getUsername());
                user.setPassword(signupForm.getPassword());
                user.seteMail(signupForm.getEmail());
                user.setRole("USER");
                DiaryService port = diaryServicePortProvider.getPort();
                port.createUser(user);
                authenticateUser(user);
                
                model.setViewName("home");
                return model;
        }
        
        public void authenticateUser(User user) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            user.getNickName(),
                            user.getPassword(),
                            authorities));
        }
}