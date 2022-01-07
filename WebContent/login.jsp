<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signs.css" type="text/css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css" type="text/css"/>
  	<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath}/static/js/particles.min.js"></script>
  <body>
  <div id="main-container">
	<div class="signinpanel">
		<form class="login" action="${pageContext.request.contextPath}/user/userlogin" method="post">
	      <div class="login-top">
	        <h1>欢迎使用信息填报系统</h1>
	        <p>请登录</p>
	      </div>
	      <div class="error-msg">
	      	<c:if test="${msg!=null}">${msg}</c:if>
	      </div>
	      <label class="login-center clearfix">
	        <div class="login-center-img">
	        <img src="${pageContext.request.contextPath}/static/img/name.png" /></div>
	        <div class="login-center-input">
	          <input type="text" name="username"  placeholder="请输入您的用户名" onfocus="this.placeholder=''"
	            onblur="this.placeholder='请输入您的用户名'" required/>
	          <div class="login-center-input-text">用户名</div>
	        </div>
	      </label>
	      <label class="login-center clearfix">
	        <div class="login-center-img">
	        	<img src="${pageContext.request.contextPath}/static/img/password.png" /></div>
	       		<div class="login-center-input">
	        		  <input type="password" name="password" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''"
	        		    onblur="this.placeholder='请输入您的密码'" required/>
	          	<div class="login-center-input-text">密码</div>
	        </div>
	      </label>
	      <div class="login-center clearfix">
	        <a href="${pageContext.request.contextPath}/register.jsp">用户注册</a>
	      </div>
	      <input type="submit" class="login-button" value="登录">
	      </input>
	    </form>
	</div>
	</div>
  </body>
  <script src="${pageContext.request.contextPath}/static/js/app.js"></script>
</html>