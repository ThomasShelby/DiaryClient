package com.softserve.tc.diaryclient.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diary.entity.Follower;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.autosave.RecordJAXBParser;
import com.softserve.tc.diaryclient.entity.Record;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.service.MailSender;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class FollowerController {
private static Logger logger = Log.init(FollowerController.class.toString());
    
    @Autowired
    DiaryServicePortProvider diaryServicePortProvider;
    
    @RequestMapping(value = "/followedUsers")
    public String followedUsers(@RequestParam(value = "nickName") String nickName, Model model) {
        logger.info("Get followed users");
        DiaryService port = diaryServicePortProvider.getPort();
        User follower=port.getUserByNickName(nickName);
        List<User> followedUsersList = port.getAllFollowedUsers(follower.getUuid());
        port.markAsViwed(follower.getUuid());
        model.addAttribute("followedUsersList", followedUsersList);
        return "followedUsers";
    }
    
    @RequestMapping(value = "/follow")
    public String follow(@RequestParam(value = "nickName") String nickName, HttpServletRequest request) {
        logger.info("Follow user "+nickName);
        DiaryService port = diaryServicePortProvider.getPort();
        User user = port.getUserByNickName(nickName);
        User follower = port.getUserByNickName(request.getUserPrincipal().getName());
        port.attachFollower(new Follower(follower,user));
        
        MailSender mail = MailSender.getInstance();
        String userEmail = user.geteMail();
        mail.setParameters("The Diary. You have new subscriber.",
                "User " + follower.getNickName() + " follows you.", userEmail);
        Thread t = new Thread(mail);
        t.start(); 
        return "redirect:/followedUsers?nickName=" + follower.getNickName();
    }
    
    @RequestMapping(value = "/unfollow")
    public String unfollow(@RequestParam(value = "nickName") String nickName, HttpServletRequest request) {
        logger.info("Follow user "+nickName);
        DiaryService port = diaryServicePortProvider.getPort();
        User user = port.getUserByNickName(nickName);
        User follower = port.getUserByNickName(request.getUserPrincipal().getName());
        port.detachFollower(new Follower(follower,user));
       
        return "redirect:/followedUsers?nickName=" + follower.getNickName();
    }
    
    @RequestMapping(value = "/checkNewRecords", method = RequestMethod.GET)
    public @ResponseBody String checkNewRecords(HttpServletRequest request) {
        logger.info("Chek if user have add records");
        DiaryService port = diaryServicePortProvider.getPort();
        User follower = port.getUserByNickName(request.getUserPrincipal().getName());
        boolean check = port.isThereAvalableNewRecords(follower.getUuid());
        if (check) {
            return "true";
        } else {
            return "false";
        }
    }
}
