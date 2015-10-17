package com.softserve.tc.diaryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class RecordDescriptionController {
	
	@RequestMapping(value = "/recordsDescription")
	public String recordDescription(@RequestParam(value = "id_rec") String id_rec, ModelMap model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		Record record = port.readByKey(id_rec);
		String userId = record.getUserId();
		User user = port.getUserByKey(userId);
		model.addAttribute("user",user);
		model.addAttribute("record", record);
		return "recordsDescription";
	}
	
	@RequestMapping(value = "/editRecord", method = RequestMethod.GET)
	public String editRecord(@RequestParam("id_rec") String id_rec, Model model) {
		System.out.println(id_rec);
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		Record record = port.readByKey(id_rec);
		User user = port.getUserByKey(record.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("record", record);
		System.out.println(record);
		return "addRecord";
	}

}
