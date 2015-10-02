package com.softserve.tc.diaryclient.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

        @RequestMapping(value = {"/","/welcome**"}, method = RequestMethod.GET)
        public ModelAndView defaultPage() {

                ModelAndView model = new ModelAndView();
                model.addObject("title", "User page");
                model.setViewName("hello");
                return model;

        }

        @RequestMapping(value = "/admin**", method = RequestMethod.GET)
        public ModelAndView adminPage() {

                ModelAndView model = new ModelAndView();
                model.addObject("title", "Admin page");
                model.setViewName("admin");

                return model;

        }

        @RequestMapping(value = "/login", method = RequestMethod.GET)
        public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
                ModelAndView model = new ModelAndView();
                if (error != null) {
                        model.addObject("error", "Invalid username or password!");
                }

                if (logout != null) {
                        model.addObject("msg", "You've been logged out successfully.");
                }
                model.addObject("title", "The Diary Log In");
                model.setViewName("login");

                return model;

        }
        
        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public ModelAndView register(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "registered", required = false) String registered) {
                ModelAndView model = new ModelAndView();
                if (error != null) {
                        model.addObject("error", "Invalid username or password!");
                }

                if (registered != null) {
                        model.addObject("msg", "You've been logged out successfully.");
                }
                model.addObject("title", "The Diary Registration");
                model.setViewName("register");

                return model;

        }
        
        @RequestMapping(value = "/403", method = RequestMethod.GET)
        public ModelAndView accesssDenied() {

                ModelAndView model = new ModelAndView();
                
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (!(auth instanceof AnonymousAuthenticationToken)) {
                        UserDetails userDetail = (UserDetails) auth.getPrincipal();
                        System.out.println(userDetail);
                
                        model.addObject("username", userDetail.getUsername());
                        
                }
                
                model.setViewName("403");
                return model;

        }
}