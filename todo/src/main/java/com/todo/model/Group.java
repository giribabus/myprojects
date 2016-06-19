package com.todo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Group implements java.io.Serializable {
	
	private static final long serialVersionUID = -1432177768606275291L;
	private Integer groupId;
	private String groupNm;
	private String status;
	@JsonInclude(Include.NON_NULL)
	private List<Integer> users;

	public Group() {
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupNm() {
		return this.groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}

}
