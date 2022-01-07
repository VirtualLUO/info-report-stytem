<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户组</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" language="javascript">
if('<%=request.getAttribute("msg")%>'!='null'){
	alert('<%=request.getAttribute("msg")%>');
}
</script>
</head>
<body>
<h1 class="callout">添加用户组</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/user/addGroup" method="post" style="width:100%;text-align:center;">
<table>
<tr>
<td>用户组名称：</td>
<td><input type="text" name="groupname" id="groupname" class="inputbox" required></td>
</tr>
<tr><td colspan='2'><input name="sub" type="submit" value="提交" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;"></td></tr>
</table>
</form>
</div>
</body>
</html>