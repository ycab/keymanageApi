package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanageapi.dao.PeopleManageRepository;
import com.example.keymanageapi.model.PeopleManage;
import com.example.keymanageapi.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 管理员管理$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @Autowired
    private PhoneService phoneService;
    @GetMapping("/getlist")
    public String getlist(HttpServletRequest request)
    {
        String company=request.getParameter("company");
        List<PeopleManage> list=peopleManageRepository.findByCompanyAndAuthority(company,"管理员");
        String json= JSON.toJSONString(list);
        // String a="[{\"id\":\"1\",\"name\":\"管理员3\",\"password\":\"123\",\"department\":\"南京理工大学\",\"phone\":\"1216679910\",\"company\":\"南理工\",\"authority\":\"管理员\"}]";
        return json;
    }
    @PostMapping("/add")
    public String add(HttpServletRequest request, HttpSession session )
    {
            String name=request.getParameter("userName");
            String password=request.getParameter("password");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String company=request.getParameter("company");
            String result="";
            boolean phoneExist=phoneService.assertPhoneExist(phone);
            if(phoneExist==true)
            {
                result="{"+"\"msg\":\"error\""+","+"\"info\":"+"\"号码已注册\""+"}";
                return result;
            }
            else {
                String authority = "管理员";
                PeopleManage user = new PeopleManage();
                user.setUserName(name);
                user.setPassword(password);
                user.setCompany(company);
                user.setDepartment(department);
                user.setPhone(phone);
                user.setAuthority(authority);
                user.setIsConfirm("1");
                peopleManageRepository.save(user);
            }
            result="{"+"\"msg\":\"ok\""+"}";
            return result;
    }
    @PostMapping("/edit")
    public String edit(HttpServletRequest request, HttpSession session )
    {
            String id=request.getParameter("id");
            String name=request.getParameter("userName");
            String password=request.getParameter("password");
            String department=request.getParameter("department");
            String phone=request.getParameter("phone");
            String result="";
            boolean phoneExist=phoneService.assertPhoneExist(phone);
            PeopleManage user=peopleManageRepository.findById(Integer.parseInt(id)).orElse(null);
            if(phoneExist==true&&(phone.equals(user.getPhone())==false))
            {
                result="{"+"\"msg\":\"error\""+","+"\"info\":"+"\"号码已注册\""+"}";
                return result;
            }
            else{
                user.setUserName(name);
                user.setPassword(password);
                user.setDepartment(department);
                user.setPhone(phone);
                peopleManageRepository.save(user);
            }
        result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
    @PostMapping("/del")
    public String del(HttpServletRequest request, HttpSession session )
    {

        String[] idArray=null;
        idArray=request.getParameter("id").split(",");
        for(int i=0;i<idArray.length;i++)
        {
            peopleManageRepository.deleteById(Integer.parseInt(idArray[i]));
        }
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
}
