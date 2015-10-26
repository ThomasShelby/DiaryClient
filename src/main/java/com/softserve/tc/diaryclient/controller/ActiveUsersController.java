package com.softserve.tc.diaryclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.tc.diaryclient.entity.SpringActiveUser;
import com.softserve.tc.diaryclient.service.ActiveUserService;

@Controller
public class ActiveUsersController {
    @Autowired
    private ActiveUserService activeUserService;
    // @Autowired
    // Logout logout;
    
    @RequestMapping(value = "/activeUsers")
    public String todayActiveUsers(Model model) {
        
        List<SpringActiveUser> springActiveUsers =
                activeUserService.getSpringActiveUsers();
                
        model.addAttribute("activeUsers",
                springActiveUsers);
                
        // logout.getSessionInformation();
        
        return "activeUsers";
    }
}
