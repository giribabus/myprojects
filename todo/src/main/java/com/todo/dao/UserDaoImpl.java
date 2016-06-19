package com.todo.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.todo.model.Group;
import com.todo.model.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	private static final Logger LOG =	Logger.getLogger(UserDaoImpl.class);
	
	private NamedParameterJdbcTemplate  namedParamTemplate;
	private SimpleJdbcInsert insertUser;
	private SimpleJdbcInsert insertUserDept;
	
	private static final String FIND_ALL_USERS = "SELECT U.*, UD.DEPT_ID FROM USER U JOIN USER_DEPT UD WHERE U.USER_ID = UD.USER_ID AND U.STATUS ='A'";
	private static final String FIND_USER_BY_ID = "SELECT U.*, UD.DEPT_ID FROM USER U JOIN USER_DEPT UD WHERE U.USER_ID = UD.USER_ID AND U.USER_ID = ? AND U.STATUS= 'A'";	
	private static final String FIND_USER_BY_DEPT_ID = "SELECT U.*, UD.DEPT_ID FROM USER U JOIN USER_DEPT UD WHERE U.USER_ID = UD.USER_ID AND UD.DEPT_ID = ? AND U.STATUS= 'A'";
	private static final String DELETE_USER = "UPDATE USER SET STATUS= 'D' WHERE USER_ID = ?";
	private static final String UPDATE_USER_BY_ID = "UPDATE USER SET USER_NAME = :USER_NAME, PASSWORD = :PASSWORD, FIRST_NM = :FIRST_NM, LAST_NM = :LAST_NM, EMAIL = :EMAIL, MOBILE = :MOBILE, UPDATED_BY = :UPDATED_BY, UPDATED_DTTM = :UPDATED_DTTM, STATUS = :STATUS WHERE USER_ID = :USER_ID";
	private static final String SELECT_GROUPS_USER_ID = "SELECT G.* FROM GROUPS G JOIN USER_GROUP UG WHERE G.GROUP_ID = UG.GROUP_ID AND UG.USER_ID = ?";
	
	
	@Override
	public void initTemplateConfig() {
		this.namedParamTemplate = new NamedParameterJdbcTemplate(getDataSource());
		this.insertUser = new SimpleJdbcInsert(getDataSource()).withTableName("USER").usingGeneratedKeyColumns(USER_ID);
		this.insertUserDept = new SimpleJdbcInsert(getDataSource()).withTableName("USER_DEPT");
		this.insertUser.includeSynonymsForTableColumnMetaData();
		this.insertUserDept.includeSynonymsForTableColumnMetaData();
	}

	@Override
	public void saveUser(User user) {
		Date now = new Date(System.currentTimeMillis());
		Map<String, Object> userParams = new HashMap<String, Object>();
		userParams.put(USER_NAME, user.getUserName());
		userParams.put(PASSWORD, user.getPassword());
		userParams.put(FIRST_NM, user.getFirstNm());
		userParams.put(LAST_NM, user.getLastNm());
		userParams.put(EMAIL, user.getEmail());
		userParams.put(MOBILE, user.getMobile());
		userParams.put(CREATED_BY, user.getActingUser());
		userParams.put(CREATED_DTTM, now);
		userParams.put(UPDATED_BY, user.getActingUser());
		userParams.put(UPDATED_DTTM, now);
		userParams.put(STATUS, user.getStatus());
		
		Number key = insertUser.executeAndReturnKey(userParams);
		int userId = ((Number) key).intValue();
		user.setUserId(userId);
		LOG.debug("User ID ===  : " + userId);
		saveUserDept(user);
	}

	private void saveUserDept(User user) {
		Date now = new Date(System.currentTimeMillis());
		Map<String, Object> userDeptParams = new HashMap<String, Object>();
		userDeptParams.put(DEPT_ID, user.getDeptId());
		userDeptParams.put(USER_ID, user.getUserId());
		userDeptParams.put(CREATED_DTTM, now);
		userDeptParams.put(UPDATED_BY, user.getActingUser());
		userDeptParams.put(UPDATED_DTTM, now);
		userDeptParams.put(STATUS, user.getStatus());
		insertUserDept.execute(userDeptParams);
	}

	@Override
	public List<User> findAllUsers() {
		LOG.debug("Fetching All users.");
		List<User> users = new ArrayList<User>();
		users = (List<User>) getJdbcTemplate().query(FIND_ALL_USERS, new UserRowMapper());
		return users;
	}

	@Override
	public void deleteUserById(int id) {
		getJdbcTemplate().update(DELETE_USER, id);
	}

	@Override
	public User findById(int userId) {
		return (User) getJdbcTemplate().queryForObject(FIND_USER_BY_ID, new UserRowMapper(), userId);
	}
	
	@Override
	public void updateUser(User user) {
		Date now = new Date(System.currentTimeMillis());
		Map<String, Object> userParams = new HashMap<String, Object>();
		userParams.put("USER_NAME", user.getUserName());
		userParams.put("PASSWORD", user.getPassword());
		userParams.put("FIRST_NM", user.getFirstNm());
		userParams.put("LAST_NM", user.getLastNm());
		userParams.put("EMAIL", user.getEmail());
		userParams.put("MOBILE", user.getMobile());
		userParams.put("UPDATED_BY", user.getActingUser());
		userParams.put("UPDATED_DTTM", now);
		userParams.put("STATUS", user.getStatus());
		userParams.put("USER_ID", user.getUserId());
		
		namedParamTemplate.update(UPDATE_USER_BY_ID, userParams);
	}
	
	@Override
	public List<User> findUserByDeptId(int deptId) {
		List<User> users = new ArrayList<User>();
		users = (List<User>) getJdbcTemplate().query(FIND_USER_BY_DEPT_ID, new UserRowMapper(), deptId);
		return users;		
	}

	private List<Group> findGroupsByUserId(int userId) {
		List<Group> groups = new ArrayList<Group>();
		groups = (List<Group>) getJdbcTemplate().query(SELECT_GROUPS_USER_ID, new GroupRowMapper(), userId);
		return groups;		
	} 
	
	/**
	 * 
	 * Get All Active Users
	 *
	 */
	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt(USER_ID));
			user.setUserName(rs.getString(USER_NAME));
			user.setFirstNm(rs.getString(FIRST_NM));
			user.setLastNm(rs.getString(LAST_NM));
			user.setEmail(rs.getString(EMAIL));
			user.setMobile(rs.getString(MOBILE));
			user.setStatus(rs.getString(STATUS));
			user.setDeptId(rs.getInt(DEPT_ID));
			user.setActingUser(rs.getInt(UPDATED_BY));
			
			List<Group> groups = findGroupsByUserId(user.getUserId());
			user.setGroups(groups);
			
			return user;
		}
	}

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
	

}