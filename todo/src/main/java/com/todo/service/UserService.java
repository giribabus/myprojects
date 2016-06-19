package com.todo.service;
import java.util.List;

import com.todo.model.User;
 
 
public interface UserService {
 
    void saveUser(User User);
 
    List<User> findAllUsers();
 
    void deleteUserById(int id);
 
    User findById(int id);
 
    void updateUser(User User);
    
    List<User> findUserByDeptId(int id);
    
    /*User isUserExist(User User);*/
}
