package com.mapper;

import java.util.List;

import com.pojo.Tables;

public interface TabMapper {
	public void insertTab(Tables tables);
	public List<Tables> getlist();
	public Tables getTab(int id);
	public int deleteTable(int id);
	public List<Tables> getgroups();
}
