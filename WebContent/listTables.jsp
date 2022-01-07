<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
 <script type="text/javascript" language="javascript">
if('<%=request.getAttribute("msg")%>'!='null'){
	alert('<%=request.getAttribute("msg")%>');
	window.location.href="${pageContext.request.contextPath}/tab/tableslist";
}
</script>
</head>
<body>
<h1 class="callout">表格列表</h1>
<div class="tab">
	<div class="querybox" style="margin: 15px 0;">
	<form name="form1" method="get" action="${pageContext.request.contextPath}/tab/tableslist">
		<p>搜索：</p>
		批次信息：<input type="text" name="groupname" id="groupname" class="inputbox">
		<input type="submit" name="querybtn" value="查询">
	</form>
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
    	<thead>
        <tr class="success">
        	<th>ID</th>
        	<th>批次信息</th>
        	<th>表格名称</th>
        	<th>表格列名</th>
        	<th>操作</th>
         </tr>
         </thead>
         <tbody>
        	<c:forEach items="${list}" var="tab">
        	<tr>
        		<td style="text-align:center;">${tab.id}</td>
        		<td style="text-align:center;">${tab.tabgroup}</td>
        		<td style="text-align:center;">${tab.tabname}</td>
        		<td style="text-align:left;">${tab.cncol}</td>
        		<td style="text-align:center;">
        		<a href="${pageContext.request.contextPath}/tab/viewTab?id=${tab.id}">查看填报</a>
             	<a href="${pageContext.request.contextPath}/tab/deleteTable?id=${tab.id}">删除</a>
             	</td>
             </tr>
             </c:forEach>
        </tbody>
	</table>
</div>	
</body>
</html>