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
public class SystemStatisticPerDay {
	
	@Id
	private String uuid; 

	@Column(name="most_active_user")
	private String nickName;
	
	@Column(name="number_of_records")
	private Integer numberOfRecords;
	
	@Column(name="count_Users")
	private Integer countUsers;
	
	@Column(name="count_active_users")
	private Integer countActiveUsers;
	
	public SystemStatisticPerDay(){}

	public SystemStatisticPerDay( String nickName, Integer numberOfRecords, Integer countUsers,
			Integer countActiveUsers) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
		this.numberOfRecords = numberOfRecords;
		this.countUsers = countUsers;
		this.countActiveUsers = countActiveUsers;
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

	public Integer getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(Integer countUsers) {
		this.countUsers = countUsers;
	}

	public Integer getCountActiveUsers() {
		return countActiveUsers;
	}

	public void setCountActiveUsers(Integer countActiveUsers) {
		this.countActiveUsers = countActiveUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countActiveUsers == null) ? 0 : countActiveUsers.hashCode());
		result = prime * result + ((countUsers == null) ? 0 : countUsers.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((numberOfRecords == null) ? 0 : numberOfRecords.hashCode());
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
		SystemStatisticPerDay other = (SystemStatisticPerDay) obj;
		if (countActiveUsers == null) {
			if (other.countActiveUsers != null)
				return false;
		} else if (!countActiveUsers.equals(other.countActiveUsers))
			return false;
		if (countUsers == null) {
			if (other.countUsers != null)
				return false;
		} else if (!countUsers.equals(other.countUsers))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
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
		return "SystemStatisticPerDay [uuid=" + uuid + ", nickName=" + nickName + ", numberOfRecords=" + numberOfRecords
				+ ", countUsers=" + countUsers + ", countActiveUsers=" + countActiveUsers + "]";
	}
	
	
	


}
