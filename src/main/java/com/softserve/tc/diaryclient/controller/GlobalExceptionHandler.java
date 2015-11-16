package com.softserve.tc.diaryclient.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.service.MailSender;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static Logger logger = Log.init(UserController.class.toString());
    
    @ExceptionHandler(JSONException.class)
    public ModelAndView handleJSONException(HttpServletRequest request,
            Exception ex) {
        logger.error("Exception", ex);
        sendMail(request, ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    @ExceptionHandler(NoResultException.class)
    public ModelAndView handleNoResultException(HttpServletRequest request,
            Exception ex) {
        logger.error("Exception", ex);
        sendMail(request, ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("globalException");
        return modelAndView;
    }
    
    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(HttpServletRequest request,
            Exception ex) {
        logger.error("Exception", ex);
        sendMail(request, ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(HttpServletRequest request,
            Exception ex) {
        logger.error("Exception", ex);
        sendMail(request, ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView handleFileNotFoundException(HttpServletRequest request,
            Exception ex) {
        logger.error("Exception", ex);
        sendMail(request, ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    private void sendMail(HttpServletRequest request, Exception ex) {
        MailSender mail = MailSender.getInstance();
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a");
        mail.setParameters("Attention! Exception: " + ex.getMessage(),
                "Username: " + request.getUserPrincipal().getName() + "\nIP: "
                        + request.getRemoteAddr() + "\nTime: " + ft.format(dNow)
                        + "\nUser agent: " + request.getHeader("user-agent")
                        + "\nURL: " + request.getRequestURL() + "\n"
                        + ex.getMessage() + " stack trace: \n"
                        + exceptionAsString,
                "admin@thediary.xyz");
        Thread t = new Thread(mail);
        t.start();
    }
}
