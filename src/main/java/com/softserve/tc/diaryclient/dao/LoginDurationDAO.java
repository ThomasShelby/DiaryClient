package com.softserve.tc.diaryclient.dao;

import java.util.List;
import java.util.Map;

import com.softserve.tc.diaryclient.entity.LoginDuration;

public interface LoginDurationDAO extends BaseDAO<LoginDuration> {
    
    LoginDuration findByNickName(String nickName);
    
    void deleteByNickName(String nickName);
    
    public Map<Integer, Long> getLoginDate();
    
    public List<Integer> getAllLoginsCount();
    
    public Map<Integer, Double> getGenLoginDuration();
    
}
