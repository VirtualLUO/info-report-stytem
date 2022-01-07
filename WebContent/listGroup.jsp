<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户组管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript">
if('<%=request.getAttribute("msg")%>'!='null'){
	alert('<%=request.getAttribute("msg")%>');
	window.location.href="${pageContext.request.contextPath}/user/grouplist";
}
</script>
</head>
<body>
<h1 class="callout">用户组列表</h1>
<div class="tab">
	<div class="querybox">
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
    	<thead>
        <tr class="success">
        	<th>ID</th>
        	<th>用户组</th>
        	<th>操作</th>
         </tr>
         </thead>
         <tbody>
        	<c:forEach items="${grouplist}" var="group">
        	<tr>
        		<td style="text-align:center;">${group.id}</td>
        		<td style="text-align:center;">${group.groupname}</td>
        		<td style="text-align:center;">
        		<a href="${pageContext.request.contextPath }/user/delGroup?id=${group.id}">删除</a>
             	</td>
             </tr>
             </c:forEach>
        </tbody>
	</table>
</div>	
</body>
</html>