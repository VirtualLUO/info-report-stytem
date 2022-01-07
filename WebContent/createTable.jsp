<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制定表格</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body>
<h1 class="callout">制定表格</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/tab/addTable" method="post" style="width:100%;text-align:center;">
<table id="tab">
<tr>
<td>批次名称：</td>
<td><input type="text" name="tabgroup" id="tabgroup" class="inputbox"></td>
</tr>
<tr>
<td>表格中文名称：</td>
<td><input type="text" name="tabname" id="tabname" class="inputbox" required></td>
</tr>
<tr>
<td>表格英文名称：</td>
<td><input type="text" name="enname" id="enname" class="inputbox" required></td>
</tr>
<tr>
<td>列名：</td>
<td><input type="text" name="col1" id="col1" class="inputbox" required placeholder="列名(勾选:选项1-选项2...)或者附件"></td>
</tr>
</table>
<div>
<input type="button" name="addcol" id="addcol" value="添加列" onclick="addCol()" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;">
<input name="sub" type="submit" value="提交" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;">
</div>
<input type="hidden" name="colnum" id="colnum" value="1">
</form>
</div>
<script type="text/javascript" language="javascript">
function addCol(){
	var txtTRLastIndex = document.getElementById("colnum").value;
	var rowID=parseInt(txtTRLastIndex);
	var tab = document.getElementById("tab");
	var newTR = tab.insertRow(tab.rows.length);
	var newNameTD = newTR.insertCell(0);
	newNameTD.innerHTML = "列名：";
	var newNameTD = newTR.insertCell(1);
	newNameTD.innerHTML="<input type=\"text\" name=\"col"+(rowID+1)+"\" id=\"col"+(rowID+1)+"\" class=\"inputbox\">";
	document.getElementById("colnum").value=(rowID + 1).toString();
}
</script>
</body>
</html>