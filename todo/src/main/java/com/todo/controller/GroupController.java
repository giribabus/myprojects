package com.todo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.Group;
import com.todo.model.Groups;
import com.todo.service.GroupService;

@RestController
@RequestMapping("/rest")
public class GroupController {

	@Autowired
	private GroupService groupService;
	private static final Logger LOG = Logger.getLogger(GroupController.class);

	// -------------------Retrieve
	// Groups---------------------------------------------------

	@RequestMapping(value = "/groups/dept/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Groups> getGroupsByDeptId(@PathVariable("id") int deptId) {
		LOG.debug("Fetching Groups with department id " + deptId);
		Groups groups = groupService.findGroupsByDeptId(deptId);
		if (groups == null) {
			LOG.debug("Groups with deptId " + deptId + " not found");
			return new ResponseEntity<Groups>(HttpStatus.NOT_FOUND);
		}
		groups.setDeptId(deptId);
		return new ResponseEntity<Groups>(groups, HttpStatus.OK);
	}
	
	// ------------------- synchGroups for  Dept----------------------------
	//----------------------------------------------------------------------

	@RequestMapping(value = "/groups/syncGroups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Groups> synchGroupsByDept(@RequestBody Groups groups) {
		if (groups == null) {
			LOG.debug("Invalid request data for synchGroups:"+groups);
			return new ResponseEntity<Groups>(HttpStatus.BAD_REQUEST);
		}
		LOG.debug("Synchup Groups for Deptid:" + groups.getDeptId());
		Groups groupsResp = groupService.syncGroups(groups);
		return new ResponseEntity<Groups>(groupsResp, HttpStatus.OK);
	}
	
	// ------------------- Delete a Group
	// --------------------------------------------------------

	@RequestMapping(value = "/groups/{id}/actUser/{actUserId}", method = RequestMethod.DELETE)
	public ResponseEntity<Group> deleteGroup(@PathVariable("id") int groupId,
			@PathVariable("actUserId") int actUserId) {
		LOG.debug("Deleting Group with id " + groupId);
		groupService.deleteGroupById(groupId);
		return new ResponseEntity<Group>(HttpStatus.OK);

	}
	
}