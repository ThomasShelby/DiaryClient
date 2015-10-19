package com.softserve.tc.diaryclient.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.entity.UserSession;

@Repository("userSessionDAO")
public class UserSessionDAOImpl extends BaseDAOImpl<UserSession>implements UserSessionDAO {
    public UserSessionDAOImpl() {
        super(UserSession.class);
    }

    @Transactional
    public UserSession findByNickName(String nickName) {
        UserSession element = null;

        element = (UserSession) getEntityManager()
                .createQuery("from " + UserSession.class.getSimpleName() + " where nick_name= '" + nickName + "'")
                .getSingleResult();
        return element;
    }

    @Transactional
    public void deleteByNickName(String nickName) {
        getEntityManager()
                .createQuery(
                        "delete from " + UserSession.class.getSimpleName() + " where nick_name= '" + nickName + "'")
                .executeUpdate();

    }

    @Transactional
    @SuppressWarnings("unchecked")
    public Map<Integer, Integer> getSessionDuration() {

        List<Object[]> list = getEntityManager()
                .createQuery(
                        "select extract(day from endSession - startSession) as duration, extract(day from startSession) as startday from UserSession order by startday")
                .getResultList();
        Map<Integer, Integer> sessionDuration = new HashMap<Integer, Integer>(list.size());
        for (Object[] row : list) {
            Integer duration = (Integer) row[0];
            Integer startday = (Integer) row[1];
            sessionDuration.put(startday, duration);
        }
        return sessionDuration;
    }

}
