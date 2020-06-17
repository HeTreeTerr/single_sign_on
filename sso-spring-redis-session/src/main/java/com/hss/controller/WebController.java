package com.hss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/service")
public class WebController {

    @RequestMapping("setMsg")
    public String  getMsg(HttpSession session) {

        session.setAttribute("msg", "Hello SpringSession!");
        return "ok";
    }

    @RequestMapping("getMsg")
    public String  setMsg(HttpSession session) {

        String msg=(String) session.getAttribute("msg");
        return msg;
    }
}
