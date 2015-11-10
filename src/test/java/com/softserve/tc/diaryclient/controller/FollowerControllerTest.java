package com.softserve.tc.diaryclient.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.tc.diary.entity.Role;
import com.softserve.tc.diary.entity.Sex;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;

@RunWith(MockitoJUnitRunner.class)
public class FollowerControllerTest {
    
    DiaryService port = Mockito.mock(DiaryService.class);
    
    @Test
    public void testGetAllFollowedUsers() {
        List<User> usersList = new ArrayList<User>();
        usersList.add(new User("JoJo", "Bob", "Davis", null, "g@h.com",
                "qwerty", Sex.MALE, "1992-12-12", "jhj.jpg", Role.USER));
        usersList.add(new User("Vinni", "Sara", "Parker", null, "sara@h.com",
                "1111", Sex.FEMALE, "1995-12-12", "4562.jpg", Role.USER));
        when(port.getAllFollowedUsers("11111")).thenReturn(usersList);
        assertEquals(usersList, port.getAllFollowedUsers("11111"));
        verify(port, times(1)).getAllFollowedUsers("11111");
        verify(port).getAllFollowedUsers(Matchers.eq("11111"));
        
    }
    
    @Test
    public void testGetUserByNickName() {
        User user = new User("JoJo", "Bob", "Davis", null, "g@h.com",
                "qwerty", Sex.MALE, "1992-12-12", "jhj.jpg", Role.USER);
        when(port.getUserByNickName("JoJo")).thenReturn(user);
        assertEquals(user, port.getUserByNickName("JoJo"));
        verify(port, times(1)).getUserByNickName("JoJo");
        verify(port).getUserByNickName(Matchers.eq("JoJo"));
        
    }
    
    @Test
    public void testMarkAsViwed() {
        doNothing().when(port).markAsViwed("1111");
        port.markAsViwed("1111");
        verify(port).markAsViwed("1111");
        
    }
    
    @Test
    public void testIsThereAvalableNewRecords() {
        when(port.isThereAvalableNewRecords("1111")).thenReturn(true);
        assertTrue(port.isThereAvalableNewRecords("1111"));
        verify(port).isThereAvalableNewRecords("1111");
        
    }
    
}
