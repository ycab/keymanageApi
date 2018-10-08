package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanageapi.dao.PeopleManageRepository;
import com.example.keymanageapi.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Description: 超级管理员$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/supermanage")
public class SuperManageController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @GetMapping("/getlist")
    public String getlist()
    {
        List<PeopleManage> list=peopleManageRepository.findByAuthority("超级管理员");
        String json=JSON.toJSONString(list);
       return json;
    }
    @PostMapping("/add")
    public String add(HttpServletRequest request )
    {
        String name=request.getParameter("userName");
        String password=request.getParameter("password");
        String company=request.getParameter("company");
        String department=request.getParameter("department");
        String phone=request.getParameter("phone");
        String authority="超级管理员";
        PeopleManage user=new PeopleManage();
        user.setUserName(name);
        user.setPassword(password);
        user.setCompany(company);
        user.setDepartment(department);
        user.setPhone(phone);
        user.setAuthority(authority);
        user.setIsConfirm("1");
        peopleManageRepository.save(user);

        return "ok";
    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request )
    {
        String id=request.getParameter("id");
        String name=request.getParameter("userName");
        String password=request.getParameter("password");
        String company=request.getParameter("company");
        String department=request.getParameter("department");
        String phone=request.getParameter("phone");
        String authority="超级管理员";
        PeopleManage user=peopleManageRepository.findById(Integer.parseInt(id)).orElse(null);
        user.setUserName(name);
        user.setPassword(password);
        user.setCompany(company);
        user.setDepartment(department);
        user.setPhone(phone);
        user.setAuthority(authority);
        peopleManageRepository.save(user);
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
    @PostMapping("/del")
    public String del(HttpServletRequest request )
    {
        String[] idArray=null;
        idArray=request.getParameter("id").split(",");
        for(int i=0;i<idArray.length;i++)
        {
            peopleManageRepository.deleteById(Integer.parseInt(idArray[i]));
        }
        return "ok";
    }
}
