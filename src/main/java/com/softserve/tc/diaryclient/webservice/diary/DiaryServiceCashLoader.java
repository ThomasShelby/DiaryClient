package com.softserve.tc.diaryclient.webservice.diary;

import java.util.ArrayList;
import java.util.List;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.controller.UserController;
import com.softserve.tc.diaryclient.log.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceCashLoader {

	private List<String> listOfHahses = new ArrayList<String>();
	private static Logger logger = Log.init(UserController.class.toString());
	
	@Autowired
	public DiaryServiceCashLoader(DiaryServicePortProvider provider){
		logger.info("Created bean Cash fot hahs-tags.");
		@SuppressWarnings("static-access")
		DiaryService port = provider.getPort();
		listOfHahses = port.getAllHashes();
		logger.info("Hash-tags loaded to cash.");
	}

	public void addToCashOfHashTags(String tag){
		if (!listOfHahses.contains(tag)){
			listOfHahses.add(tag);
		}
	}

	public void removeFromCashOfHashTags(String tag){
		if (listOfHahses.contains(tag)){
			listOfHahses.remove(tag);
		}
	}

	public List<String> getListOfHashTagsFromCash() {
		return listOfHahses;
	}
}
