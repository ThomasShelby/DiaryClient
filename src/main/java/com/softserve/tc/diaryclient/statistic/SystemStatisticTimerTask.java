package com.softserve.tc.diaryclient.statistic;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diaryclient.dao.SystemStatisticPerDayDAO;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.entity.SystemStatisticPerDay;

public class SystemStatisticTimerTask extends TimerTask{
	
	private static ConfigurableApplicationContext contextConn = new ClassPathXmlApplicationContext(
			"/META-INF/appContext.xml");
	
	@Override
	public void run() {
		SystemStatisticHelper st = new SystemStatisticHelper();
        Date date = new Date();
        User user = st.getMostActiveUserByDate(date);
        String nickName = null;
        if (user != null) {
        	nickName = user.getNickName();
        }
        int numberOfRecords = st.getNumerOfRecords(date);
        int countUsers = st.getCountUsers();
        int countActiveUsers = st.getCountActiveUsers();

		SystemStatisticPerDayDAO systemStatisticPerDayDAO = 
		(SystemStatisticPerDayDAO)contextConn.getBean("systemStatisticPerDayDAO");
		SystemStatisticPerDay perDay = new SystemStatisticPerDay(nickName, numberOfRecords, countUsers, countActiveUsers);
		systemStatisticPerDayDAO.create(perDay);

	}
}
