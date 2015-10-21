package com.softserve.tc.diaryclient.webservice.diary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CashBeanGetter {

	static ApplicationContext applicationContext;
	static DiaryServiceCashLoader diaryServiceCashLoader;

	private CashBeanGetter() {
	}

	public static DiaryServiceCashLoader getInstance(){

		if (diaryServiceCashLoader == null){
			applicationContext = new ClassPathXmlApplicationContext("META-INF/appContext.xml");
			diaryServiceCashLoader = (DiaryServiceCashLoader) applicationContext.getBean("cashDiaryService");
		}
		return diaryServiceCashLoader;
	}
}
