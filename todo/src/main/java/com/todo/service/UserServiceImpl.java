package com.todo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.dao.UserDao;
import com.todo.model.User;


 
@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao dao;
     
    public void saveUser(User User) {
        dao.saveUser(User);
    }
 
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }
 
    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }
 
    public User findById(int id) {
        return dao.findById(id);
    }
 
    public void updateUser(User User){
        dao.updateUser(User);
    }

	@Override
	public List<User> findUserByDeptId(int id) {
		return dao.findUserByDeptId(id);
	}
}
