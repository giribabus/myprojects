package com.todo.controller;

import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.todo.model.User;
import com.todo.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserController {
	@Autowired
	private UserService userService;
	private static final Logger LOG = Logger.getLogger(UserController.class);
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
		LOG.debug("Fetching All Users");
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
	// -------------------Retrieve Single User---------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		LOG.debug("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			LOG.debug("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Retrieve Single User---------------------------------------------------

	@RequestMapping(value = "/user/dept/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUserByDeptId(@PathVariable("id") int id) {
		LOG.debug("Fetching Users with department id " + id);
		List<User> user = userService.findUserByDeptId(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}
	
	// -------------------Create a User--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOG.debug("Creating User " + user.getUserName());
		/*if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}*/

		userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	// ------------------- Update a User
	// --------------------------------------------------------
	@RequestMapping(value = "/user/update/", method = RequestMethod.POST)
	public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOG.debug("Updating User " + user.getUserId());
		userService.updateUser(user);
				
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// ------------------- Delete a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		LOG.debug("Fetching & Deleting User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			LOG.debug("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

	}

}