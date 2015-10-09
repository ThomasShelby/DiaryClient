package com.softserve.tc.diaryclient.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserve.tc.diaryclient.entity.UserStatistic;

public class UserStatisticDAOTest {
    private static ConfigurableApplicationContext context;
    private static UserStatisticDAO statisticDAO;
    private static UserStatistic testRecord;
    
    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        context =
                new ClassPathXmlApplicationContext("/META-INF/appContext.xml");
        statisticDAO =
                (UserStatisticDAO) context.getBean("userStatisticDAO");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        context.close();
    }
    
    @Before
    public void beforeTest() throws SQLException {
        testRecord =
                new UserStatistic("testRecord", 2, 3, "Sun, live", new Date());
    }
    
    @After
    public void afterTest() throws SQLException {
        statisticDAO.deleteByNickName(testRecord.getNickName());
        
    }
    
    @Test
    public void deleteByNickNameTest() {
        statisticDAO.create(testRecord);
        statisticDAO.deleteByNickName(testRecord.getNickName());
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void deleteByObjectTest() {
        statisticDAO.create(testRecord);
        statisticDAO.delete(testRecord);
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void createTest() {
        statisticDAO.create(testRecord);
        UserStatistic expectedRecord =
                statisticDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByNickNameTest() {
        statisticDAO.create(testRecord);
        UserStatistic expectedRecord =
                statisticDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByUuidTest() {
        statisticDAO.create(testRecord);
        UserStatistic expectedRecord =
                statisticDAO.find(testRecord.getUuid());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void updateTest() {
        statisticDAO.create(testRecord);
        testRecord.setLastRecords("SEA");
        testRecord.setNumberOfLogins(10);
        statisticDAO.update(testRecord);
        UserStatistic expectedRecord =
                statisticDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void getAllTest() {
        statisticDAO.create(testRecord);
        List<UserStatistic> list = statisticDAO.getAll();
        assertEquals(list.size(), 3);
    }
}
