//package com.softserve.tc.diaryclient.controller;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.softserve.tc.diary.webservice.DiaryService;
//import com.softserve.tc.diaryclient.dao.UserSessionDAO;
//import com.softserve.tc.diaryclient.entity.UserSession;
//import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;
//
//@Controller
//public class LogoutController {
//	
//	@Autowired
//	UserSessionDAO userSesDao;
//	
//    @Autowired
//    LogoutHandler securityContextLogoutHandler;
//    
//	private DiaryService port;
//	
//	@Autowired
//	public LogoutController(DiaryServicePortProvider diaryServicePortProvider) {
//		port = diaryServicePortProvider.getPort();
//	}
//    
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//
//        String nickName = request.getUserPrincipal().getName();
//        HttpSession httpSession = request.getSession(false);
//
//        String sessionId = httpSession.getId();
//        //UserSession us = userSesDao.findBySession(sessionId);
//        //us.setEndSession(new Date());
//        //userSesDao.update(us);
//
//        securityContextLogoutHandler.logout(request, response, null);
//
//        port.invalidateSession(nickName, sessionId);
//        
//        return "redirect:/";
//    }
//
//}
