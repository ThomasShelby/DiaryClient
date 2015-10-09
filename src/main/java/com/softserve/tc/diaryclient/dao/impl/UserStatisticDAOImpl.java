package com.softserve.tc.diaryclient.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.UserStatisticDAO;
import com.softserve.tc.diaryclient.entity.UserStatistic;

@Repository("userStatisticDAO")
public class UserStatisticDAOImpl extends BaseDAOImpl<UserStatistic>
        implements UserStatisticDAO {
        
    public UserStatisticDAOImpl() {
        super(UserStatistic.class);
    }
    
    @Transactional
    public UserStatistic findByNickName(String nickName) {
        UserStatistic element = null;
        
        element = (UserStatistic) getEntityManager()
                .createQuery("from " + UserStatistic.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .getSingleResult();
        return element;
    }
    
    @Transactional
    public void deleteByNickName(String nickName) {
        getEntityManager().createQuery(
                "delete from " + UserStatistic.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .executeUpdate();
    }
}
