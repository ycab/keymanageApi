package com.example.keymanageapi.controller;

import com.alibaba.fastjson.JSON;

import com.example.keymanageapi.dao.CabinetRepository;
import com.example.keymanageapi.dao.GoodsManageRepository;
import com.example.keymanageapi.model.Cabinet;
import com.example.keymanageapi.model.GoodsManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@RestController
@RequestMapping("/Cabinet")
public class CabinetController {
    @Autowired
    private CabinetRepository cabinetRepository;
    @Autowired
    private GoodsManageRepository goodsManageRepository;
    @Autowired
    @GetMapping("/getAll")
    public String getlist()
    {
        List<Cabinet> list=cabinetRepository.findAll();
        String json= JSON.toJSONString(list);
        return json;
    }
    @PostMapping("/add")
    public String add(HttpServletRequest request ) {
            String mac = request.getParameter("mac");
            String company = request.getParameter("company");
            String cabinetName = request.getParameter("cabinetName");
            String location = request.getParameter("location");
            String numofdoor = request.getParameter("numofdoor");
            Cabinet cabinet = new Cabinet();
            cabinet.setMac(mac);
            cabinet.setCompany(company);
            cabinet.setCabinetName(cabinetName);
            cabinet.setLocation(location);
            cabinet.setNumofdoor(Integer.parseInt(numofdoor));
            cabinetRepository.save(cabinet);
            for (int i = 0; i < Integer.parseInt(numofdoor); i++) {
                GoodsManage goodsManage = new GoodsManage();
                goodsManage.setMac(mac);
                goodsManage.setCellNo(i + 1);
                goodsManage.setCabinetName(cabinetName);
                goodsManage.setCompany(company);
                goodsManage.setLocation(location);
                goodsManage.setIsApply("1");
                goodsManageRepository.save(goodsManage);
            }
          String result="{"+"\"msg\":\"ok\""+"}";
          return result;
    }

    @PostMapping("/edit")
    public String edit(HttpServletRequest request ) {
        String id=request.getParameter("id");
        String mac=request.getParameter("mac");
        String company=request.getParameter("company");
        String cabinetName=request.getParameter("cabinetName");
        String location=request.getParameter("location");
        Cabinet cabinetfromdatabase=cabinetRepository.findById(Integer.parseInt(id)).orElse(null);
        String  macfromdatabase=cabinetfromdatabase.getMac();
        Cabinet cabinet=new Cabinet();
        cabinet.setId(Integer.parseInt(id)); //该行代码是为了实现修改数据，去掉后会变成新增数据
        cabinet.setMac(mac);
        cabinet.setCompany(company);
        cabinet.setCabinetName(cabinetName);
        cabinet.setLocation(location);
        cabinet.setNumofdoor(cabinetfromdatabase.getNumofdoor());
        cabinetRepository.save(cabinet);
        if(macfromdatabase.equals(mac))
        {

        }
        else
        {
            List<GoodsManage> list= goodsManageRepository.findByMac(macfromdatabase);
            for(int i=0;i<list.size();i++)
            {
                list.get(i).setMac(mac);
                goodsManageRepository.save(list.get(i));
            }
        }
        List<GoodsManage> goodsManageList=goodsManageRepository.findByMac(mac);
        for(int i=0;i<goodsManageList.size();i++)
        {
            goodsManageList.get(i).setCabinetName(cabinetName);
            goodsManageList.get(i).setCompany(company);
            goodsManageList.get(i).setLocation(location);
            goodsManageRepository.save(goodsManageList.get(i));
        }
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }
    @PostMapping("/del")
    public String del(HttpServletRequest request ) {
        String[] idArray=null;
        idArray=request.getParameter("id").split(",");
        for(int i=0;i<idArray.length;i++)
        {
            Cabinet cabinet=cabinetRepository.findById(Integer.parseInt(idArray[i])).orElse(null);
            cabinetRepository.deleteById(Integer.parseInt(idArray[i]));
            String mac=cabinet.getMac();
            List<GoodsManage> list= goodsManageRepository.findByMac(mac);
            for(int j=0;j<list.size();j++)
            {
                goodsManageRepository.deleteById(list.get(j).getId());
            }
        }
        String result="{"+"\"msg\":\"ok\""+"}";
        return result;
    }

}
