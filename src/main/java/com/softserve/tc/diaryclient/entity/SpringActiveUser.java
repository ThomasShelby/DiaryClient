package com.softserve.tc.diaryclient.entity;

public class SpringActiveUser extends User {
    private String session;
    private String avatar;
    
    public String getSession() {
        return session;
    }
    
    public void setSession(String session) {
        this.session = session;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
