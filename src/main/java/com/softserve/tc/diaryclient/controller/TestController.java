package com.softserve.tc.diaryclient.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
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
}
