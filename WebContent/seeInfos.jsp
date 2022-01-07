<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填报信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body>
<h1 class="callout">信息管理</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/user/queryTablist" method="post" style="width:100%;text-align:center;">
<table>
<tr>
<td>选择要查看的表格：</td>
<td><select name="tabid" id="tabid" style="width:230px;">
<c:forEach items="${tablist}" var="tab">
<option value="${tab.id}">${tab.tabname}</option>
</c:forEach>
</select></td>
</tr>
<tr><td colspan='2'><input name="sub" type="submit" value="查询" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;"></td></tr>
</table>
</form>
</div>
</body>
</html>