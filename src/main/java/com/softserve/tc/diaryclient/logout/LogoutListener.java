package com.softserve.tc.diaryclient.logout;

import java.security.Principal;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;


public class LogoutListener implements HttpSessionListener {
    
    private DiaryService port;
    
    @Autowired
    public LogoutListener(DiaryServicePortProvider diaryServicePortProvider) {
        port = diaryServicePortProvider.getPort();
    }
    public LogoutListener(){}
    
    @Autowired
    private SessionRegistry sessionRegistry;
    

    public void sessionCreated(HttpSessionEvent arg0) {
        
    }

    public void sessionDestroyed(HttpSessionEvent event) {

            String sessionId = event.getSession().getId(); 
            
            Principal principal = (Principal) sessionRegistry.getSessionInformation(sessionId).getPrincipal();
            
            String nickName = ((User) principal).getUsername();
            
            port.invalidateSession(nickName, sessionId);
            System.out.println("nickName///////////////////////////////////////////////// "+nickName);
            System.out.println("sessionId///////////////////////////////////////////////// "+sessionId);
        }   
    }