package com.todo.service;

import com.todo.model.Group;
import com.todo.model.Groups;
 
 
public interface GroupService {
 
    void saveGroups(Groups groups);
    
    Groups findGroupsByDeptId(int deptId);
 
    void deleteGroupById(int id);
 
    Group findById(int id);
    
    Groups syncGroups(Groups group);
}
