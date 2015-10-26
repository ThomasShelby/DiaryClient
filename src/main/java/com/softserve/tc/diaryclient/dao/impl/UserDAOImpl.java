package com.softserve.tc.diaryclient.dao.impl;

import java.util.List;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.entity.User;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

public class UserDAOImpl {
    
    public static User findByUserName(String username) {
        DiaryService port = DiaryServicePortProvider.getPort();
        com.softserve.tc.diary.entity.User user =
                port.getUserByNickName(username);
        return new User(user.getNickName(), user.getPassword(), user.getRole());
    }
    
    public static void create(final com.softserve.tc.diary.entity.User user) {
        DiaryService port = DiaryServicePortProvider.getPort();
        port.createUser(user);
    }
    
    public static List<com.softserve.tc.diary.entity.User> getActiveUsers() {
        
        DiaryService port = DiaryServicePortProvider.getPort();
        
        List<com.softserve.tc.diary.entity.User> diaryUsers =
                port.getActiveUsers();
                
        return diaryUsers;
    }
}
