package com.softserve.tc.diaryclient.statistic;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class SystemStatisticTimer extends Timer {

	private TimerTask timerTask;
	private final static int  MILISINDAY = 24 * 60 * 60 * 1000;
	
	public SystemStatisticTimer () {}

	public TimerTask getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}
	
	public void init() {
		Date current  = new Date();
		current.setHours(01);
		current.setMinutes(59);
		current.setSeconds(59);
		this.scheduleAtFixedRate(timerTask, current, MILISINDAY);
	}
}
