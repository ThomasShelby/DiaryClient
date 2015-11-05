package com.softserve.tc.diaryclient.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.tc.diary.webservice.DiaryService;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

  @Test
  public void testGetDaysWichHaveRecordsPerMonth(){
    String date = "2015-10-28";
    String userNickName = "mary";

    DiaryService port = Mockito.mock(DiaryService.class);

    List<String> dates = new ArrayList<String>();
    dates.add("2015-10-01");
    dates.add("2015-10-10");
    dates.add("2015-10-15");
    dates.add("2015-10-27");
    dates.add("2015-10-28");

    when(port.getDatesWithRecordsPerMonth(userNickName, date)).thenReturn(dates);
    assertEquals(dates, port.getDatesWithRecordsPerMonth(userNickName, date));
  }

}
