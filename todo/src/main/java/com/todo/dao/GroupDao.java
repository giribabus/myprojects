package com.todo.dao;

import java.util.List;

import com.todo.model.Group;
import com.todo.model.Groups;

public interface GroupDao {
	
	/*
	 * Constants to represent actual table field names
	 */
	public static final String USER_ID = "USER_ID";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String FIRST_NM = "FIRST_NM";
	public static final String LAST_NM = "LAST_NM";
	public static final String EMAIL = "EMAIL";
	public static final String MOBILE = "MOBILE";
	public static final String STATUS = "STATUS";
	public static final String DEPT_ID = "DEPT_ID";
	public static final String CREATED_BY = "CREATED_BY";
	public static final String CREATED_DTTM = "CREATED_DTTM";
	public static final String UPDATED_BY = "UPDATED_BY";
	public static final String UPDATED_DTTM = "UPDATED_DTTM";
	
	public static final String GROUP_ID = "GROUP_ID";
	public static final String GROUP_NM = "GROUP_NM";
	
	List<Group> findGroupsByDeptId(int deptId);
	
	void deleteGroupById(int groupId);
	
	Groups syncGroup(Groups groups);
}
