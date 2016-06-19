package com.todo.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.todo.model.Group;
import com.todo.model.Groups;

public class GroupDaoImpl extends JdbcDaoSupport implements GroupDao {

	private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);
	private SimpleJdbcInsert insertUserGroup;
	private SimpleJdbcInsert insertGroup;

	private static final String SELECT_GROUPS_BY_DEPT_ID = "SELECT G.*, UG.USER_ID, UD.DEPT_ID FROM GROUPS G JOIN USER_GROUP UG JOIN USER_DEPT UD WHERE G.GROUP_ID = UG.GROUP_ID AND UG.USER_ID = UD.USER_ID AND G.STATUS='A' AND UD.DEPT_ID = ?";
	private static final String DELETE_GROUP = "UPDATE GROUPS SET STATUS= 'D' WHERE GROUP_ID = ?";
	// private static final String SELECT_GROUP_BY_USER_ID_DEPT_ID = "SELECT
	// G.*, UG.USER_ID, UD.DEPT_ID FROM GROUPS G JOIN USER_GROUP UG JOIN
	// USER_DEPT UD WHERE G.GROUP_ID = UG.GROUP_ID AND UG.USER_ID = UD.USER_ID
	// AND UD.DEPT_ID = ? and UD.USER_ID = ?";
	private static final String DELETE_USER_GROUP = "DELETE FROM USER_GROUP WHERE GROUP_ID = ?";

	@Override
	public void initTemplateConfig() {
		this.insertUserGroup = new SimpleJdbcInsert(getDataSource()).withTableName("USER_GROUP");
		this.insertGroup = new SimpleJdbcInsert(getDataSource()).withTableName("GROUPS").usingGeneratedKeyColumns(GROUP_ID);
	}

	/**
	 * 
	 */
	@Override
	public List<Group> findGroupsByDeptId(int deptId) {

		List<Group> groups = new ArrayList<Group>();

		Map<Integer, Group> groupusers = new HashMap<Integer, Group>();

		ColumnMapRowMapper rowMapper = new ColumnMapRowMapper();
		List<Map<String, Object>> groupDataList = (List<Map<String, Object>>) getJdbcTemplate()
				.query(SELECT_GROUPS_BY_DEPT_ID, rowMapper, deptId);

		for (Map<String, Object> map : groupDataList) {
			Group group = new Group();
			group.setGroupId((Integer) map.get(GROUP_ID));
			group.setGroupNm((String) map.get(GROUP_NM));
			group.setStatus((String) map.get(STATUS));

			Group existgroup = groupusers.get(group.getGroupId());

			if (existgroup != null) {
				existgroup.getUsers().add((Integer) map.get(USER_ID));
			} else {
				groupusers.put(group.getGroupId(), group);
				List<Integer> users = new ArrayList<Integer>();
				users.add((Integer) map.get(USER_ID));
				group.setUsers(users);
			}
		}
		groups = new ArrayList<Group>(groupusers.values());
		return groups;
	}

	/**
	 * 
	 */
	@Override
	public void deleteGroupById(int groupId) {
		getJdbcTemplate().update(DELETE_GROUP, groupId);
	}

	/**	 
	 * 
	 *
	 */
	class GroupRowMapper implements RowMapper<Group> {
		@Override
		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			group.setGroupId(rs.getInt(GROUP_ID));
			group.setGroupNm(rs.getString(GROUP_NM));
			group.setStatus(rs.getString(STATUS));
			return group;
		}
	}

	/**
	 * 
	 */
	@Override
	public Groups syncGroup(Groups groups) {
		LOG.debug("Department Id : " + groups.getDeptId() + ", Groups : " + groups.getGroups());
		for (Group group : groups.getGroups()) {
			saveOrUpdateGroup(group, groups.getActingUser());
		}
		return groups;
	}

	private void saveOrUpdateGroup(Group group, int actingUser) {
		if (group.getGroupId() != 0) {
			deleteUserGroups(group.getGroupId());
		} else {
			insertGroup(group, actingUser);
		}
		insertUserGroups(group, actingUser);
	}

	private void insertGroup(Group group, int actingUser) {
		Date now = new Date(System.currentTimeMillis());
		Map<String, Object> groupParams = new HashMap<String, Object>();
		groupParams.put(GROUP_NM, group.getGroupNm());
		groupParams.put(STATUS, group.getStatus());
		groupParams.put(CREATED_DTTM, now);
		groupParams.put(CREATED_BY, actingUser);
		groupParams.put(UPDATED_DTTM, now);
		groupParams.put(UPDATED_BY, actingUser);
		Number key = insertGroup.executeAndReturnKey(groupParams);
		int groupId = ((Number) key).intValue();
		group.setGroupId(groupId);
	}

	private void deleteUserGroups(Integer groupId) {
		getJdbcTemplate().update(DELETE_USER_GROUP, groupId);
	}

	private void insertUserGroups(Group group, int actingUser) {
		Date now = new Date(System.currentTimeMillis());
		for (Integer userId : group.getUsers()) {
			Map<String, Object> userGroupParams = new HashMap<String, Object>();
			userGroupParams.put(GROUP_ID, group.getGroupId());
			userGroupParams.put(USER_ID, userId);
			userGroupParams.put(CREATED_DTTM, now);
			userGroupParams.put(CREATED_BY, actingUser);
			userGroupParams.put(UPDATED_DTTM, now);
			userGroupParams.put(UPDATED_BY, actingUser);
			insertUserGroup.execute(userGroupParams);
		}
	}

}