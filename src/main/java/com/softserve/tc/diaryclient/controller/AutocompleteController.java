package com.softserve.tc.diaryclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diary.entity.Tag;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

@Controller
public class AutocompleteController {


	private static List<Tag> data = new ArrayList<Tag>();

	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	String getTags(@RequestParam String tag) {
		System.out.println(tag);
		if (tag.startsWith("#")) {
			DiaryService port = DiaryServiceConnection.getDairyServicePort();
			data = port.getListTagsByPrefix(tag);
			return new Gson().toJson(simulateSearchResult(tag));	
		}
		return null;
	}

	private List<Tag> simulateSearchResult(String tagName) {

		List<Tag> result = new ArrayList<Tag>();

		// iterate a list and filter by tagName
		for (Tag tag : data) {
			if (tag.getTagMessage().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}
}