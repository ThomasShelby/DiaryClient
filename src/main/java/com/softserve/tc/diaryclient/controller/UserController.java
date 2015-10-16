package com.softserve.tc.diaryclient.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class UserController {
    private static Logger logger = Log.init(UserController.class.toString());
    
    @RequestMapping(value = "/users")
    public String users(Model model) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        List<User> usersList = port.getAllUsers();
        model.addAttribute("usersList", usersList);
        return "Users";
    }
    
    @RequestMapping(value = "/userProfile")
    public String userProfile(@RequestParam(value = "nickName") String nickName,
            Model model) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        User us = port.getUserByNickName(nickName);
        model.addAttribute("user", us);
        return "user_profile";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(
            @RequestParam(value = "nickName", required = true) String nickName,
            Model model) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        User user = port.getUserByNickName(nickName);
        model.addAttribute("user", user);
        return "editUser";
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("user") User user,
            @RequestParam("file") MultipartFile file) {
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
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
        DiaryService port = DiaryServiceConnection.getDairyServicePort();
        port.deleteUser(port.getUserByNickName(nickName));
        return "redirect:/users";
    }
    
}
