package com.softserve.tc.diaryclient.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class LoginDuration {
	
	@Id
	private String uuid; 

	@NotNull
	@Column(name="nick_name")
	private String nickName;
	
	@Column(name="login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	
	@Column(name="duration")
	private Double duration;
	
	public LoginDuration() {
		super();
	}
	
	public LoginDuration(String nickName, Date loginDate, Double duration) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
		this.loginDate = loginDate;
		this.duration = duration;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result
                + ((loginDate == null) ? 0 : loginDate.hashCode());
        result = prime * result
                + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginDuration other = (LoginDuration) obj;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (loginDate == null) {
            if (other.loginDate != null)
                return false;
        } else if (!loginDate.equals(other.loginDate))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginDuration [uuid=" + uuid + ", nickName=" + nickName + ", loginDate=" + loginDate + ", duration="
				+ duration + "]\n";
	}

}
