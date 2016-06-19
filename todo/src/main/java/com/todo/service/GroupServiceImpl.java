package com.todo.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.dao.GroupDao;
import com.todo.model.Group;
import com.todo.model.Groups;
import com.todo.model.User;


 
@Service("GroupService")
@Transactional
public class GroupServiceImpl implements GroupService{
	
	@Autowired
    private GroupDao groupDao;

	@Override
	public void saveGroups(Groups groups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Groups findGroupsByDeptId(int deptId) {
		Groups groups = new Groups();
		groups.setGroups(groupDao.findGroupsByDeptId(deptId));
		return groups;
	}

	@Override
	public void deleteGroupById(int groupId) {
		groupDao.deleteGroupById(groupId);
	}

	@Override
	public Group findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Groups syncGroups(Groups groups) {
		return groupDao.syncGroup(groups);
	}
	
}
