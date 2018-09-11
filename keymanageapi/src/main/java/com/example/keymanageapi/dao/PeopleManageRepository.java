package com.example.keymanageapi.dao;



import com.example.keymanageapi.model.PeopleManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleManageRepository extends JpaRepository<PeopleManage,Integer> {
    public List<PeopleManage> findByAuthority(String authority);
    public List<PeopleManage> findByPhoneAndPasswordAndIsConfirm(String phone, String password, String iscomfirm);
    @Query(value = "select * from people_manage",nativeQuery = true)
    public List<PeopleManage> selectall();
    public List<PeopleManage> findByPhone(String phone);
    public List<PeopleManage> findByAuthorityAndIsConfirm(String authority, String iscomfirm);
    public List<PeopleManage> findByCompanyAndAuthority(String company, String authority);
    public List<PeopleManage> findByCompanyAndAuthorityAndIsConfirm(String company, String authority, String iscomfirm);
}
