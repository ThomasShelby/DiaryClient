package com.softserve.tc.diaryclient.logout;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.dao.UserSessionDAO;
import com.softserve.tc.diaryclient.entity.UserSession;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;


public class UserLogoutHandler implements LogoutHandler {

    private boolean invalidateHttpSession = true;
    private boolean clearAuthentication = true;

    public UserLogoutHandler( ) {}

    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        HttpSession httpSession = request.getSession(false);
        User activeUser = (User) authentication.getPrincipal();
        String nickName = activeUser.getUsername();
     
        String sessionId = httpSession.getId();
        
        if (invalidateHttpSession) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        
        if (clearAuthentication) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(null);
        }

        SecurityContextHolder.clearContext();
        
        DiaryService port = DiaryServicePortProvider.getPort();
        port.invalidateSession(nickName, sessionId);
        
        ConfigurableApplicationContext contextConn = new ClassPathXmlApplicationContext(
                "/META-INF/appContext.xml");
        UserSessionDAO userSessionDAO = (UserSessionDAO)contextConn.getBean("userSessionDAO");
        UserSession userSession = userSessionDAO.findBySession(sessionId);
        userSession.setEndSession(new Date());
        userSessionDAO.update(userSession);
        
        try {
            response.sendRedirect("/DiaryClient/login");
        } catch (IOException e) {
            System.out.println("PROBLEM!!!!*****************************************************************");
            e.printStackTrace();
        }
        
//        String ajaxRequestHeader = request.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equals(ajaxRequestHeader)) {
//            try {
//                response.sendRedirect("/login.jsp");
//            } catch (IOException e) {
//               System.out.println("PROBBLEEEMMMM!!!*************************");
//                e.printStackTrace();
//            }
//        }
        
    }

   
    
    public boolean isInvalidateHttpSession() {
        return invalidateHttpSession;
    }

    public void setInvalidateHttpSession(boolean invalidateHttpSession) {
        this.invalidateHttpSession = invalidateHttpSession;
    }

    public void setClearAuthentication(boolean clearAuthentication) {
        this.clearAuthentication = clearAuthentication;
    }
}
