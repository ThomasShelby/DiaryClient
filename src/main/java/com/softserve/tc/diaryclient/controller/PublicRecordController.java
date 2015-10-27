package com.softserve.tc.diaryclient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class PublicRecordController {
	
	@Autowired
	DiaryServicePortProvider diaryServicePortProvider;
	
	private DiaryService port;
	
	public PublicRecordController() {
	  port = DiaryServicePortProvider.getPort();
  }

	@RequestMapping(value = "/publicRecords")
	public String records(ModelMap model) {
		List<Record> list = port.getAllPublicRecords();
		model.addAttribute("recordsList", list);
		return "publicRecords";
	}

	@RequestMapping(value = "/hashTag", method = RequestMethod.GET)
	public String recordsByHahTag(@RequestParam(value = "hashTag") String hashTag, Model model) {
		List<Record> list = port.getAllPublicRecordsByHashTag(hashTag);
		model.addAttribute("recordsList", list);
		return "publicRecords";	
	}

}
