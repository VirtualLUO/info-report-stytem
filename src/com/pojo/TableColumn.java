package com.pojo;

public class TableColumn {
	private String columnName;
    private String dataType;
    private int length;
    private boolean isNull;
    private boolean isKey;
    private boolean autoIncrement;
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean getIsNull() {
		return isNull;
	}
	public void setIsNull(boolean isNull) {
		this.isNull = isNull;
	}
	public boolean getIsKey() {
		return isKey;
	}
	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean getAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
}
