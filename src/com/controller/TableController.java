package com.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.TableColumn;
import com.pojo.Tables;
import com.service.TabService;

@Controller
@RequestMapping("tab")
public class TableController {
	@Autowired
	public TabService tabService;
	
	@RequestMapping(value = "/addTable", method = RequestMethod.POST)
	public ModelAndView addTable(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		String tabgroup=request.getParameter("tabgroup");
		String tabname = request.getParameter("tabname");
		String enname=request.getParameter("enname");
		String colnum = request.getParameter("colnum");
		List<TableColumn> colList = new ArrayList<>();
		TableColumn col=new TableColumn();
		col.setColumnName("id");
		col.setIsKey(true);
		col.setIsNull(false);
		col.setAutoIncrement(true);
		col.setLength(10);
		col.setDataType("int");
		colList.add(col);
		TableColumn col1=new TableColumn();
		col1.setColumnName("uid");
		col1.setIsKey(false);
		col1.setIsNull(false);
		col1.setAutoIncrement(false);
		col1.setLength(10);
		col1.setDataType("int");
		colList.add(col1);
		String cncol="填报者,";
		for(int i=1;i<Integer.parseInt(colnum)+1;i++) {
			String colvalue = request.getParameter("col"+i);
			if(!colvalue.equals("")) {
				cncol=cncol+colvalue+",";
				TableColumn ncol=new TableColumn();
				ncol.setColumnName("col"+i);
				ncol.setIsKey(false);
				ncol.setIsNull(true);
				ncol.setAutoIncrement(false);
				ncol.setLength(255);
				ncol.setDataType("varchar");
				colList.add(ncol);
			}
		}
		boolean flag = tabService.executeSQL(getSQL(colList,enname));
		if(flag) {
			if(cncol.endsWith(",")) {
				cncol=cncol.substring(0,cncol.length()-1);
			}
			
			Tables tables = new Tables();
			tables.setTabgroup(tabgroup);
			tables.setTabname(tabname);
			tables.setCncol(cncol);
			tables.setEnname(enname);
			tabService.insertTab(tables);
			mav.addObject("msg", "制定表格成功");
		}else {
			mav.addObject("msg", "制定表格失败");
		}
		mav.setViewName("createTable");
		return mav;
	}
	
	public String getSQL(List<TableColumn> ltc, String tableName) {
		String sql = "DROP TABLE IF EXISTS `" + tableName + "`;CREATE TABLE `"
				+ tableName + "` (";
		boolean autoIncrement = false;
		for (TableColumn tc : ltc) {
			sql += "`" + tc.getColumnName() + "` " + this.getDataType(tc) + " "
					+ this.getIsNull(tc) + ",";
			if (tc.getAutoIncrement() && !autoIncrement) {
				autoIncrement = tc.getAutoIncrement();
			}
		}
		if (this.getPrimaryKey(ltc) == "") {
			sql = sql.substring(0, sql.length() - 1);
		}
		sql += this.getPrimaryKey(ltc) + ") ENGINE=MyISAM "
				+ (autoIncrement ? "AUTO_INCREMENT=0" : "")
				+ " DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	public String getDataType(TableColumn tc) {
		if (tc.getLength() != 0) {
			return tc.getDataType() + "(" + tc.getLength() + ")";
		} else {
			return tc.getDataType();
		}
	}

	public String getIsNull(TableColumn tc) {
		if (tc.getIsKey() && tc.getAutoIncrement()) {
			return "NOT NULL AUTO_INCREMENT";
		}
		if (tc.getIsNull()) {
			return "DEFAULT NULL";
		} else {
			return "NOT NULL";
		}
	}
	
	public String getPrimaryKey(List<TableColumn> ltc) {
		String pk = "PRIMARY KEY (";
		for (TableColumn tc : ltc) {
			if (tc.getIsKey()) {
				pk += "`" + tc.getColumnName() + "`,";
			}
		}
		if ("PRIMARY KEY (".equals(pk)) {
			return "";
		} else {
			return pk.substring(0, pk.length() - 1) + ")";
		}
	}
	
