package com.softserve.tc.diaryclient.logout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class Logout {

    @Autowired
    private SessionRegistry sessionRegistry;
    
    
    public void getSessionInformation(){
        System.out.println("#_#_#_#_#_NUMBER_OF_AUTHENTICATED_USERS_NOW_: "+sessionRegistry.getAllPrincipals().size());
        List<Object> principal =  sessionRegistry.getAllPrincipals();
        
        for(Object userFromSession : principal){
            System.out.println("#_#_#_#_#_ USERNAME : "+((User) userFromSession).getUsername());
            System.out.println("#_#_#_#_#_ USER's AUTHORITUES : "+((User) userFromSession).getAuthorities());
            
            System.out.println("#_#_#_#_#_ SESSIONS_OF_THIS_USER: ");
            for(SessionInformation sessionInformation: sessionRegistry.getAllSessions(userFromSession, false)){
                System.out.println("#_#_#_#_#_ SESSION ID : "+sessionInformation.getSessionId());
                System.out.println("#_#_#_#_#_ LAST REQUEST : "+sessionInformation.getLastRequest());
                System.out.println("#_#_#_#_#_ IS_EXPIRED : "+sessionInformation.isExpired());
            }
            System.out.println("#####################################");
        }
    }
}
