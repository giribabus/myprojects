/*package com.todo;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.todo.model.User;
import com.todo.service.UserService;
 
 
public class AppMain {
 
    public static void main(String args[]) {
    	AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
    	context.scan("com.todo"); 
    	context.refresh();

        UserService service = (UserService) context.getBean("UserService");
 
        
         * Create User1
         
        User User1 = new User();
        User1.setName("Han Yenn");
        User1.setJoiningDate(new Date(0));
        User1.setSalary(new BigDecimal(10000));
        User1.setSsn("ssn00000001");
 
        
         * Create User2
         
        User User2 = new User();
        User2.setName("Dan Thomas");
        User2.setJoiningDate(new Date(0));
        User2.setSalary(new BigDecimal(20000));
        User2.setSsn("ssn00000002");
 
        
         * Persist both Users
         
        service.saveUser(User1);
        service.saveUser(User2);
 
        
         * Get all Users list from database
         
        List<User> Users = service.findAllUsers();
        for (User emp : Users) {
            System.out.println(emp);
        }
 
        
         * delete an User
         
        service.deleteUserById(2);
 
        
         * update an User
         
 
        User User = service.findById(1);
        User.setSalary(new BigDecimal(50000));
        service.updateUser(User);
 
        
         * Get all Users list from database
         
        List<User> UserList = service.findAllUsers();
        for (User emp : UserList) {
            System.out.println(emp);
        }
 
        context.close();
    }
}
*/