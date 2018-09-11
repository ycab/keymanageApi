package com.example.keymanageapi.controller;


import com.example.keymanageapi.dao.PeopleManageRepository;
import com.example.keymanageapi.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/Login")
public class TestController {
    @Autowired
    private PeopleManageRepository c;
    @PostMapping(value="/test")
    public String Login(HttpServletRequest request )
    {
        String a=request.getParameter("username");
        String b=request.getParameter("pwd");
        List<PeopleManage> d=c.selectall();
        return "ok";
    }
}
