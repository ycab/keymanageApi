package com.example.keymanageapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description: 物品管理$
 * @Param: $
 * @return: $
 * @Author: yu peng
 * @date: 2018.8.13$
 */
@Entity
public class GoodsManage {
    @Id
    @GeneratedValue
    private Integer id;
    private String goodName;         //物品名称
    private String mac;           //物品mac
    private String company;      //公司
    private String location;     //柜子位置
    private String needApproved;//是否需要审批
    private String rfid;         //rfid
    private String userName;     //当前使用者姓名
    private String phone;    //当前使用者电话
    private String goodsStatus; //物品状态 0为可借 1为成功申请还未使用 2为使用中
    private String doorStatus;  //箱格状态 0为门关闭 1为门打开
    private String isApply;
    private String cabinetName;//柜子名称
    private Integer cellNo;//箱格编号

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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNeedApproved() {
        return needApproved;
    }

    public void setNeedApproved(String needApproved) {
        this.needApproved = needApproved;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getCellNo() {
        return cellNo;
    }

    public void setCellNo(Integer cellNo) {
        this.cellNo = cellNo;
    }
}
