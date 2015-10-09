package com.softserve.tc.diaryclient.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.entity.UserSession;

@Repository("userSessionDAO")
public class UserSessionDAOImpl extends BaseDAOImpl<UserSession>
        implements UserSessionDAO {
    public UserSessionDAOImpl() {
        super(UserSession.class);
    }
    
    @Transactional
    public UserSession findByNickName(String nickName) {
        UserSession element = null;
        
        element = (UserSession) getEntityManager()
                .createQuery("from " + UserSession.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .getSingleResult();
        return element;
    }
    
    @Transactional
    public void deleteByNickName(String nickName) {
        getEntityManager().createQuery(
                "delete from " + UserSession.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .executeUpdate();
                
    }
}
