package com.softserve.tc.diaryclient.dao;

import com.softserve.tc.diaryclient.entity.LoginDuration;

public interface LoginDurationDAO extends BaseDAO<LoginDuration> {
    LoginDuration findByNickName(String nickName);
            
    void deleteByNickName(String nickName);
    
}
