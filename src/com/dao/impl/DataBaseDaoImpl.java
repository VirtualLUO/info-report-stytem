package com.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dao.DataBaseDao;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

@Repository(value="databaseDao")
public class DataBaseDaoImpl implements DataBaseDao {
	@Override
	public boolean ExecuteSQL(String sql) {
		String[] sqlList = sql.split(";");
		@SuppressWarnings("resource")
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=ioc.getBean(JdbcTemplate.class);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(jdbcTemplate.getDataSource());
		TransactionStatus ts = transactionManager.getTransaction(def);
		try {
			for (int i = 0; i < sqlList.length; i++) {
				jdbcTemplate.execute(sqlList[i]);
			}
		} catch (Exception e) {
			transactionManager.rollback(ts);
			e.printStackTrace();
			return false;
		} finally {
			transactionManager.commit(ts);
		}
		return true;
	}
	
	@Override
	public List<Map<String, Object>> GetTableData(
			Map<String, Object> params) {	
		String tableName=params.get("tableName").toString();
		String sql="select * from "+tableName+" where 1=1 ";
		Set<String> keys=params.keySet();
		Iterator<String> it=keys.iterator();
		String conditionsSql="";
		while(it.hasNext()){
			String key=(String)it.next();
			if(!key.equals("tableName")){
				conditionsSql+="and "+key+" = "+params.get(key);
			}
		}
		sql+=conditionsSql;
		System.out.println(sql);
		@SuppressWarnings("resource")
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=ioc.getBean(JdbcTemplate.class);
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public int insertData(String sql) {
		@SuppressWarnings("resource")
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=ioc.getBean(JdbcTemplate.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}
	
	@Override
	public int delData(String sql) {
		@SuppressWarnings("resource")
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=ioc.getBean(JdbcTemplate.class);
		int row = jdbcTemplate.update(sql);
		return row;
	}
}
