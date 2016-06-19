package com.todo.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -244672922711208685L;
	private Integer deptId;
	private Integer actingUser;
	
	public Integer getDeptId() {
	    return deptId;
	}
	public void setDeptId(Integer deptId) {
	    this.deptId = deptId;
	}
	public Integer getActingUser() {
	    return actingUser;
	}
	public void setActingUser(Integer actingUser) {
	    this.actingUser = actingUser;
	}
	
}
