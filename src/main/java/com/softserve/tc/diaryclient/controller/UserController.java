package com.softserve.tc.diaryclient.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class UserController {
	@RequestMapping(value = "/users")
	public String users(Model model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		List<User> usersList = port.getAllUsers();
		model.addAttribute("usersList", usersList);
		return "Users";
	}

	@RequestMapping(value = "/userProfile")
	public String userProfile(@RequestParam(value = "nickName") String nickName, Model model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		User us = port.getUserByNickName(nickName);
		model.addAttribute("user", us);
		return "user_profile";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editProfile(@RequestParam(value = "nickName", required = true) String nickName, Model model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		User user = port.getUserByNickName(nickName);
		String[] address = user.getAddress().split(", ");
		model.addAttribute("user", user);
		model.addAttribute("country", address[0]);
		model.addAttribute("city", address[1]);
		model.addAttribute("street", address[2]);
		model.addAttribute("building", address[3]);
		return "editUser";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("user") User user, @RequestParam("country") String country,
			@RequestParam("city") String city, @RequestParam("street") String street,
			@RequestParam("building") String building) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		user.setAddress(country + ", " + city + ", " + street + ", " + building);
		port.updateUser(user);
		return "redirect:/userProfile?nickName="+user.getNickName();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteProfile(@RequestParam(value = "nickName", required = true) String nickName) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		port.deleteUser(port.getUserByNickName(nickName));
		return "redirect:/users";
	}

}
