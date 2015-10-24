package com.softserve.tc.diaryclient.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceConnection;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //do what you want with 
       authentication = SecurityContextHolder.getContext().getAuthentication();
       DiaryService port = DiaryServiceConnection.getDairyServicePort();
       port.updateSession(authentication.getName(), request.getSession(false).getId());
       response.sendRedirect("home");
    }
}

