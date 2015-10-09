package com.softserve.tc.diaryclient.dao;

import com.softserve.tc.diaryclient.entity.UserSettings;

public interface UserSettingsDAO extends BaseDAO<UserSettings> {
    UserSettings findByNickName(String nickName);
            
    void deleteByNickName(String nickName);
}
