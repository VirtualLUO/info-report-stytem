package com.service;

import com.pojo.Tables;
import java.util.List;
import java.util.Map;

public interface TabService {
	void insertTab(Tables tables);
	boolean executeSQL(String sql);
	List<Tables> getlist();
	Tables getTab(int id);
	List<Map<String,Object>> GetTableData(Map<String,Object> params);
	int insertData(String sql);
	int deleteData(String sql);
	int deleteTable(int id);
	List<Tables> getgroups();
}
