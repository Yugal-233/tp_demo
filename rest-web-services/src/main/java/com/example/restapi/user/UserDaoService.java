package com.example.restapi.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	static int userCount=3;
	
	static {
		users.add(new User(10, "yugal", new Date()));
		users.add(new User(20, "mayur", new Date()));
		users.add(new User(30, "rahul", new Date()));
	}
	
	public List<User> findAllUser() {
		return users;
	}
	public User saveUser(User user) {
	
		if(user.getId()==0) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	public User findUserById(int id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUserById(int id) {
		
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}
