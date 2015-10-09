package com.softserve.tc.diaryclient.dao;

import com.softserve.tc.diaryclient.entity.UserSession;

public interface UserSessionDAO extends BaseDAO<UserSession> {
    UserSession findByNickName(String nickName);
            
    void deleteByNickName(String nickName);
}
