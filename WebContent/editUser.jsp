<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置用户组</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<script type="text/javascript" language="javascript">
if('<%=request.getAttribute("msg")%>'!='null'){
	alert('<%=request.getAttribute("msg")%>');
}
</script>
</head>
<body>
<h1 class="callout">设置用户组</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/user/updateUser" method="post" style="width:100%;text-align:center;">
<input type="hidden" name="id" id="id" value="${user.id}">
<table>
<tr>
<td colspan='2'>用户名：${user.username}</td>
</tr>
<tr>
<td>用户组：</td>
<td><select name="groupid" id="groupid" style="width:230px;">
<c:forEach items="${grouplist}" var="group">
<option value="${group.id}">${group.groupname}</option>
</c:forEach>
</select></td>
</tr> 
<tr><td colspan='2'><input name="sub" type="submit" value="提交" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;"></td></tr>
</table>
</form>
</div>
</body>
</html>