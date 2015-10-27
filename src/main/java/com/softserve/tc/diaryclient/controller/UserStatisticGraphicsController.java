package com.softserve.tc.diaryclient.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diary.dao.implementation.UserDAOImpl;
import com.softserve.tc.diary.entity.Address;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.service.MailSender;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class UserStatisticGraphicsController {
	
	@Autowired
	private UserSessionDAO userSesDAO;
	
	private DiaryService port;
	
	@Autowired
	public UserStatisticGraphicsController(DiaryServicePortProvider diaryServicePortProvider) {
		port = diaryServicePortProvider.getPort();
	}
	
	@RequestMapping(value = "/userStatcGraphic", method = RequestMethod.GET)
	public  String userStatGoogleMap(Model model) {

		
		return  "UserStatGraphics";
	}
	
	@RequestMapping(value = "/getAllAddresses", method = RequestMethod.GET)
	public @ResponseBody String getAllAddresses(@RequestParam("country") String country, Model model) {

		return port.getDataForGeoChactGraphic(country);
	}
	
	@RequestMapping(value = "/getActiveAddresses", method = RequestMethod.GET)
	public @ResponseBody String getActiveAddresses(@RequestParam("mapping") String mapping, Model model) {
		List<User> activeUsers = port.getActiveUsers();

		return  new Gson().toJson(null);
	}
	
	@RequestMapping(value = "/getUsersActivity", method = RequestMethod.GET)
	public @ResponseBody String getUsersActivity(@RequestParam("selectedDate") Date date,
			@RequestParam("dateRange") String dateRange, Model model) {

		Map<String, Integer> map = userSesDAO.getCountActiveusers(dateRange, date);
		return  new Gson().toJson(map);
	}
	

	

}
