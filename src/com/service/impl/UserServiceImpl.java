package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.pojo.Group;
import com.pojo.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	public UserMapper userMapper;
	
	@Override
	public User userLogin(String username,String password) {
		User dbuser=userMapper.userLogin(username);
		if(dbuser!=null&&password.equals(dbuser.getPassword())) {
			return dbuser;
		}
		return null;
	}
	
	@Override
	public User checkUser(String username) {
		User dbuser=userMapper.userLogin(username);
		return dbuser;
	}
	
	@Override
	public void insert(User user) {
		userMapper.insertUser(user);
	}
	
	@Override
	public List<User> list()
	{
		return userMapper.getlist();
	}
	
	@Override
	public int findGroup(String groupname) {
		return userMapper.findGroup(groupname);
	}
	
	@Override
	public void insertGroup(Group group) {
		userMapper.insertGroup(group);
	}
	
	@Override
	public List<Group> glist(){
		return userMapper.getGroupList();
	}
	
	@Override
	public List<User> getUserByGroup(int groupid){
		return userMapper.getUserByGroup(groupid);
	}
	
	@Override
	public void delGroup(int id) {
		userMapper.delGroup(id);
	}
	
	@Override
	public User getUser(int id) {
		return userMapper.getUser(id);
	}
	
	@Override
	public void update(User user) {
		userMapper.updateUser(user);
	}
	
	@Override
	public Group getGroup(int groupid) {
		return userMapper.getGroup(groupid);
	}
}
