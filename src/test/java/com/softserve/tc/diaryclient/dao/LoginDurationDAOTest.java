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

import com.softserve.tc.diaryclient.entity.LoginDuration;

public class LoginDurationDAOTest {
    private static ConfigurableApplicationContext context;
    private static LoginDurationDAO loginDurationDAO;
    private static LoginDuration testRecord;
    
    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        context =
                new ClassPathXmlApplicationContext("/META-INF/appContext.xml");
        loginDurationDAO =
                (LoginDurationDAO) context.getBean("loginDurationDAO");
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        context.close();
    }
    
    @Before
    public void beforeTest() throws SQLException {
        testRecord =
                new LoginDuration("testRec", new Date(), 2365.32);
    }
    
    @After
    public void afterTest() throws SQLException {
        loginDurationDAO.deleteByNickName(testRecord.getNickName());
        
    }
    
    @Test
    public void deleteByNickNameTest() {
        loginDurationDAO.create(testRecord);
        loginDurationDAO.deleteByNickName(testRecord.getNickName());
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void deleteByObjectTest() {
        loginDurationDAO.create(testRecord);
        loginDurationDAO.delete(testRecord);
        // assertNull(statisticDAO.findByNickName(userStat.getNickName()));
    }
    
    @Test
    public void createTest() {
        loginDurationDAO.create(testRecord);
        LoginDuration expectedRecord =
                loginDurationDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByNickNameTest() {
        loginDurationDAO.create(testRecord);
        LoginDuration expectedRecord =
                loginDurationDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void findByUuidTest() {
        loginDurationDAO.create(testRecord);
        LoginDuration expectedRecord =
                loginDurationDAO.find(testRecord.getUuid());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void updateTest() {
        loginDurationDAO.create(testRecord);
        testRecord.setDuration(123.03);
        loginDurationDAO.update(testRecord);
        LoginDuration expectedRecord =
                loginDurationDAO.findByNickName(testRecord.getNickName());
        assertTrue(testRecord.equals(expectedRecord));
    }
    
    @Test
    public void getAllTest() {
        loginDurationDAO.create(testRecord);
        List<LoginDuration> list = loginDurationDAO.getAll();
        assertEquals(list.size(), 3);
    }
}
