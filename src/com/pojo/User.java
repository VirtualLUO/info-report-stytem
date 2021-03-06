package com.pojo;

public class User {
	private int id;
	private String username;
	private String password;
	private String role;
	private int groupid;
	private String groupname;
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getGroupid() {
		return groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
