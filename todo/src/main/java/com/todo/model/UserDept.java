package com.todo.model;


import java.util.Date;


public class UserDept implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7953794274985042957L;
	private Integer id;
	private Department department;
	private User user;
	private String createdBy;
	private Date createdDttm;
	private String updatedBy;
	private Date updatedDttm;

	public UserDept() {
	}

	public UserDept(Department department, User user) {
		this.department = department;
		this.user = user;
	}

	public UserDept(Department department, User user, String createdBy, Date createdDttm, String updatedBy,
			Date updatedDttm) {
		this.department = department;
		this.user = user;
		this.createdBy = createdBy;
		this.createdDttm = createdDttm;
		this.updatedBy = updatedBy;
		this.updatedDttm = updatedDttm;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDttm() {
		return this.createdDttm;
	}

	public void setCreatedDttm(Date createdDttm) {
		this.createdDttm = createdDttm;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDttm() {
		return this.updatedDttm;
	}

	public void setUpdatedDttm(Date updatedDttm) {
		this.updatedDttm = updatedDttm;
	}

}
