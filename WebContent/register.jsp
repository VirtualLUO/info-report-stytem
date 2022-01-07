<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signs.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css" type="text/css"/>
  	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath}/static/js/particles.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  <body>
  	  <div id="main-container">
		<div class="signinpanel">
			<form class="login" action="${pageContext.request.contextPath}/user/userreg" method="post">
		      <div class="login-top">
		        <h1>欢迎使用信息填报系统</h1>
		        <p>用户注册</p>
		      </div>
		      <div class="error-msg">
		      	<c:if test="${msg!=null}">${msg}</c:if>
		      </div>
		      <label class="login-center clearfix">
		        <div class="login-center-img">
		        <img src="${pageContext.request.contextPath}/static/img/name.png" /></div>
		        <div class="login-center-input">
		          <input type="text" name="username"  placeholder="请输入注册的账号" onfocus="this.placeholder=''"
		            onblur="this.placeholder='请输入注册的账号" required/>
		          <div class="login-center-input-text">注册账号</div>
		        </div>
		      </label>
		      <label class="login-center clearfix">
		        <div class="login-center-img">
		        	<img src="${pageContext.request.contextPath}/static/img/password.png" /></div>
		       		<div class="login-center-input">
		        		  <input type="password" name="password" value="" placeholder="请输入您设置的密码" onfocus="this.placeholder=''"
		        		    onblur="this.placeholder='请输入您设置的密码'" required />
		          	<div class="login-center-input-text">设置密码</div>
		        </div>
		      </label>
		      <div class="login-center clearfix">
		        <a href="login.jsp">已有账号，去登陆</a>
		      </div>
		      <input type="submit" class="login-button" value="注册">
		      </input>
		    </form>
		</div>
	</div>
  </body>
  <script src="${pageContext.request.contextPath}/static/js/app.js"></script>
  </body>
</html>