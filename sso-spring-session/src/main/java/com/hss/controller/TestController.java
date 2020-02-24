package com.hss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response){

        Object testAttrValue = request.getSession().getAttribute("testAttrName");

        if(null == testAttrValue){
            request.getSession().setAttribute("testAttrName","testAttrValue");
        }

        System.out.println("8080:"+testAttrValue);
        return "index";
    }
}
