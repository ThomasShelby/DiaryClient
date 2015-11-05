package com.softserve.tc.diaryclient.webservice.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.tc.diary.entity.Tag;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.controller.UserController;
import com.softserve.tc.diaryclient.log.Log;

@Service
public class DiaryServiceCashLoader {

  private Set<String> setOfHahsTags = new HashSet<String>();
  
  private static Logger logger = Log.init(UserController.class.toString());
  
  @Autowired
  public DiaryServiceCashLoader(DiaryServicePortProvider provider){
    logger.info("Created bean Cash for hahs-tags.");
    DiaryService port = provider.getPort();
    setSetOfHahsTags(port.getAllHashes());
    logger.info("Hash-tags loaded to cash.");
  }
  
  public List<Tag> getListOfHashTags() {
    List<Tag> result = new ArrayList<Tag>();
    for (String string : setOfHahsTags) {
      result.add(new Tag(string));
    }
    return result;
  }

  public void setSetOfHahsTags(Set<String> setOfHahses) {
    this.setOfHahsTags = setOfHahses;
  }

  public void addToCashOfHashTags(String tag){
      setOfHahsTags.add(tag);
  }

  public void removeFromCashOfHashTags(String tag){
      setOfHahsTags.remove(tag);
  }

}
