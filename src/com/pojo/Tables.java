package com.pojo;

public class Tables {
	private int id;
	private String tabname;
	private String cncol;
	private String enname;
	private String tabgroup;
	
	public int getId() {
		return id;
	}
	public String getTabname() {
		return tabname;
	}
	public String getCncol() {
		return cncol;
	}
	public String getEnname() {
		return enname;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	public void setCncol(String cncol) {
		this.cncol = cncol;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
	public String getTabgroup() {
		return tabgroup;
	}
	public void setTabgroup(String tabgroup) {
		this.tabgroup = tabgroup;
	}
}
