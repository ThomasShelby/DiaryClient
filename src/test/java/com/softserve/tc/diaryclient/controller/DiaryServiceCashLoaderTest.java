package com.softserve.tc.diaryclient.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceCashLoader;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@PrepareForTest({DiaryServicePortProvider.class, DiaryService.class})
@RunWith(PowerMockRunner.class)
public class DiaryServiceCashLoaderTest{
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @SuppressWarnings("static-access")
  @Test
  public final void testAddRecordPost(){
    
    Set<String> setOfHahsTags = new HashSet<String>();
    setOfHahsTags.add("Hello");
    setOfHahsTags.add("France");
    setOfHahsTags.add("Family");
    
    Set<String> setOfHahsTagsActual = new HashSet<String>();
    setOfHahsTagsActual.add("Hello");
    setOfHahsTagsActual.add("France");
    setOfHahsTagsActual.add("Family");
    setOfHahsTagsActual.add("New");
    
    PowerMockito.mockStatic(DiaryService.class);
    PowerMockito.mockStatic(DiaryServicePortProvider.class);
    
    DiaryService portMocked = Mockito.mock(DiaryService.class);
    DiaryServicePortProvider providerMocked = Mockito.mock(DiaryServicePortProvider.class);
    when(providerMocked.getPort()).thenReturn(portMocked);
    DiaryServiceCashLoader cashLoader = new DiaryServiceCashLoader(providerMocked); 
    
    when(portMocked.getAllHashes()).thenReturn(setOfHahsTags);
    cashLoader.setSetOfHahsTags(setOfHahsTags);
    cashLoader.addToCashOfHashTags("New");
    
    assertEquals(setOfHahsTagsActual, setOfHahsTags);
    

  }
}