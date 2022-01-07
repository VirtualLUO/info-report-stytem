package com.service;

import java.util.List;

import com.pojo.User;
import com.pojo.Group;

public interface UserService {
	User userLogin(String username,String password);
	User checkUser(String username);
	void insert(User user);
	List<User> list();
	int findGroup(String groupname);
	void insertGroup(Group group);
	List<Group> glist();
	List<User> getUserByGroup(int groupid);
	void delGroup(int id);
	User getUser(int id);
	void update(User user);
	Group getGroup(int groupid);
}
