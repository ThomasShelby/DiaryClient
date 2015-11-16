package com.softserve.tc.diaryclient.statistic;

import java.util.Date;
import java.util.List;

import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;



public class SystemStatisticHelper {
	
	
	private DiaryService port;
	
	public SystemStatisticHelper() {
		port = DiaryServicePortProvider.getPort();
	}

	public int getNumerOfRecords(Date date) {
		return port.getCountRecordsByDate(date);
	}
	
	public int getCountUsers() {
		return port.getCountOfUsers();
	}
	
	public int getCountActiveUsers() {
		List<User> list = port.getActiveUsers();
		return list.size();
	}
	
	public User getMostActiveUserByDate(Date date) {
		return port.getMostActiveUser(date);
	}
}
