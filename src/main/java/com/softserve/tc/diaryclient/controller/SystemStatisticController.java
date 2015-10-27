package com.softserve.tc.diaryclient.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @RequestMapping(value = "/systemStatistic")
    public String systemStatistic(Model model) {
        Map<Date, Long> map = logDurDAO.getLoginDate();
        model.addAttribute("map", map);  
        List<Integer> list = logDurDAO.getAllLoginsCount();
        model.addAttribute("list", list);
        Map<Date, Double> loginDuration = logDurDAO.getGenLoginDuration();
        model.addAttribute("loginDuration", loginDuration);  
        Map<Date, Long> sessionDuration = userSessDao.getSessionDuration();
        model.addAttribute("sessionDuration", sessionDuration);
        
        String[][] recordsPerDay = port.getRecDate();
        model.addAttribute("recordsPerDay", recordsPerDay);

        return "SystemStatistic";
    }
}