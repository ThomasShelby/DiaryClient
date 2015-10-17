package com.softserve.tc.diaryclient.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.log.Log;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

@Controller
public class HomeController {

	private Logger logger = Log.init(this.getClass().getName());

	public final String START_OF_DAY = " 00:00:00";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
	    String userNickName = request.getUserPrincipal().getName();
	    DiaryService port = DiaryServiceConnection.getDairyServicePort();
	    User user=port.getUserByNickName(userNickName);
	    String userCity=user.getAddress().getCity();
	    String defaultCity="Kiev";
	    
	    logger.info("Connect to OpenWeatherMap service");
	    OpenWeatherMap weatherService=new OpenWeatherMap(Units.METRIC,"a151b14359476eb0b7b2738e8723dc0c");
	    CurrentWeather weather=null;
	    
	    JsonObject json =null;
	    JsonParser parser=new JsonParser();
	    
	    try {
	        if(userCity==null){
	            userCity=defaultCity;
	        }
	        logger.info("Get current weather for "+userCity+" in JSON format");
            weather=weatherService.currentWeatherByCityName(userCity);
            logger.info("Parsing JSON");
            json= parser.parse(weather.getRawResponse()).getAsJsonObject();
            
            if(!json.get("cod").getAsString().equals("200")){
                logger.error("City not found, use dafault city: "+defaultCity);
                weather=weatherService.currentWeatherByCityName(defaultCity);
                logger.info("Parsing JSON");
                json = parser.parse(weather.getRawResponse()).getAsJsonObject();
                JsonObject main=json.getAsJsonObject("main");
                JsonArray weatherArray=json.getAsJsonArray("weather");
                JsonObject weatherElement=weatherArray.get(0).getAsJsonObject();
                JsonObject wind=json.getAsJsonObject("wind");
                model.addAttribute("city_name", json.get("name").getAsString());
                model.addAttribute("temp", main.get("temp").getAsString());
                model.addAttribute("humidity", main.get("humidity").getAsString());
                model.addAttribute("weather_icon", weatherElement.get("icon").getAsString());
                model.addAttribute("wind", wind.get("speed").getAsString());
                return "home";
            }
            
            JsonObject main=json.getAsJsonObject("main");
            JsonArray weatherArray=json.getAsJsonArray("weather");
            JsonObject weatherElement=weatherArray.get(0).getAsJsonObject();
            JsonObject wind=json.getAsJsonObject("wind");
            model.addAttribute("city_name", json.get("name").getAsString());
            model.addAttribute("temp", main.get("temp").getAsString());
            model.addAttribute("humidity", main.get("humidity").getAsString());
            model.addAttribute("weather_icon", weatherElement.get("icon").getAsString());
            model.addAttribute("wind", wind.get("speed").getAsString());
        } catch (JSONException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
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