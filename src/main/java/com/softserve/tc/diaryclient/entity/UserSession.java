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
public class UserSession {
	
	@Id
    private String uuid;
	
	@NotNull
	@Column(name="nick_name")
	private String nickName;

	@NotNull
	@Column(name="session_number")
	private String sessionNumber;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_session")
	private Date startSession;
	
	@Column(name="end_session")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endSession;
	
	public UserSession() {
		super();
	}
	
	public UserSession(String nickName, String sessionNumber, Date startSession) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
		this.sessionNumber = sessionNumber;
		this.startSession = startSession;
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

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Date getStartSession() {
		return startSession;
	}

	public void setStartSession(Date startSession) {
		this.startSession = startSession;
	}

	public Date getEndSession() {
		return endSession;
	}

	public void setEndSession(Date endSession) {
		this.endSession = endSession;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((endSession == null) ? 0 : endSession.hashCode());
        result = prime * result
                + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result
                + ((sessionNumber == null) ? 0 : sessionNumber.hashCode());
        result = prime * result
                + ((startSession == null) ? 0 : startSession.hashCode());
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
        UserSession other = (UserSession) obj;
        if (endSession == null) {
            if (other.endSession != null)
                return false;
        } else if (!endSession.equals(other.endSession))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (sessionNumber == null) {
            if (other.sessionNumber != null)
                return false;
        } else if (!sessionNumber.equals(other.sessionNumber))
            return false;
        if (startSession == null) {
            if (other.startSession != null)
                return false;
        } else if (!startSession.equals(other.startSession))
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
		return "UserSession [uuid=" + uuid + ", nickName=" + nickName + ", sessionNumber=" + sessionNumber
				+ ", startSession=" + startSession + ", endSession=" + endSession + "]\n";
	}

}
