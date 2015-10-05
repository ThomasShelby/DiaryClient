package com.softserve.tc.diaryclient.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.tc.diary.dao.implementation.TagDAOImpl;
import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.Tag;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;

import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class PublicRecordController {

	@RequestMapping(value = "/publicRecords")
	public String records(ModelMap model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		List<Record> list = port.getAllPublicRecords();
		model.addAttribute("recordsList", list);
		return "publicRecords";
	}

	@RequestMapping(value = "/recordsDescription")
	public String recordDescription(@RequestParam(value = "id_rec") String id_rec, ModelMap model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		Record record = port.readByKey(id_rec);
		System.out.println(record);
		model.addAttribute("record", record);
		return "recordsDescription";
	}

	@RequestMapping(value = "/hashTag", method = RequestMethod.GET)
	public String recordsByHahTag(@RequestParam(value = "hashTag") String hashTag, Model model) {
		DiaryService port = DiaryServiceConnection.getDairyServicePort();
		List<Record> list = port.getAllPublicRecordsByHashTag(hashTag);
		model.addAttribute("recordsList", list);
		return "publicRecords";	
	}

}
