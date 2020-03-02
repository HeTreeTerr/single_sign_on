package hss.sso.jwt.controller;

import hss.sso.jwt.entity.ReturnEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/helloPage")
    public String helloPage(Map<String, Object> map){
        map.put("name","hss");
        return "hello";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnEntity add(){

        Map<String,Object> map = new LinkedHashMap<String, Object>();
        map.put("type","add");
        return ReturnEntity.successResult(map);
    }

    @GetMapping(value = "/find")
    @ResponseBody
    public ReturnEntity find(){

        Map<String,Object> map = new LinkedHashMap<String, Object>();
        map.put("type","find");
        return ReturnEntity.successResult(map);
    }
}
