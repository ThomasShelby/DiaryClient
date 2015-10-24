package com.softserve.tc.diaryclient.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.dao.LoginDurationDAO;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class SystemStatisticController {
    @Autowired    
    LoginDurationDAO logDurDAO;
    
    @Autowired
    UserSessionDAO userSessDao;
    
    @RequestMapping(value = "/systemStatistic")
    public String systemStatistic(Model model) {
        Map<Integer, Long> map = logDurDAO.getLoginDate();
        model.addAttribute("map", map);  
        List<Integer> list = logDurDAO.getAllLoginsCount();
        model.addAttribute("list", list);
        Map<Integer, Double> loginDuration = logDurDAO.getGenLoginDuration();
        model.addAttribute("loginDuration", loginDuration);  
        Map<Integer, Integer> sessionDuration = userSessDao.getSessionDuration();
        model.addAttribute("sessionDuration", sessionDuration);
        
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        int[][] recordsPerDay = port.getRecDate();
        model.addAttribute("recordsPerDay", recordsPerDay);

        return "SystemStatistic";
    }
}