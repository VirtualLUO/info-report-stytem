package com.mapper;

import com.pojo.Group;
import com.pojo.User;
import java.util.List;

public interface UserMapper {
	public User userLogin(String username);
	public void insertUser(User user);
	public List<User> getlist();
	public int findGroup(String groupname);
	public void insertGroup(Group group);
	public List<Group> getGroupList();
	public List<User> getUserByGroup(int groupid);
	public void delGroup(int id);
	public User getUser(int id);
	public void updateUser(User user);
	public Group getGroup(int groupid);
}
