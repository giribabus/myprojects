package com.todo.model;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel implements java.io.Serializable {

    private static final long serialVersionUID = 4899474188802229546L;

    private Integer userId;
    private String userName;
    private String password;
    private String firstNm;
    private String lastNm;
    private String email;
    private String mobile;
    private String status;
    private Integer deptId;
    
    private List<Group> groups = new ArrayList<Group>();

    public User() {
    }

    public Integer getUserId() {
	return this.userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public String getUserName() {
	return this.userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstNm() {
	return this.firstNm;
    }

    public void setFirstNm(String firstNm) {
	this.firstNm = firstNm;
    }

    public String getLastNm() {
	return this.lastNm;
    }

    public void setLastNm(String lastNm) {
	this.lastNm = lastNm;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getMobile() {
	return this.mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getStatus() {
	return this.status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Integer getDeptId() {
	return deptId;
    }

    public void setDeptId(Integer deptId) {
	this.deptId = deptId;
    }

    public List<Group> getGroups() {
	return groups;
    }

    public void setGroups(List<Group> groups) {
	this.groups = groups;
    }

}