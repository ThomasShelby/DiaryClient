package com.softserve.tc.diaryclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.softserve.tc.diaryclient.webservice.diary.CashBeanGetter;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceCashLoader;

@Controller
public class AutocompleteController {

//	private static List<Tag> data = new ArrayList<Tag>();
	private static List<String> data = new ArrayList<String>();
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody
	String getTags(@RequestParam String tag) {
		if (tag.startsWith("#")) {
			//get data directly from service
			//DiaryService port = DiaryServiceConnection.getDairyServicePort();
			//data = port.getListTagsBPrefix(tag);

			//get data (hashes) from client cash
			DiaryServiceCashLoader loader = CashBeanGetter.getInstance();
			data = loader.getListOfHashTagsFromCash();

			return new Gson().toJson(simulateSearchResult(tag));	
		}
		return null;
	}

	private List<String> simulateSearchResult(String tagName) {

		List<String> result = new ArrayList<String>();

		// iterate a list and filter by tagName
		for (String tag : data) {
			if (tag.contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}
}