	@RequestMapping(value = "/tableslist",method = RequestMethod.GET)
	public ModelAndView tableslist(HttpServletRequest request) {
		String groupname=request.getParameter("groupname");
		ModelAndView mav = new ModelAndView();
		List<Tables> list = tabService.getlist();
		if(groupname==null) {
			mav.addObject("list",list);
		}else {
			if(groupname.equals("")) {
				mav.addObject("list",list);
			}else {
				List<Tables> nlist=new ArrayList<Tables>();
				for(Tables tab:list) {
					if(tab.getTabgroup().equals(groupname)) {
						nlist.add(tab);
					}
				}
				mav.addObject("list",nlist);
			}
		}
		mav.setViewName("listTables");
		return mav;
	}
	
	@RequestMapping(value = "/viewTab",method = RequestMethod.GET)
	public ModelAndView viewTab(int id,HttpServletRequest request) {
		Tables tab = tabService.getTab(id);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("tableName", tab.getEnname());
		List<Map<String,Object>> DataSet = tabService.GetTableData(params);
		request.setAttribute("tab", tab);
		request.setAttribute("data", DataSet);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listReport");
		return mav;
	}
	
	@RequestMapping(value = "/export",method = RequestMethod.POST)
	public void export(int tabid,HttpServletResponse response) {
		Tables tab = tabService.getTab(tabid);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("tableName", tab.getEnname());
		List<Map<String,Object>> DataSet = tabService.GetTableData(params);
		//创建HSSFWorkbook对象
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    //创建HSSFSheet对象
	    HSSFSheet sheet = workbook.createSheet("sheet");
  		sheet.setDefaultColumnWidth(80);
  		HSSFRow row = sheet.createRow((int) 0);
  		row.setHeightInPoints(30);//行高
  		// 标题样式（加粗，垂直居中）
  		HSSFCellStyle cellStyle = workbook.createCellStyle();
  		cellStyle.setAlignment(HorizontalAlignment.CENTER);
  		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
  		HSSFFont fontStyle = workbook.createFont();
  		fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
  		cellStyle.setFont(fontStyle);
  		String cols=tab.getCncol();
  		cols=cols.replace("填报者,", "");
  		String []tableHeader=cols.split(",");
  		short cellNumber=(short)tableHeader.length;//表的列数 
  		for(int k = 0;k < cellNumber;k++){
  			HSSFCell cell = row.createCell(k);//创建第0行第k列
  			if(tableHeader[k].contains("select")) {
  				cell.setCellValue(tableHeader[k].substring(0,tableHeader[k].indexOf("(")));
  			}else {
  				cell.setCellValue(tableHeader[k]);//设置第0行第k列的值 
  			} 
        }
  		int i=0;
  		for(Map<String,Object> m:DataSet){
  			row = sheet.createRow((short) (i + 1));//创建第i+1行
  			row.setHeightInPoints(80);//设置行高 
            int ii=0;
  			for (String k : m.keySet()){
  				if(!k.equals("id")) {
  					if(!k.equals("uid")) {
		  				HSSFCell cell = row.createCell((short) ii);//创建第i+1行第0列
		  				cell.setCellValue(String.valueOf(m.get(k)));
		  				ii++;
  					}
  				}
  			}
  			i++;
  		}
  		OutputStream out = null;//创建一个输出流对象 
  		try {
			out = response.getOutputStream();// 得到输出流
			response.setHeader("Content-disposition","attachment; filename="+ System.currentTimeMillis() + ".xls");//filename是下载的xls的名
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");//设置类型 
	        response.setHeader("Pragma","No-cache");//设置头 
	        response.setHeader("Cache-Control","no-cache");//设置头 
	        response.setDateHeader("Expires", 0);//设置日期头 
	        workbook.write(out); 
	        out.flush();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        }finally{ 
            try{ 
                if(out!=null){ 
                    out.close(); 
                    workbook.close();
                } 
            }catch(IOException e){ 
                e.printStackTrace(); 
            } 
        }
	}
	
	@RequestMapping(value = "/deleteTable",method = RequestMethod.GET)
	public ModelAndView deleteTable(int id) {
		tabService.deleteTable(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "删除成功");
		mav.setViewName("listTables");
		return mav;
	}
}
