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
<h1 class="callout">填报信息</h1>
<div class="callout">
<form action="${pageContext.request.contextPath}/user/nextinputinfo" method="post" style="width:100%;text-align:center;">
<table>
<tr>
<td>选择表格组：</td>
<td><select name="selgroup" id="selgroup" style="width:230px;" onchange="tosel()">
<option value="">请选择</option>
<c:forEach items="${tablist}" var="tab">
<option value="${tab.tabgroup}">${tab.tabgroup}</option>
</c:forEach>
</select></td>
</tr>
<tr>
<td>选择要填报的表格：</td>
<td><select name="tabid" id="tabid" style="width:230px;">
</select></td>
</tr>
<tr><td colspan='2'><input name="sub" type="submit" value="下一步" style="background-color: #458CD3;border: none;color: white;padding: 10px 30px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;border-radius: 12px;"></td></tr>
</table>
</form>
</div>
<script type="text/javascript">
function tosel(){
	var index=document.getElementById("selgroup").selectedIndex;
	var selgroup=document.getElementById("selgroup").options[index].value;
	if(selgroup==""){
		
	}else{
		$.ajax({
			url:"${pageContext.request.contextPath}/user/getTabByGroup",
			type:"POST",
			data:{"selgroup":selgroup},
			success:function(result){
				var obj = eval("("+result+")");
				var arr=obj.data;
				var tabs=document.getElementById("tabid");
				tabs.options.length=0;
				for(var i=0;i<arr.length;i++){
					var optionObj=document.createElement("option");
				    optionObj.innerHTML=arr[i].tabname;
				    optionObj.value=arr[i].id;
					tabs.options.add(optionObj);
				}
			}
		});
	}
}
</script>
</body>
</html>