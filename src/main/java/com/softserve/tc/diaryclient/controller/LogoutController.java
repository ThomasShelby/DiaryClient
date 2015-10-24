package com.softserve.tc.diaryclient.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class LogoutController {
    @Autowired
    LogoutHandler securityContextLogoutHandler;
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        String nickName = request.getUserPrincipal().getName();
        HttpSession httpSession = request.getSession(false);
        String sessionId = httpSession.getId(); 
//        System.out.println(request.getRequestedSessionId());
        securityContextLogoutHandler.logout(request, response, null);
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        port.invalidateSession(nickName, sessionId);
        
        return "redirect:/";
    }

}
