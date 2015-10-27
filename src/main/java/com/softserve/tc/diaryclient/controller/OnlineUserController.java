package com.softserve.tc.diaryclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OnlineUserController {
    
    @Autowired
    private SessionRegistry sessionRegistry;
    
    @RequestMapping(value = "/authenticated", method = RequestMethod.GET)
    public @ResponseBody int getSessionInformation() {
        
        return sessionRegistry.getAllPrincipals().size();
    }
}
