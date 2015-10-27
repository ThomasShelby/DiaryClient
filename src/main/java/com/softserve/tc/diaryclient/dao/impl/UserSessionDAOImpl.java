package com.softserve.tc.diaryclient.dao.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
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
