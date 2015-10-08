package com.softserve.tc.diaryclient.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserve.tc.diaryclient.entity.LoginDuration;

public interface LoginDurationDAO extends BaseDAO<LoginDuration> {

    public Map<Integer, Long> getLoginDate() ;

    public List<Integer> getAllLoginsCount() ;
    
    public Map<Integer, Double> getGenLoginDuration();
}
