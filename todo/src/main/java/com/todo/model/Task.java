package com.todo.model;

// Generated Jun 3, 2016 3:56:09 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Task generated by hbm2java
 */
public class Task implements java.io.Serializable {

	private Integer taskId;
	private Tasklist tasklist;
	private String taskNm;
	private String taskShortnm;
	private String createdBy;
	private Date createdDttm;
	private String updatedBy;
	private Date updatedDttm;
	private Set taskActivities = new HashSet(0);

	public Task() {
	}

	public Task(Tasklist tasklist, String taskNm) {
		this.tasklist = tasklist;
		this.taskNm = taskNm;
	}

	public Task(Tasklist tasklist, String taskNm, String taskShortnm, String createdBy, Date createdDttm,
			String updatedBy, Date updatedDttm, Set taskActivities) {
		this.tasklist = tasklist;
		this.taskNm = taskNm;
		this.taskShortnm = taskShortnm;
		this.createdBy = createdBy;
		this.createdDttm = createdDttm;
		this.updatedBy = updatedBy;
		this.updatedDttm = updatedDttm;
		this.taskActivities = taskActivities;
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Tasklist getTasklist() {
		return this.tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}

	public String getTaskNm() {
		return this.taskNm;
	}

	public void setTaskNm(String taskNm) {
		this.taskNm = taskNm;
	}

	public String getTaskShortnm() {
		return this.taskShortnm;
	}

	public void setTaskShortnm(String taskShortnm) {
		this.taskShortnm = taskShortnm;
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

	public Set getTaskActivities() {
		return this.taskActivities;
	}

	public void setTaskActivities(Set taskActivities) {
		this.taskActivities = taskActivities;
	}

}
