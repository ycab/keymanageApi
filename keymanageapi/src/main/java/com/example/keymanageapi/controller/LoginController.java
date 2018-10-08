package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanageapi.Util.WeiXinUtil;
import com.example.keymanageapi.dao.PeopleManageRepository;
import com.example.keymanageapi.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LoginController {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    @GetMapping(value="/checkinfo")
    public String Login(HttpServletRequest request, HttpSession session, Model model)
    {
        String phone=request.getParameter("phone");
        String pwd=request.getParameter("pwd");
        String result="";
        List<PeopleManage> peopleManageList=peopleManageRepository.findByPhoneAndPasswordAndIsConfirm(phone,pwd,"1");
        if(peopleManageList.size()==1)
        {
            if(peopleManageList.get(0).getAuthority().equals("用户"))
            {
                result="{"+"\"msg\":\"error\""+","+"\"info\":"+"\"普通用户无权登录\""+"}";
            }
            else {
                PeopleManage a=peopleManageList.get(0);
                String json= JSON.toJSONString(peopleManageList.get(0));
                result="{"+"\"msg\":\"ok\""+","+"\"info\":"+json+"}";
            }

        }
        else {
            result="{"+"\"msg\":\"error\""+","+"\"info\":"+"\"账号或密码错误\""+"}";
        }
        return result;
    }
}
