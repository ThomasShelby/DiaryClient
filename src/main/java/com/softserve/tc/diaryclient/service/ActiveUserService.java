package com.softserve.tc.diaryclient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.softserve.tc.diaryclient.dao.impl.UserDAOImpl;
import com.softserve.tc.diaryclient.entity.SpringActiveUser;

@Service
public class ActiveUserService {
    
    @Autowired
    public SessionRegistry sessionRegistry;
    
    // Getting Avatar, FirstName, SecondName, Email from
    // activeUsersFromDB for user from sessionRegistry
    
    private String getAvatar(String userName,
            List<com.softserve.tc.diary.entity.User> activeUsersFromDB) {
        for (com.softserve.tc.diary.entity.User user : activeUsersFromDB) {
            if (user.getNickName().equals(userName)) {
                return user.getAvatar();
            }
        }
        return null;
    }
    
    private String getFirstName(String userName,
            List<com.softserve.tc.diary.entity.User> activeUsersFromDB) {
        for (com.softserve.tc.diary.entity.User user : activeUsersFromDB) {
            if (user.getNickName().equals(userName)) {
                return user.getFirstName();
            }
        }
        return null;
    }
    
    private String geteMail(String userName,
            List<com.softserve.tc.diary.entity.User> activeUsersFromDB) {
        for (com.softserve.tc.diary.entity.User user : activeUsersFromDB) {
            if (user.getNickName().equals(userName)) {
                return user.geteMail();
            }
        }
        return null;
    }
    
    private String getSecondName(String userName,
            List<com.softserve.tc.diary.entity.User> activeUsersFromDB) {
        for (com.softserve.tc.diary.entity.User user : activeUsersFromDB) {
            if (user.getNickName().equals(userName)) {
                return user.getSecondName();
            }
        }
        return null;
    }
    
    public List<SpringActiveUser> getSpringActiveUsers() {
        
        List<com.softserve.tc.diary.entity.User> activeUsersFromDB =
                UserDAOImpl.getActiveUsers();
                
        System.out.println("begin of getSpringActiveUsers");
        System.out.println("NUMBER_OF_AUTHENTICATED_USERS_NOW_: "
                + sessionRegistry.getAllPrincipals().size());
        List<Object> principals = sessionRegistry.getAllPrincipals();
        List<SpringActiveUser> springActiveUsers =
                new ArrayList<SpringActiveUser>();
                
        for (Object principal : principals) {
            SpringActiveUser springActiveUser = new SpringActiveUser();
            String username = ((User) principal).getUsername();
            springActiveUser.setUsername(username);
            SessionInformation sessionInformation = sessionRegistry
                    .getAllSessions(principal, false).get(0);
            springActiveUser.setSession(sessionInformation.getSessionId());
            
            springActiveUser.setAvatar(getAvatar(username, activeUsersFromDB));
            springActiveUser
                    .setFirstName(getFirstName(username, activeUsersFromDB));
            springActiveUser
                    .setSecondName(getSecondName(username, activeUsersFromDB));
            springActiveUser
                    .seteMail(geteMail(username,
                            activeUsersFromDB));
                            
            springActiveUsers.add(springActiveUser);
        }
        return springActiveUsers;
    }
}
