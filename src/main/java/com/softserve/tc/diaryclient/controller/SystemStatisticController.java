package com.softserve.tc.diaryclient.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.dao.LoginDurationDAO;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class SystemStatisticController {
    @Autowired    
    LoginDurationDAO logDurDAO;
    
    @Autowired
    UserSessionDAO userSessDao;
    
  private DiaryService port;
  
  @Autowired
  public SystemStatisticController(DiaryServicePortProvider diaryServicePortProvider) {
    port = DiaryServicePortProvider.getPort();
  }
  
  @RequestMapping(value = "/systemStatisticPage")
  public String systemStatisticPage() {
      return "SystemStatistic";
  }
    
    @RequestMapping(value = "/systemStatistic/logins")
    public @ResponseBody Map<Date, Long> getLogins() {
        Map<Date, Long> loginDateMap = logDurDAO.getLoginDate();
        return loginDateMap;
    }
    
    @RequestMapping(value = "/systemStatistic/records")
    public @ResponseBody Map<Date, Long> getRecords() {
        String[][] recordsPerDay = port.getRecDate();
        Map<Date, Long> recordsDateMap = new LinkedHashMap<Date, Long>();
            for(int i = 0;i < recordsPerDay.length;i++){
                Date key = null;
                Long value; 
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    key = sdf.parse(recordsPerDay[i][0]);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(recordsPerDay[i][1] == null || recordsPerDay[i][1].isEmpty()){
                    value = 0L;
                }
                else{
                    value = Long.valueOf(recordsPerDay[i][1]);
                }
                recordsDateMap.put(key, value);
            }
        return recordsDateMap;
    }
    
    @RequestMapping(value = "/systemStatistic/login_duration")
    public @ResponseBody Map<Date, Double> getLoginDuration() {
        Map<Date, Double> loginDuration = logDurDAO.getGenLoginDuration();
        return loginDuration;
    }   
    
    @RequestMapping(value = "/systemStatistic/session_duration")
    public @ResponseBody Map<Date, Long> getSessionDuration() {
        Map<Date, Long> sessionDuration = userSessDao.getSessionDuration();
        return sessionDuration;
    }   

}