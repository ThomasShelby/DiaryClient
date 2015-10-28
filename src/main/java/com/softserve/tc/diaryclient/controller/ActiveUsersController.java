package com.softserve.tc.diaryclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diaryclient.entity.SpringActiveUser;
import com.softserve.tc.diaryclient.service.ActiveUserService;

@Controller
public class ActiveUsersController {
    @Autowired
    private ActiveUserService activeUserService;
    
    
    @RequestMapping(value = "/activeUsers")
    public String todayActiveUsers(Model model) {
        
        List<SpringActiveUser> springActiveUsers =
                activeUserService.getSpringActiveUsers();
                
        model.addAttribute("activeUsers",
                springActiveUsers);
        
        return "activeUsers";
    }
    
    @RequestMapping(value = "/activeUsers1")
    public @ResponseBody String todayActiveUsers1(Model model) {
        
        List<SpringActiveUser> springActiveUsers =
                activeUserService.getSpringActiveUsers();
                
        return new Gson().toJson(springActiveUsers);
    }
}
