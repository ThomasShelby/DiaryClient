package com.softserve.tc.diaryclient.dao;


import java.util.Date;
import java.util.Map;

import com.softserve.tc.diaryclient.entity.UserSession;

public interface UserSessionDAO extends BaseDAO<UserSession> {
    UserSession findByNickName(String nickName);
    
    public Map<String, Integer> getCountActiveusers(String mapping, Date selectedDate);
    
    public UserSession findBySession(String session);
            
    void deleteByNickName(String nickName);

    Map<Date, Long> getSessionDuration();
}