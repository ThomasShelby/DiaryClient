package com.softserve.tc.diaryclient.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.LoginDurationDAO;
import com.softserve.tc.diaryclient.entity.LoginDuration;

@Repository("loginDurationDAO")
public class LoginDurationDAOImpl extends BaseDAOImpl<LoginDuration>
        implements LoginDurationDAO {
    public LoginDurationDAOImpl() {
        super(LoginDuration.class);
    }
    
    @Transactional
    public LoginDuration findByNickName(String nickName) {
        LoginDuration element = null;
        
        element = (LoginDuration) getEntityManager()
                .createQuery("from " + LoginDuration.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .getSingleResult();
        return element;
    }
    
    @Transactional
    public void deleteByNickName(String nickName) {
        getEntityManager().createQuery(
                "delete from " + LoginDuration.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .executeUpdate();
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public Map<Date, Long> getLoginDate(int month) {
        List<Object[]> list = getEntityManager()
                .createQuery(
                        "Select CAST(loginDate as date), COUNT(*) FROM LoginDuration WHERE DATE(loginDate) BETWEEN '2015-"+ month +"-01 00:00:00.000000' AND '2015-"+ month +"-31 23:59:59.999999' GROUP BY CAST(loginDate as date) ORDER BY CAST(loginDate as date)")
                .getResultList();
        Map<Date, Long> statsPerDate =new TreeMap<Date, Long>();
        for (Object[] row : list) {
            Date logindate = (Date) row[0];
            Long count = (Long) row[1];
            statsPerDate.put(logindate, count);
        }
        
        return statsPerDate;
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Integer> getAllLoginsCount() {
        List<Integer> list = getEntityManager()
                .createQuery("Select COUNT(*) FROM LoginDuration")
                .getResultList();
        return list;
    }
    
    @Transactional
    @SuppressWarnings("unchecked")
    public Map<Date, Double> getGenLoginDuration() {
        
        List<Object[]> list = getEntityManager()
                .createQuery(
                        "Select CAST(loginDate as date), SUM(duration) FROM LoginDuration GROUP BY CAST(loginDate as date) ORDER BY CAST(loginDate as date)")
                .getResultList();
        Map<Date, Double> logDuration =
                new TreeMap<Date, Double>();
        for (Object[] row : list) {
            Date logindate = (Date) row[0];
            Double duration = (Double) row[1];
            logDuration.put(logindate, duration);
        }
        
        return logDuration;
    }
    
}
