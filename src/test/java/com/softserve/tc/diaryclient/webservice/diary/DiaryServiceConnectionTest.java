 package com.softserve.tc.diaryclient.webservice.diary;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.tc.diary.webservice.DiaryService;

public class DiaryServiceConnectionTest {
	
	@Autowired
	DiaryServicePortProvider diaryServicePortProvider;
	
	@Test
	public void testgetDairyServicePort(){
		 DiaryService port = diaryServicePortProvider.getPort();
		 String actual = port.sayHello("Anna");
		 String expected = "Hello from WebService to Anna!";
			 
		 actual = port.sayHello("Lv-159.1Java");
		 expected = "Hello from WebService to Lv-159.1Java!";
		 assertEquals(expected, actual);
	}
}
