<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
 <script type="text/javascript" language="javascript">
if('<%=request.getAttribute("msg")%>'!='null'){
	alert('<%=request.getAttribute("msg")%>');
	window.location.href="${pageContext.request.contextPath}/user/userlist";
}
</script>
</head>
<body>
<h1 class="callout">用户列表</h1>
<div class="tab">
	<div class="querybox" style="min-height: 50px; margin: 15px 0;">
		<form name="form1" method="get" action="${pageContext.request.contextPath}/user/userlist">
			<p>搜索</p>
			用户名：<input type="text" name="username" id="username" class="inputbox">
			用户组：<input type="text" name="usergroup" id="usergroup" class="inputbox">
			<input type="submit" name="querybtn" value="查询">
		</form>
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
    	<thead>
        <tr class="success">
        	<th>ID</th>
        	<th>用户名</th>
        	<th>用户组</th>
        	<th>操作</th>
         </tr>
         </thead>
         <tbody>
        	<c:forEach items="${userlist}" var="user">
        	<tr>
        		<td style="text-align:center;">${user.id}</td>
        		<td style="text-align:center;">${user.username}</td>
        		<td style="text-align:center;">${user.groupname}</td>
        		<td style="text-align:center;">
        		<a href="${pageContext.request.contextPath }/user/editUser?id=${user.id}">设置用户组</a>
             	</td>
             </tr>
             </c:forEach>
        </tbody>
	</table>
</div>	
</body>
</html>