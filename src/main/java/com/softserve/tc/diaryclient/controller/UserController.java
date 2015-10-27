package com.softserve.tc.diaryclient.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class UserController {
    private static Logger logger = Log.init(UserController.class.toString());
    
	@Autowired
	DiaryServicePortProvider diaryServicePortProvider;
    
    @RequestMapping(value = "/users")
    public String users(Model model) {
        DiaryService port = diaryServicePortProvider.getPort();
        List<User> usersList = port.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "Users";
    }
    
    @RequestMapping(value = "/userProfile")
    public String userProfile(@RequestParam(value = "nickName") String nickName,
            @RequestParam(value = "followed", required = false) String followed,
            Model model) {
        DiaryService port = diaryServicePortProvider.getPort();
        User user = port.getUserByNickName(nickName);
        List<Record> recordList=port.getAllPublicRecordsByNickName(nickName);
        model.addAttribute("recordList", recordList);
        model.addAttribute("followed", followed);
        model.addAttribute("user", user);
        return "user_profile";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(
            @RequestParam(value = "nickName", required = true) String nickName,
            Model model) {
        DiaryService port = diaryServicePortProvider.getPort();
        User user = port.getUserByNickName(nickName);
        model.addAttribute("user", user);
        return "editUser";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("user") User user,
            @RequestParam("file") MultipartFile file) {
        DiaryService port = diaryServicePortProvider.getPort();
        try {
            if (file.isEmpty()) {
                port.updateUserWithoutImage(user);
            } else {
                port.updateUser(user, file.getBytes(),
                        file.getOriginalFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/userProfile?nickName=" + user.getNickName();
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProfile(@RequestParam(value = "nickName",
            required = true) String nickName) {
        DiaryService port = diaryServicePortProvider.getPort();
        port.deleteUser(port.getUserByNickName(nickName));
        return "redirect:/users";
    }
    
}
