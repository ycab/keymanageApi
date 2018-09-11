package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;

import com.example.keymanageapi.dao.ApplyRepository;
import com.example.keymanageapi.dao.CabinetRepository;
import com.example.keymanageapi.dao.GoodsManageRepository;
import com.example.keymanageapi.model.Apply;
import com.example.keymanageapi.model.Cabinet;
import com.example.keymanageapi.model.GoodsManage;
import com.example.keymanageapi.service.TemplateMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    private GoodsManageRepository goodsManageRepository;
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    TemplateMsgService templateMsgService;

    @GetMapping("/cabinetforgoods/getlist")
    public String getlist(HttpSession session)
    {
        String company=session.getAttribute("company").toString();
        List<Cabinet> list=cabinetRepository.findByCompany(company);
        String json= JSON.toJSONString(list);
        return json;
    }
    @PostMapping("/cabinetforgoods/edit")
    public String edit(HttpServletRequest request )
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {

        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String cabinetName=request.getParameter("cabinetName");
            String location=request.getParameter("location");

            Cabinet cabinet=cabinetRepository.findById(Integer.parseInt(id)).orElse(null);
            cabinet.setId(Integer.parseInt(id)); //该行代码是为了实现修改数据，去掉后会变成新增数据
            cabinet.setCabinetName(cabinetName);
            cabinet.setLocation(location);
            cabinetRepository.save(cabinet);
            List<GoodsManage> goodsManageList=goodsManageRepository.findByMac(cabinet.getMac());
            for(int i=0;i<goodsManageList.size();i++)
            {
                goodsManageList.get(i).setCabinetName(cabinetName);
                goodsManageRepository.save(goodsManageList.get(i));
            }

        }
        else if(oper.equals("del"))
        {

        }
        return "ok";
    }

    @GetMapping("/goods/getlist")
    public String getgoodslist(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Cabinet cabinets=cabinetRepository.findById(Integer.parseInt(id)).orElse(null);
        String mac=cabinets.getMac();
        List<GoodsManage> list= goodsManageRepository.findByMacOrderByCellNo(mac);
        String json= JSON.toJSONString(list);
        return json;

    }
    @PostMapping("/goods/edit")
    public String goodsedit(HttpServletRequest request)
    {
        String oper=request.getParameter("oper");
        if(oper.equals("add"))
        {

        }
        else if(oper.equals("edit"))
        {
            String id=request.getParameter("id");
            String goodName =request.getParameter("goodName");
            String needApproved=request.getParameter("needApproved");
            GoodsManage goodsManage=goodsManageRepository.findById(Integer.parseInt(id)).orElse(null);
            goodsManage.setGoodName(goodName);
            goodsManage.setNeedApproved(needApproved);
            goodsManageRepository.save(goodsManage);
        }
        else if(oper.equals("del"))
        {

        }
        return "ok";
    }

    @PostMapping("/pass")
    public String pass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Apply apply=applyRepository.findById(Integer.parseInt(id)).orElse(null);
        String mac=apply.getMac();
        Integer cellNo=apply.getCellNo();
        String username=apply.getUserName();
        String phone=apply.getPhone();
        List<GoodsManage> goodsManageList=goodsManageRepository.findByMacAndCellNo(mac,cellNo);
        goodsManageList.get(0).setUserName(username);
        goodsManageList.get(0).setPhone(phone);
        goodsManageList.get(0).setIsApply("0");
        goodsManageRepository.save(goodsManageList.get(0));
        apply.setIsApply("1");
        applyRepository.save(apply);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        templateMsgService.WeChatTemplateMsg2Service(apply.getOpenid(),"通过",df.format(new Date()));
        return "ok";
    }
    @PostMapping("/notpass")
    public String notpass(HttpServletRequest request)
    {
        String id=request.getParameter("id");
        Apply apply=applyRepository.findById(Integer.parseInt(id)).orElse(null);
        apply.setIsApply("2");
        applyRepository.save(apply);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        templateMsgService.WeChatTemplateMsg3Service(apply.getOpenid(),"未通过",df.format(new Date()));
        return "ok";
    }
    @GetMapping("/getapplylist")
    public String getapplylist(HttpServletRequest request, HttpSession session)
    {
        String company=session.getAttribute("company").toString();
        List<Apply> list=applyRepository.findByCompanyAndIsApply(company,"0");
        String json= JSON.toJSONString(list);
        return json;
    }


}
