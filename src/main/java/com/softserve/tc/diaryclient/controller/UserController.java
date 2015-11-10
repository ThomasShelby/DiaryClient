package com.softserve.tc.diaryclient.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class UserController {

  private static Logger logger = Log.init(UserController.class.toString());
  DiaryService port;

  @Autowired
  DiaryServicePortProvider diaryServicePortProvider;

  @SuppressWarnings("static-access")
  public UserController() {
    port = diaryServicePortProvider.getPort();
  }

  @RequestMapping(value = "/users")
  public String users(Model model) {
    List<User> usersList = port.getAllUsers();
    model.addAttribute("usersList", usersList);
    return "Users";
  }

  @RequestMapping(value = "/userProfile")
  public String userProfile(@RequestParam(value = "nickName") String nickName,
      @RequestParam(value = "followed", required = false) String followed, Model model) {
    User user = port.getUserByNickName(nickName);
    List<Record> recordList = port.getAllPublicRecordsByNickName(nickName);
    model.addAttribute("recordList", recordList);
    model.addAttribute("followed", followed);
    model.addAttribute("user", user);
    return "user_profile";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String editProfile(
      @AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails,
      Model model) {
    String nickName = userDetails.getUsername();
    User receivedUser = port.getUserByNickName(nickName);
    model.addAttribute("user", receivedUser);
    return "editUser";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String saveEdit(@ModelAttribute("user") User user,
      @RequestParam("file") MultipartFile file, String passwordNew) {
    
    if (!(passwordNew.isEmpty())){
      String passwordHashNew = port.getHashOfPassword(passwordNew);
      user.setPassword(passwordHashNew);
    }
    
    try {
      if (file.isEmpty()) {
        port.updateUserWithoutImage(user);
      } else {
        port.updateUser(user, file.getBytes(), file.getOriginalFilename());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "redirect:/userProfile?nickName=" + user.getNickName();
  }

  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  public String deleteProfile(@RequestParam(value = "nickName", required = true) String nickName) {
    port.deleteUser(port.getUserByNickName(nickName));
    return "redirect:/users";
  }

  @RequestMapping(value = "/isOldPasswordCorrect", method = RequestMethod.GET)
  public @ResponseBody String isOldPasswordCorrect(@RequestParam("password") String oldPassword,
      HttpServletRequest request) throws NoSuchAlgorithmException {

    String userNickName = request.getUserPrincipal().getName();
    User user = port.getUserByNickName(userNickName);
    String hashOfRealPassword = user.getPassword();
    String hashOfEnteredOldPassword = port.getHashOfPassword(oldPassword);
    if (hashOfEnteredOldPassword.equals(hashOfRealPassword))
      return new Gson().toJson("true");

    logger.info("Not correct password entered during password changing user:" + userNickName);
    return new Gson().toJson("false");

  }

}
