package com.softserve.tc.diaryclient.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.tc.diaryclient.dao.LoginDurationDAO;

@Controller
public class SystemStatisticController {
    @Autowired    
    LoginDurationDAO logDurDAO;

    
    @RequestMapping(value = "/systemStatistic")
    public String systemStatistic(Model model) {
        Map<Integer, Long> map = logDurDAO.getLoginDate();
        model.addAttribute("map", map);  
        List<Integer> list = logDurDAO.getAllLoginsCount();
        model.addAttribute("list", list);
        Map<Integer, Double> loginDuration = logDurDAO.getGenLoginDuration();
        model.addAttribute("loginDuration", loginDuration);  
        
        return "SystemStatistic";
    }
    
//    @RequestMapping(value = "/getLoginDate")
//    public Map<Integer, Long> userProfile( Model model) {
//        Map<Integer, Long> map = logDurDAO.getLoginDate();
//     return map;
//    }
}