package com.softserve.tc.diaryclient.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.log.Log;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class HomeController {

	private Logger logger = Log.init(this.getClass().getName());

	public final String START_OF_DAY = " 00:00:00";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/getRecordsByDay", method = RequestMethod.GET)
	public @ResponseBody String getRecordsByDay(@RequestParam("selected") String date, HttpServletRequest request) {

		String userNickName = request.getUserPrincipal().getName();
		DiaryService port = DiaryServiceConnection.getDairyServicePort();

		logger.info(userNickName + " gets records per " + date);

		List<Record> recordsList = port.getAllRecordsByDate(userNickName, date + START_OF_DAY);

		return new Gson().toJson(recordsList);

	}

	@RequestMapping(value = "/getDaysWichHaveRecordsPerMonth", method = RequestMethod.GET)
	public @ResponseBody String getDaysWichHaveRecordsPerMonth(@RequestParam("dateStart") String date,
			HttpServletRequest request) {

		String userNickName = request.getUserPrincipal().getName();
		DiaryService port = DiaryServiceConnection.getDairyServicePort();

		logger.info(userNickName + " gets records per month to " + date);

		List<String> listOfDatesWithRecordsInString = port.getDatesWithRecordsPerMonth(userNickName, date);

//		List<LocalDateTime> listOfDatesWithRecords = new ArrayList<LocalDateTime>();
//		for (String dateElement : listOfDatesWithRecordsInString) {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDateTime dateTime = LocalDateTime.parse(dateElement.substring(0, 9), formatter);
//			listOfDatesWithRecords.add(dateTime);
//		}
//		logger.info(listOfDatesWithRecords.toString());
		return new Gson().toJson(listOfDatesWithRecordsInString);

	}
}