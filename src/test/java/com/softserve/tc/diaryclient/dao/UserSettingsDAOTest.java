package com.softserve.tc.diaryclient.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserve.tc.diaryclient.entity.Language;
import com.softserve.tc.diaryclient.entity.Theme;
import com.softserve.tc.diaryclient.entity.TimeFormat;
import com.softserve.tc.diaryclient.entity.UserSettings;
import com.softserve.tc.diaryclient.entity.YesNo;

public class UserSettingsDAOTest {
    private static ConfigurableApplicationContext context;
    private static UserSettingsDAO settingsDAO;
    private static UserSettings testRecord;
    
    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        context =
                new ClassPathXmlApplicationContext("/META-INF/appContext.xml");
        settingsDAO =
                (UserSettingsDAO) context.getBean("userSettingsDAO");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        context.close();
    }
    
    @Before
    public void beforeTest() throws SQLException {
        testRecord =
                new UserSettings("testRec", Theme.DEFAULT, 5, YesNo.YES,
                        YesNo.YES, Language.ENG, TimeFormat.AMPM);
    }
    
    @After
    public void afterTest() throws SQLException {
        settingsDAO.deleteByNickName(testRecord.getNickName());
        
    }
    
    @Test
    public void deleteByNickNameTest() {
        settingsDAO.create(testRecord);
        settingsDAO.deleteByNickName(testRecord.getNickName());
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void deleteByObjectTest() {
        settingsDAO.create(testRecord);
        settingsDAO.delete(testRecord);
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void createTest() {
        settingsDAO.create(testRecord);
        UserSettings expectedRecord =
                settingsDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByNickNameTest() {
        settingsDAO.create(testRecord);
        UserSettings expectedRecord =
                settingsDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByUuidTest() {
        settingsDAO.create(testRecord);
        UserSettings expectedRecord =
                settingsDAO.find(testRecord.getUuid());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void updateTest() {
        settingsDAO.create(testRecord);
        testRecord.setLanguage(Language.UKR);
        ;
        testRecord.setNumberOfRecordsToShow(50);
        settingsDAO.update(testRecord);
        UserSettings expectedRecord =
                settingsDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void getAllTest() {
        settingsDAO.create(testRecord);
        List<UserSettings> list = settingsDAO.getAll();
        assertEquals(list.size(), 3);
    }
}
