package com.softserve.tc.diaryclient.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.tc.diaryclient.dao.UserSettingsDAO;
import com.softserve.tc.diaryclient.entity.UserSettings;

@Repository("userSettingsDAO")
public class UserSettingsDAOImpl extends BaseDAOImpl<UserSettings>
        implements UserSettingsDAO {
    public UserSettingsDAOImpl() {
        super(UserSettings.class);
    }
    
    @Transactional
    public UserSettings findByNickName(String nickName) {
        UserSettings element = null;
        
        element = (UserSettings) getEntityManager()
                .createQuery("from " + UserSettings.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .getSingleResult();
        return element;
    }
    
    @Transactional
    public void deleteByNickName(String nickName) {
        getEntityManager().createQuery(
                "delete from " + UserSettings.class.getSimpleName()
                        + " where nick_name= '" + nickName + "'")
                .executeUpdate();
    }
}
