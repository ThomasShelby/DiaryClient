package com.softserve.tc.diaryclient.dao.impl;


import java.util.Date;
import java.util.HashMap;
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
    public UserSession findBySession(String session) {
        UserSession element = null;

        element = (UserSession) getEntityManager()
                .createQuery("from " + UserSession.class.getSimpleName() + " where session_number= '" + session + "'")
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
    public Map<String, Integer> getCountActiveusers(String mapping, Date selectedDate) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	List<Object[]> list = null;
    	if (mapping.equals("hourly")) {
    		list = getEntityManager()
                  .createQuery("SELECT EXTRACT(HOUR FROM startSession), COUNT(*) FROM UserSession "
                  + "WHERE DATE(startSession) = DATE('" + selectedDate + "') GROUP BY EXTRACT(HOUR FROM startSession)"
                  + " order by EXTRACT(HOUR FROM startSession)").getResultList();
    	}
    	else if (mapping.equals("weekly")) {
    		list = getEntityManager().createQuery(
    				"SELECT EXTRACT(DOW FROM startSession), COUNT(*) FROM UserSession"
    				+ " WHERE EXTRACT(WEEK FROM DATE('"+ selectedDate +"')) = EXTRACT(WEEK FROM startSession)"
    				+ " group by EXTRACT(DOW FROM startSession)").getResultList();
    	}
    	else if (mapping.equals("monthly")) {
    		list = getEntityManager().createQuery(
    				"SELECT EXTRACT(DAY FROM startSession), COUNT(*) FROM UserSession WHERE "
    				+ "EXTRACT(MONTH from DATE('"+ selectedDate +"')) = EXTRACT(MONTH from startSession) "
    				+ "GROUP BY EXTRACT(DAY FROM startSession)").getResultList();
    	}
		for (Object[] row : list) {
            String key = String.valueOf(row[0]);
            Integer value = Integer.valueOf(String.valueOf(row[1]));
            map.put(key, value);
          }
    	
    	return map;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public Map<Date, Long> getSessionDuration() {

        List<Object[]> list = getEntityManager()
                .createQuery("select CAST(startSession as date),SUM((((DATE_PART('day', endSession - startSession) * 24 +DATE_PART('hour', endSession - startSession)) * 60 + DATE_PART('minute', endSession - startSession)) * 60 + DATE_PART('second', endSession - startSession)))  as duration from UserSession group by CAST(startSession as date) order by CAST(startSession as date)")
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