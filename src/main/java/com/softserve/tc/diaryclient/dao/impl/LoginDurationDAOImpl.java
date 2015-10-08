package com.softserve.tc.diaryclient.dao.impl;

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
}
