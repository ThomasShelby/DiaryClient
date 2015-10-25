package com.softserve.tc.diaryclient.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserve.tc.diaryclient.entity.LoginDuration;

public interface LoginDurationDAO extends BaseDAO<LoginDuration> {
    
    LoginDuration findByNickName(String nickName);
    
    void deleteByNickName(String nickName);
    
    Map<Date, Long> getLoginDate();
    
    List<Integer> getAllLoginsCount();
    
    Map<Date, Double> getGenLoginDuration();
    
}
