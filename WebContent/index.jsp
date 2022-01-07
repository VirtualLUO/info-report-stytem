<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.pojo.User"%>
<% User loginuser = (User)session.getAttribute("user");%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>信息填报系统</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome-4.7.0/css/font-awesome.min.css">
	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
</head>
<body>
<div class="header" style="padding: 0 15px">
	<h1>信息填报系统</h1>
	<span>登录用户：<%=loginuser.getUsername()%></span>
</div>
<div class="container" >
	<div class="container-nav " >
		<div class="nav-top" ><div class="fold-btn" ><i class="fa fa-bars fa-2x" ></i></div></div>
		<ul>
		<%if(loginuser.getRole().equals("user")){ %>
			<li class="nav-item" >
	     		<a href="javascript:;"> 
					<i class="fa fa-tv fa-fw fa-lg"></i>
					<span>信息管理</span>
					<i class="nav-more fa fa-chevron-right"></i>
				</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/user/userTablist" target="mainframe"><span>信息管理</span></a></li>
					<li><a href="${pageContext.request.contextPath}/user/inputinfo" target="mainframe"><span>填报信息</span></a></li>
				</ul>
			</li>
		<%}
		if(loginuser.getRole().equals("admin")){
		%>	
			<li class="nav-item" >
				<a href="javascript:;">
					<i class="fa fa-pencil fa-fw fa-lg"></i>
					<span>用户管理</span>
					<i class="nav-more fa fa-chevron-right"></i>
				</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/user/grouplist" target="mainframe"><span>用户组管理</span></a></li>
					<li><a href="${pageContext.request.contextPath}/addGroup.jsp" target="mainframe"><span>添加用户组</span></a></li>
					<li><a href="${pageContext.request.contextPath}/user/userlist" target="mainframe"><span>用户管理</span></a></li>
				</ul>
			</li>
			<li class="nav-item">
				<a href="javascript:;"  >
					<i class="fa fa-pencil fa-fw fa-lg"></i>
					<span>表格管理</span>
					<i class="nav-more fa fa-chevron-right"></i>
				</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/tab/tableslist" target="mainframe"><span>表格管理</span></a></li>
					<li><a href="${pageContext.request.contextPath}/createTable.jsp" target="mainframe"><span>制定表格</span></a></li>
				</ul>
			</li>
			<% } %>
			<li class="nav-item"><a href="${pageContext.request.contextPath}/user/outLogin"><i class="fa fa-sign-out fa-fw fa-lg"></i> <span>退出登录</span></a></li>
		</ul>
	</div>
	<div class="container-main">
		<iframe name="mainframe" src="" frameborder="0" style="width:100%;min-height:100%;background-color: #FFF;"></iframe>
	</div>
</div>
<script type="text/javascript">
    $(function(){
        // nav收缩展开
        $('.nav-item>a').on('click',function(){
            if (!$('.nav').hasClass('nav-mini')) {
                if ($(this).next().css('display') == "none") {
                    //展开未展开
                    $('.nav-item').children('ul').slideUp(300);
                    $(this).next('ul').slideDown(300);
                    $(this).parent('li').addClass('nav-show').siblings('li').removeClass('nav-show');
                }else{
                    //收缩已展开
                    $(this).next('ul').slideUp(300);
                    $('.nav-item.nav-show').removeClass('nav-show');
                }
            }
        });
        //nav-mini切换
        $('.fold-btn').on('click',function(){
            $('.nav-item.nav-show').toggleClass('nav-show');
            $('.container').toggleClass("fold");
        });
    });
</script>
</body>
</html>