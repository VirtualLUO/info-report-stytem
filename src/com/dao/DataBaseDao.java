package com.dao;

import java.util.List;
import java.util.Map;

public interface DataBaseDao {
	public boolean ExecuteSQL(String sql);
	public List<Map<String,Object>> GetTableData(Map<String,Object> params);
	public int insertData(String sql);
	public int delData(String sql);
}
