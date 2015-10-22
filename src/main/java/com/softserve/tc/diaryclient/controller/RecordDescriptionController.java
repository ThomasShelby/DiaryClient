package com.softserve.tc.diaryclient.controller;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.Status;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class RecordDescriptionController {

	private static Logger logger = Log.init(UserController.class.toString());
	private static DiaryService port = DiaryServiceConnection.getDairyServicePort();

	@RequestMapping(value = "/recordsDescription")
	public String recordDescription(@RequestParam(value = "id_rec") String id_rec, ModelMap model) {
		Record record = port.readByKey(id_rec);
		System.out.println(record);
		String userId = record.getUserId();
		User user = port.getUserByKey(userId);
		System.out.println(user);
		model.addAttribute("user", user);
		model.addAttribute("record", record);
		return "recordsDescription";
	}

	@RequestMapping(value = "/editRecord", method = RequestMethod.GET)
	public String editRecordGet(@RequestParam("id_rec") String id_rec, Model model) {
		Record record = port.readByKey(id_rec);
		User user = port.getUserByKey(record.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("record", record);
		return "addRecord";
	}

	@RequestMapping(value = "/editRecord", method = RequestMethod.POST)
	public String editRecordPost(@ModelAttribute("record") Record record, @RequestParam("nick") String nick,
			@RequestParam("status") String status, @RequestParam("file") MultipartFile file, Model model) {
		User user = port.getUserByNickName(nick);
		record.setVisibility(status);
		Record rec = null;
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			record.setSupplement(fileName);
			try {
				byte[] bytes = file.getBytes();
				rec = port.updateRecord(record, bytes);
			} catch (IOException e) {
				logger.error("You failed to upload " + fileName + " => " + e.getMessage());
			}
		} else {
			rec = port.updateRecord(record, null);
		}

		if (rec == null) {
			model.addAttribute("result", false);
		} else {
			model.addAttribute("result", true);
			model.addAttribute("user", user);
			model.addAttribute("record", rec);
		}

		File xmlFile = new File(System.getProperty("catalina.home") + File.separator + "tmpFiles" + File.separator
				+ "autosaved_records" + File.separator + nick + "-tempRecord.xml");
		if (xmlFile.exists() && xmlFile.isFile()) {
			xmlFile.delete();
			logger.info("DELETED " + xmlFile.getAbsolutePath());
		}

		return "recordsDescription";
	}

}
