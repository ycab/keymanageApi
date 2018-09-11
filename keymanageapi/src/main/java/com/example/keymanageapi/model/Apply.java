package com.example.keymanageapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description: $
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: $
 */
@Entity
public class Apply {
    @Id
    @GeneratedValue
    private Integer id;
    private String goodName;//物品名
    private String location;//柜子位置
    private Timestamp startTime;//开始时间
    private Timestamp endTime;//结束时间
    private String company;//公司名
    private String goodNo;//物品编号
    private Integer cellNo;//箱格编号
    private String mac;//柜子mac
    private String phone;//电话
    private String userName;//使用者姓名
    private String rfid;
    private String cabinetName;//柜子名称
    private String openid;//
    private String isApply;//申请状态，0为申请中，1为申请通过，2为申请未通过

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public Integer getCellNo() {
        return cellNo;
    }

    public void setCellNo(Integer cellNo) {
        this.cellNo = cellNo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }
}
