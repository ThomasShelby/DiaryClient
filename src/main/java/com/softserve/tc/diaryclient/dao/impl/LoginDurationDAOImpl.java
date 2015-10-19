package com.softserve.tc.diaryclient.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Integer, Long> getLoginDate() {
        List<Object[]> list = getEntityManager()
                .createQuery(
                        "Select EXTRACT(DAY FROM loginDate), COUNT(*) FROM LoginDuration "
                                + "GROUP BY loginDate ORDER BY loginDate")
                .getResultList();
        Map<Integer, Long> statsPerDate =
                new HashMap<Integer, Long>(list.size());
        for (Object[] row : list) {
            Integer logindate = (Integer) row[0];
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
    public Map<Integer, Double> getGenLoginDuration() {
        
        List<Object[]> list = getEntityManager()
                .createQuery(
                        "Select EXTRACT(DAY FROM loginDate), SUM(duration) FROM LoginDuration GROUP BY loginDate ORDER BY loginDate")
                .getResultList();
        Map<Integer, Double> logDuration =
                new HashMap<Integer, Double>(list.size());
        for (Object[] row : list) {
            Integer logindate = (Integer) row[0];
            Double duration = (Double) row[1];
            logDuration.put(logindate, duration);
        }
        
        return logDuration;
    }
    
}
