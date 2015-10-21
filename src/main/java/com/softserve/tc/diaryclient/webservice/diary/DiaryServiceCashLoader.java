package com.softserve.tc.diaryclient.webservice.diary;

import java.util.List;

import com.softserve.tc.diary.webservice.DiaryService;

public class DiaryServiceCashLoader {

	DiaryService port = DiaryServiceConnection.getDairyServicePort();
	private List<String> listOfHahses = port.getAllHashes();

	public List<String> getListOfHashTagsFromCash() {
		return listOfHahses;
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

}
