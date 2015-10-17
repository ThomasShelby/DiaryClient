package com.softserve.tc.diaryclient.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.service.UserFolderForPersonalData;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class AddRecordConteroller {
	private static Logger logger = Log.init(UserController.class.toString());

	@RequestMapping(value = "/addRecord", method = RequestMethod.POST)
	public String addRecordPost(@RequestParam("title") String title, @RequestParam("text") String text,
			@RequestParam("status") String status, @RequestParam("nick") String nick,
			@RequestParam("file") MultipartFile file, Model model) {
		String fileName = null;
		File serverFile = null;
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            try {
            	String url = UserFolderForPersonalData.createFolderForUser(nick);
            	
                byte[] bytes = file.getBytes();
                
                serverFile = new File(url
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                
                fileName = nick + File.separator + fileName;
                stream.write(bytes);
                stream.close();
                logger.info("You successfully uploaded file=" + fileName);
               
            } catch (Exception e) {
            	logger.error("You failed to upload " + fileName + " => "
                        + e.getMessage());

            }
        } 
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		Record record = port.addRecord(nick, title, text, status, fileName);
		boolean result = false;
		if (record == null) {
			model.addAttribute("result", result);
		} else {
			result = true;
			model.addAttribute("result", result);
			model.addAttribute("record", record);
		}
		return "recordsDescription";
	}
	
	@RequestMapping(value = "/addRecord", method = RequestMethod.GET)
	public String addRecordGet(HttpServletRequest request, Model model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		String userNickName = request.getUserPrincipal().getName();
		com.softserve.tc.diary.entity.User user = port.getUserByNickName(userNickName);
		model.addAttribute("user", user);
		return "addRecord";
	}
	
}
