package com.example.keymanageapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Description: 柜子$
 * @Param: $
 * @return: $
 * @Author: your name
 * @date: 2018.8.15$
 */
@Entity
public class Cabinet {
    @Id
    @GeneratedValue
    private Integer id;
    private String mac;
    private String company;
    private String cabinetName;
    private String location;  //柜子地址
    private Integer numofdoor; //箱格数量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumofdoor() {
        return numofdoor;
    }

    public void setNumofdoor(Integer numofdoor) {
        this.numofdoor = numofdoor;
    }
}
