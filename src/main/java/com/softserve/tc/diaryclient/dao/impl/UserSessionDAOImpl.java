package com.softserve.tc.diaryclient.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public Map<Date, Long> getSessionDuration() {

        List<Object[]> list = getEntityManager()
                .createQuery("select CAST(startSession as date),SUM((((DATE_PART('day', endSession - startSession) * 24 +DATE_PART('hour', endSession - startSession)) * 60 + DATE_PART('minute', endSession - startSession)) * 60 + DATE_PART('second', endSession - startSession)))  as duration from UserSession group by startSession order by startSession")
                .getResultList();
        Map<Date, Long> sessionDuration = new TreeMap<Date, Long>();
        for (Object[] row : list) {
            Date startday = (Date) row[0];
            Long duration = (Long) row[1];
            sessionDuration.put(startday, duration);
        }
        return sessionDuration;
    }
}