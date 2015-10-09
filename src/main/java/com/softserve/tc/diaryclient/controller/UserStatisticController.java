package com.softserve.tc.diaryclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.softserve.tc.diary.entity.Tag;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.*;
import com.softserve.tc.diaryclient.dao.UserStatisticDAO;
import com.softserve.tc.diaryclient.entity.Record;
import com.softserve.tc.diaryclient.entity.SystemStatistic;
import com.softserve.tc.diaryclient.entity.UserStatistic;
import com.softserve.tc.diaryclient.service.UserStatisticService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class UserStatisticController {
    @Autowired
    UserStatisticDAO userStatDAO;
    @Autowired
    UserStatisticService userStatisticService ;
    
    @RequestMapping(value = "/users-statistic")
    public String users(Model model) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
    
        List<UserStatistic> usersList = userStatDAO.getAll();
        String json = new Gson().toJson(usersList);
        model.addAttribute("usersList", json);
        
        User mostActiveUser = port.getMostActiveUser(); 
        model.addAttribute("mostActiveUser", mostActiveUser);
        int numberOfRecords = userStatisticService.getUserAmountOfRecords(mostActiveUser.getNickName());
        model.addAttribute("usersAmountOfRecords", numberOfRecords);
        
        Tag mostPopularTag = port.getMostPopularTag();
        model.addAttribute("mostPopularTag", mostPopularTag);
        
        int[] sexStatistic = port.getSexStatistic();
        int male = sexStatistic[0];
        model.addAttribute("male", male);
        int female = sexStatistic[1];
        model.addAttribute("female", female);
        
        return "users-statistic";
    }
    
    @RequestMapping(value = "/my-statistic", method = RequestMethod.GET)
    public String myStatistic(@RequestParam(value = "nickName",required=false) String nickName,Model model) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        if (nickName.isEmpty()){
            return "redirect:/login";
        }
        User us = port.getUserByNickName(nickName);
        model.addAttribute("user", us);
        UserStatistic clientUserStat = userStatDAO.findByNickName("Bob");
        model.addAttribute("userStatistic",clientUserStat);
        
        int numberOfRecords = userStatisticService.getUserAmountOfRecords(nickName);
        model.addAttribute("numberOfRecords", numberOfRecords);
        
        /*List<com.softserve.tc.diary.entity.Record> records= port.getAllRecordsByDate(nickName,"2015-09-03 00:00:00");
        model.addAttribute("recordsByDate", records);*/
        
        return "my-statistic";
    }
    
}
