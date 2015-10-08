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
public class UserStatistic {

	@Id
	private String uuid; 

	@NotNull
	@Column(name="nick_name")
	private String nickName;
	
	@Column(name="number_of_records")
	private Integer numberOfRecords;
	
	@Column(name="number_of_logins")
	private Integer numberOfLogins;
	
	@Column(name="last_records")
	private String lastRecords;
	
	@Column(name="last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
	public UserStatistic() {
		super();
	}
	
	public UserStatistic(String nickName, Integer numberOfRecords, Integer numberOfLogins,
			String lastRecords, Date lastLogin) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
		this.numberOfRecords = numberOfRecords;
		this.numberOfLogins = numberOfLogins;
		this.lastRecords = lastRecords;
		this.lastLogin = lastLogin;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Integer getNumberOfLogins() {
		return numberOfLogins;
	}

	public void setNumberOfLogins(Integer numberOfLogins) {
		this.numberOfLogins = numberOfLogins;
	}

	public String getLastRecords() {
		return lastRecords;
	}

	public void setLastRecords(String lastRecords) {
		this.lastRecords = lastRecords;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((lastLogin == null) ? 0 : lastLogin.hashCode());
        result = prime * result
                + ((lastRecords == null) ? 0 : lastRecords.hashCode());
        result = prime * result
                + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result
                + ((numberOfLogins == null) ? 0 : numberOfLogins.hashCode());
        result = prime * result
                + ((numberOfRecords == null) ? 0 : numberOfRecords.hashCode());
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
        UserStatistic other = (UserStatistic) obj;
        if (lastLogin == null) {
            if (other.lastLogin != null)
                return false;
        } else if (!lastLogin.equals(other.lastLogin))
            return false;
        if (lastRecords == null) {
            if (other.lastRecords != null)
                return false;
        } else if (!lastRecords.equals(other.lastRecords))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (numberOfLogins == null) {
            if (other.numberOfLogins != null)
                return false;
        } else if (!numberOfLogins.equals(other.numberOfLogins))
            return false;
        if (numberOfRecords == null) {
            if (other.numberOfRecords != null)
                return false;
        } else if (!numberOfRecords.equals(other.numberOfRecords))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "UserStatistic [uuid=" + uuid + ", nickName=" + nickName + ", numberOfRecords=" + numberOfRecords
				+ ", numberOfLogins=" + numberOfLogins + ", lastRecords=" + lastRecords + ", lastLogin=" + lastLogin
				+ "]\n";
	}
	
}
