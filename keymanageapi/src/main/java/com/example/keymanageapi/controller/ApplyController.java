package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;
import com.example.keymanageapi.dao.ApplyRepository;
import com.example.keymanageapi.dao.GoodsManageRepository;
import com.example.keymanageapi.model.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/Apply")
public class ApplyController {
    @Autowired
    private GoodsManageRepository goodsManageRepository;
    @Autowired
    private ApplyRepository applyRepository;
    @GetMapping("/findByCompanyAndIsApply")
    public String findByCompany(HttpServletRequest request)
    {
        String company=request.getParameter("company");
        List<Apply> list=applyRepository.findByCompanyAndIsApply(company,"0");
        String json= JSON.toJSONString(list);
        return json;
    }
    @GetMapping("/pass")
    public String pass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Apply apply=applyRepository.findById(Integer.parseInt(id)).orElse(null);
        apply.setIsApply("1");
        applyRepository.save(apply);
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
    @PostMapping("/notpass")
    public String notpass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Apply apply=applyRepository.findById(Integer.parseInt(id)).orElse(null);
        apply.setIsApply("2");
        applyRepository.save(apply);
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
}
