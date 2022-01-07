<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,com.pojo.Tables,java.util.Map"%>
<%
Tables tab = (Tables)request.getAttribute("tab");
List<Map<String,Object>> data = (List<Map<String,Object>>)request.getAttribute("data");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表格填报管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/subpage.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body>
<h1 class="callout"><%=tab.getTabname() %></h1>
<div class="tab">
	<div class="querybox">
	</div>
	<table class="table table-striped table-bordered table-hover table-condensed">
    	<thead>
        <tr class="success">
        <th>ID</th>
        <%
        String cncol=tab.getCncol();
        String [] cols=cncol.split(",");
        for(int i=0;i<cols.length;i++){
        %>
        <th>
        <%if(cols[i].contains("select")){ %>
        <%=cols[i].substring(0,cols[i].indexOf("(")) %>
        <%}else{ %>
        <%=cols[i] %></th>
        <% }} %>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <% for(Map<String,Object> m:data){ %>
        	<tr>
        	<% for (String k : m.keySet()){
        	%>
        	<td><%=m.get(k) %></td>
        	 <% } %>
        	 <td><a href="${pageContext.request.contextPath }/user/delInfo?tabid=<%=tab.getId()%>&id=<%=m.get("id")%>">删除</a></td>
        	</tr>
        <% } %>
        </tbody>
	</table>
</div>
</body>
</html>