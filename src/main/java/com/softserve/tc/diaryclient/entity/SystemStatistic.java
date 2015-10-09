package com.softserve.tc.diaryclient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// @Table(name="system_statistic)
public class SystemStatistic {
    
    @Id
    private String uuid;
    
    @Column(name = "general_number_of_users_per_month")
    private Integer genNumOfUsersPerMonth;
    
    @Column(name = "general_number_of_records_per_month")
    private Integer genNumOfRecordsPerMonth;
    
    @Column(name = "the_longest_time_online")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date theLongestTimeOnline;
    private Integer theLongestTimeOnline;
    
    @Column(name = "the_biggest_record")
    private String theBiggestRecord;
    
    @Column(name = "most_popular_record") // most_interesting_record
    private String mostPopRecord;
    
    @Column(name = "number_of_new_users_per_month")
    private Integer numOfNewUsersPerMonth;
    
    @Column(name = "most_active_user_per_month")
    private String mostActiveUserPerMonth;
    
    @Column(name = "most_popular_user_per_month")
    private String mostPpopularUserPerMonth;
    
    public SystemStatistic() {
        super();
    }
    
    public SystemStatistic(Integer genNumOfUsersPerMonth,
            Integer genNumOfRecordsPerMonth, Integer theLongestTimeOnline,
            String theBiggestRecord, String mostPopRecord,
            Integer numOfNewUsersPerMonth,
            String mostActiveUserPerMonth, String mostPpopularUserPerMonth) {
        super();
        this.genNumOfUsersPerMonth = genNumOfUsersPerMonth;
        this.genNumOfRecordsPerMonth = genNumOfRecordsPerMonth;
        this.theLongestTimeOnline = theLongestTimeOnline;
        this.theBiggestRecord = theBiggestRecord;
        this.mostPopRecord = mostPopRecord;
        this.numOfNewUsersPerMonth = numOfNewUsersPerMonth;
        this.mostActiveUserPerMonth = mostActiveUserPerMonth;
        this.mostPpopularUserPerMonth = mostPpopularUserPerMonth;
    }
    
    public Integer getGenNumOfUsersPerMonth() {
        return genNumOfUsersPerMonth;
    }
    
    public void setGenNumOfUsersPerMonth(Integer genNumOfUsersPerMonth) {
        this.genNumOfUsersPerMonth = genNumOfUsersPerMonth;
    }
    
    public Integer getGenNumOfRecordsPerMonth() {
        return genNumOfRecordsPerMonth;
    }
    
    public void setGenNumOfRecordsPerMonth(Integer genNumOfRecordsPerMonth) {
        this.genNumOfRecordsPerMonth = genNumOfRecordsPerMonth;
    }
    
    public Integer getTheLongestTimeOnline() {
        return theLongestTimeOnline;
    }
    
    public void setTheLongestTimeOnline(Integer theLongestTimeOnline) {
        this.theLongestTimeOnline = theLongestTimeOnline;
    }
    
    public String getTheBiggestRecord() {
        return theBiggestRecord;
    }
    
    public void setTheBiggestRecord(String theBiggestRecord) {
        this.theBiggestRecord = theBiggestRecord;
    }
    
    public String getMostPopRecord() {
        return mostPopRecord;
    }
    
    public void setMostPopRecord(String mostPopRecord) {
        this.mostPopRecord = mostPopRecord;
    }
    
    public Integer getNumOfNewUsersPerMonth() {
        return numOfNewUsersPerMonth;
    }
    
    public void setNumOfNewUsersPerMonth(Integer numOfNewUsersPerMonth) {
        this.numOfNewUsersPerMonth = numOfNewUsersPerMonth;
    }
    
    public String getMostActiveUserPerMonth() {
        return mostActiveUserPerMonth;
    }
    
    public void setMostActiveUserPerMonth(String mostActiveUserPerMonth) {
        this.mostActiveUserPerMonth = mostActiveUserPerMonth;
    }
    
    public String getMostPpopularUserPerMonth() {
        return mostPpopularUserPerMonth;
    }
    
    public void setMostPpopularUserPerMonth(String mostPpopularUserPerMonth) {
        this.mostPpopularUserPerMonth = mostPpopularUserPerMonth;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genNumOfRecordsPerMonth == null) ? 0
                : genNumOfRecordsPerMonth.hashCode());
        result = prime * result + ((genNumOfUsersPerMonth == null) ? 0
                : genNumOfUsersPerMonth.hashCode());
        result = prime * result + ((mostActiveUserPerMonth == null) ? 0
                : mostActiveUserPerMonth.hashCode());
        result = prime * result
                + ((mostPopRecord == null) ? 0 : mostPopRecord.hashCode());
        result = prime * result + ((mostPpopularUserPerMonth == null) ? 0
                : mostPpopularUserPerMonth.hashCode());
        result = prime * result + ((numOfNewUsersPerMonth == null) ? 0
                : numOfNewUsersPerMonth.hashCode());
        result = prime * result + ((theBiggestRecord == null) ? 0
                : theBiggestRecord.hashCode());
        result = prime * result + ((theLongestTimeOnline == null) ? 0
                : theLongestTimeOnline.hashCode());
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
        SystemStatistic other = (SystemStatistic) obj;
        if (genNumOfRecordsPerMonth == null) {
            if (other.genNumOfRecordsPerMonth != null)
                return false;
        } else
            if (!genNumOfRecordsPerMonth.equals(other.genNumOfRecordsPerMonth))
            return false;
        if (genNumOfUsersPerMonth == null) {
            if (other.genNumOfUsersPerMonth != null)
                return false;
        } else if (!genNumOfUsersPerMonth.equals(other.genNumOfUsersPerMonth))
            return false;
        if (mostActiveUserPerMonth == null) {
            if (other.mostActiveUserPerMonth != null)
                return false;
        } else if (!mostActiveUserPerMonth.equals(other.mostActiveUserPerMonth))
            return false;
        if (mostPopRecord == null) {
            if (other.mostPopRecord != null)
                return false;
        } else if (!mostPopRecord.equals(other.mostPopRecord))
            return false;
        if (mostPpopularUserPerMonth == null) {
            if (other.mostPpopularUserPerMonth != null)
                return false;
        } else if (!mostPpopularUserPerMonth
                .equals(other.mostPpopularUserPerMonth))
            return false;
        if (numOfNewUsersPerMonth == null) {
            if (other.numOfNewUsersPerMonth != null)
                return false;
        } else if (!numOfNewUsersPerMonth.equals(other.numOfNewUsersPerMonth))
            return false;
        if (theBiggestRecord == null) {
            if (other.theBiggestRecord != null)
                return false;
        } else if (!theBiggestRecord.equals(other.theBiggestRecord))
            return false;
        if (theLongestTimeOnline == null) {
            if (other.theLongestTimeOnline != null)
                return false;
        } else if (!theLongestTimeOnline.equals(other.theLongestTimeOnline))
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
        return "SystemStatistic [genNumOfUsersPerMonth=" + genNumOfUsersPerMonth
                + ", genNumOfRecordsPerMonth=" + genNumOfRecordsPerMonth
                + ", theLongestTimeOnline=" + theLongestTimeOnline
                + ", theBiggestRecord=" + theBiggestRecord + ", mostPopRecord="
                + mostPopRecord + ", numOfNewUsersPerMonth="
                + numOfNewUsersPerMonth + ", mostActiveUserPerMonth="
                + mostActiveUserPerMonth + ", mostPpopularUserPerMonth="
                + mostPpopularUserPerMonth + "]\n";
    }
    
}
