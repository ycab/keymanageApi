package com.example.keymanageapi.service;



import com.example.keymanageapi.dao.PeopleManageRepository;
import com.example.keymanageapi.model.PeopleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 验证手机号是否已被注册$
 * @Param: phone$
 * @return: 被注册未true，未被注册为false$
 * @Author: your name
 * @date: 2018.8.24$
 */
@Service
public class PhoneService {
    @Autowired
    private PeopleManageRepository peopleManageRepository;
    public boolean assertPhoneExist(String phone)
    {
        List<PeopleManage> peopleManageList=peopleManageRepository.findByPhone(phone);
        if(peopleManageList.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
