package com.softserve.tc.diaryclient.dao;

import com.softserve.tc.diaryclient.entity.UserStatistic;

public interface UserStatisticDAO extends BaseDAO<UserStatistic> {
    UserStatistic findByNickName(String nickName);
            
    void deleteByNickName(String nickName);
}
