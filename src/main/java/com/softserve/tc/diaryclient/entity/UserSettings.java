package com.softserve.tc.diaryclient.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UserSettings {
	
	@Id
	private String uuid; 
	
	@NotNull
	@Column(name="nick_name")
	private String nickName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="theme")
	private Theme theme;
	
	@Column(name="number_of_records_to_show")
	private Integer numberOfRecordsToShow;
	
	@Enumerated(EnumType.STRING)
	@Column(name="show_full_name")
	private YesNo showFullName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="show_date_of_birth")
	private YesNo showDateOfBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(name="language")
	private Language language;
	
	@Enumerated(EnumType.STRING)
	@Column(name="time_format")
	private TimeFormat timeFormat;
	
	public UserSettings() {
		super();
	}
	
	public UserSettings(String nickName, Theme theme, Integer numberOfRecordsToShow, YesNo showFullName,
			YesNo showDateOfBirth, Language language, TimeFormat timeFormat) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
		this.theme = theme;
		this.numberOfRecordsToShow = numberOfRecordsToShow;
		this.showFullName = showFullName;
		this.showDateOfBirth = showDateOfBirth;
		this.language = language;
		this.timeFormat = timeFormat;
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

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public Integer getNumberOfRecordsToShow() {
		return numberOfRecordsToShow;
	}

	public void setNumberOfRecordsToShow(Integer numberOfRecordsToShow) {
		this.numberOfRecordsToShow = numberOfRecordsToShow;
	}

	public YesNo getShowFullName() {
		return showFullName;
	}

	public void setShowFullName(YesNo showFullName) {
		this.showFullName = showFullName;
	}

	public YesNo getShowDateOfBirth() {
		return showDateOfBirth;
	}

	public void setShowDateOfBirth(YesNo showDateOfBirth) {
		this.showDateOfBirth = showDateOfBirth;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public TimeFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(TimeFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((language == null) ? 0 : language.hashCode());
        result = prime * result
                + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((numberOfRecordsToShow == null) ? 0
                : numberOfRecordsToShow.hashCode());
        result = prime * result
                + ((showDateOfBirth == null) ? 0 : showDateOfBirth.hashCode());
        result = prime * result
                + ((showFullName == null) ? 0 : showFullName.hashCode());
        result = prime * result + ((theme == null) ? 0 : theme.hashCode());
        result = prime * result
                + ((timeFormat == null) ? 0 : timeFormat.hashCode());
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
        UserSettings other = (UserSettings) obj;
        if (language != other.language)
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (numberOfRecordsToShow == null) {
            if (other.numberOfRecordsToShow != null)
                return false;
        } else if (!numberOfRecordsToShow.equals(other.numberOfRecordsToShow))
            return false;
        if (showDateOfBirth != other.showDateOfBirth)
            return false;
        if (showFullName != other.showFullName)
            return false;
        if (theme != other.theme)
            return false;
        if (timeFormat != other.timeFormat)
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
		return "Settings [uuid=" + uuid + ", nickName=" + nickName + ", theme=" + theme + ", numberOfRecordsToShow="
				+ numberOfRecordsToShow + ", showFullName=" + showFullName + ", showDateOfBirth=" + showDateOfBirth
				+ ", language=" + language + ", timeFormat=" + timeFormat + "]\n";
	}

}
