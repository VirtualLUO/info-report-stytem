package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DataBaseDao;
import com.mapper.TabMapper;
import com.pojo.Tables;
import com.service.TabService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class TabServiceImpl implements TabService{
	@Autowired
	public TabMapper tabMapper;
	@Resource(name="databaseDao")
	private DataBaseDao databaseDao;

	@Override
	public void insertTab(Tables tables) {
		tabMapper.insertTab(tables);		
	}

	@Override
	public boolean executeSQL(String sql) {
		return databaseDao.ExecuteSQL(sql);
	}
	
	@Override
	public List<Tables> getlist(){
		return tabMapper.getlist();
	}
	
	@Override
	public Tables getTab(int id) {
		return tabMapper.getTab(id);
	}
	
	@Override
	public List<Map<String,Object>> GetTableData(Map<String,Object> params){
		return databaseDao.GetTableData(params);
	}
	
	@Override
	public int insertData(String sql) {
		return databaseDao.insertData(sql);
	}
	
	@Override
	public int deleteData(String sql) {
		return databaseDao.delData(sql);
	}
	
	@Override
	public int deleteTable(int id) {
		return tabMapper.deleteTable(id);
	}
	
	@Override
	public List<Tables> getgroups(){
		return tabMapper.getgroups();
	}
}
