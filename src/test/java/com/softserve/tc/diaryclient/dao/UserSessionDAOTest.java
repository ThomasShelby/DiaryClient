package com.softserve.tc.diaryclient.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.softserve.tc.diaryclient.entity.UserSession;

public class UserSessionDAOTest {
    private static ConfigurableApplicationContext context;
    private static UserSessionDAO sessionDAO;
    private static UserSession testRecord;
    
    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        context =
                new ClassPathXmlApplicationContext("/META-INF/appContext.xml");
        sessionDAO =
                (UserSessionDAO) context.getBean("userSessionDAO");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        context.close();
    }
    
    @Before
    public void beforeTest() throws SQLException {
        testRecord =
                new UserSession("testRec", "kidygfr", new Date());
    }
    
    @After
    public void afterTest() throws SQLException {
        sessionDAO.deleteByNickName(testRecord.getNickName());
        
    }
    
    @Test
    public void deleteByNickNameTest() {
        sessionDAO.create(testRecord);
        sessionDAO.deleteByNickName(testRecord.getNickName());
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void deleteByObjectTest() {
        sessionDAO.create(testRecord);
        sessionDAO.delete(testRecord);
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void createTest() {
        sessionDAO.create(testRecord);
        UserSession expectedRecord =
                sessionDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByNickNameTest() {
        sessionDAO.create(testRecord);
        UserSession expectedRecord =
                sessionDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByUuidTest() {
        sessionDAO.create(testRecord);
        UserSession expectedRecord =
                sessionDAO.find(testRecord.getUuid());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void updateTest() {
        sessionDAO.create(testRecord);
        testRecord.setEndSession(new Date());
        testRecord.setSessionNumber("kuhgeee234");
        sessionDAO.update(testRecord);
        UserSession expectedRecord =
                sessionDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void getAllTest() {
        sessionDAO.create(testRecord);
        List<UserSession> list = sessionDAO.getAll();
        assertEquals(list.size(), 9);
    }
}
