package com.softserve.tc.diaryclient.controller;

import java.sql.SQLException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JSONException.class)
    public ModelAndView handleJSONException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    @ExceptionHandler(NoResultException.class)
    public ModelAndView handleNoResultException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("globalException");
        return modelAndView;
    }
    
    @ExceptionHandler(SQLException.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
}
