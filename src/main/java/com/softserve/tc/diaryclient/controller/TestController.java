package com.softserve.tc.diaryclient.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.softserve.tc.diaryclient.log.Log;

@Controller
public class TestController {
    private static Logger logger = Log.init(UserController.class.toString());
    
    @RequestMapping(value="/test/{id}", method=RequestMethod.GET)
    public String getError(@PathVariable("id") int id, Model model) throws Exception {
        if (id==1) {
            throw new SQLException("SQLException");
        } else if (id==2) {
            throw new IOException("IOException");
        } else if (id==3) {
            throw new FileNotFoundException("FileNotFound");
        } else if (id==4) {
            throw new JSONException("JSON Exception");
        }
        return "home";
    }
    
    @ExceptionHandler(IOException.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("msg", "Something happened");
        modelAndView.setViewName("exception");
        return modelAndView;
    }
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="FileNotFound")
    @ExceptionHandler(FileNotFoundException.class)
    public void handelFileNotFound() {
        logger.error("FileNotFound Exception");
    }
}
