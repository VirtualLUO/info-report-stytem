<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<String[]> opArr=(ArrayList<String[]>)request.getAttribute("options");
int j=0;
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>填报信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body>
<h1 class="callout">填报信息</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/user/addinputinfo" method="post" style="width:100%;text-align:center;" enctype="multipart/form-data">
<c:if test="${isupload=='n'}">
<input type="file" name="uploadfile" id="uploadfile" style="display:none;">
</c:if>
<table>
<c:forEach items="${cols}" var="col" varStatus="status" begin="1">
<tr>
<c:if test="${fn:contains(col,'select')}">
<td>${fn:substring(col,0,fn:indexOf(col,"("))}：</td>
</c:if>
<c:if test="${!fn:contains(col,'select')}">
<td>${col}：</td>
</c:if>
<c:if test="${col=='附件'}">
<td><input type="file" name="uploadfile" id="uploadfile" class="inputbox"></td>
</c:if>
<c:if test="${col!='附件'}">
<c:if test="${fn:contains(col,'select')}">
<td>
<select name="col${status.count}" id="col${status.count}">
<%
String [] arr=opArr.get(j);
for(int k=0;k<arr.length;k++){
%>
<option value="<%=arr[k] %>"><%=arr[k] %></option>
<% }
j++;
%>
</select>
</td>
</c:if>
<c:if test="${!fn:contains(col,'select')}">
<td><input type="text" name="col${status.count}" id="col${status.count}" class="inputbox" required></td>
</c:if>
</c:if>
</tr>
</c:forEach>
<tr><td colspan='2'><input name="sub" type="submit" value="提交" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;"></td></tr>
</table>
<input type="hidden" name="tabid" id="tabid" value="${tab.id}">
</form>
</div>
</body>
</html>