package com.todo.model;

import java.util.List;

public class Groups  extends BaseModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7399427476141183985L;
	private List<Group> groups;

	public Groups() {
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	

}
