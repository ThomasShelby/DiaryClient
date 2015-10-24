package com.softserve.tc.diaryclient.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.Status;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.service.UserFolderForPersonalData;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class RecordDescriptionController {
	
	private static Logger logger = Log.init(UserController.class.toString());
	
	@Autowired
	DiaryServicePortProvider diaryServicePortProvider;
	
	@RequestMapping(value = "/recordsDescription")
	public String recordDescription(@RequestParam(value = "id_rec") String id_rec, ModelMap model) {
		DiaryService port = diaryServicePortProvider.getPort();
		Record record = port.readByKey(id_rec);
		String userId = record.getUserId();
		User user = port.getUserByKey(userId);
		model.addAttribute("user",user);
		model.addAttribute("record", record);
		return "recordsDescription";
	}
	
	@RequestMapping(value = "/editRecord", method = RequestMethod.GET)
	public String editRecordGet(@RequestParam("id_rec") String id_rec, Model model) {
		DiaryService port = diaryServicePortProvider.getPort();
		Record record = port.readByKey(id_rec);
		User user = port.getUserByKey(record.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("record", record);
		return "addRecord";
	}
	
	@RequestMapping(value = "/editRecord", method = RequestMethod.POST)
	public String editRecordPost(@RequestParam("id_rec") String id_rec, @RequestParam("title") String title, 
			@RequestParam("text") String text, @RequestParam("nick") String nick, @RequestParam("url") String url,
			@RequestParam("status") String status, @RequestParam("id_user") String id_user,
			@RequestParam("file") MultipartFile file, Model model) {

		String fileName = null;
		File serverFile = null;
        if (!file.isEmpty()) {
            fileName = file.getOriginalFilename();
            try {
            	url = UserFolderForPersonalData.createFolderForUser(nick);
            	
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
        } else {
        	fileName = url;
        }
		DiaryService port = diaryServicePortProvider.getPort();
		Record record = port.updateRecord(new Record(id_rec, id_user, null, title, text, 
				fileName, Status.valueOf(status) ));
		boolean result = false;
		if (record == null) {
			model.addAttribute("result", result);
		} else {
			result = true;
			model.addAttribute("result", result);
			model.addAttribute("record", record);
		}
		
		File xmlFile = new File(System.getProperty("catalina.home")
                + File.separator + "tmpFiles"
                + File.separator + "autosaved_records" + File.separator + nick
                + "-tempRecord.xml");
        if (xmlFile.exists() && xmlFile.isFile()) {
            xmlFile.delete();
            logger.info("DELETED " + xmlFile.getAbsolutePath());
        }
        
		return "recordsDescription";
	}

}
