package com.softserve.tc.diaryclient.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.entity.UserSession;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //do what you want with 
       authentication = SecurityContextHolder.getContext().getAuthentication();
       DiaryService port = DiaryServicePortProvider.getPort();
       String session = port.updateSession(authentication.getName(), request.getSession(false).getId());
       ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/appContext.xml");
		UserSessionDAO userSessionDAO = (UserSessionDAO)context.getBean("userSessionDAO");
		userSessionDAO.create(new UserSession(authentication.getName(), session, new Date()));
       response.sendRedirect("home");
    }
       
}
