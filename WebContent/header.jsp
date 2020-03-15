<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="custom.css">
<title>LinkedOut.com</title>
<script src="jquery-3.3.1.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #113377;">
  <a class="navbar-brand" href="UserPage.jsp">LinkedOut</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
 
<c:if test="${not empty sessionScope.admin}">
	<div class="collapse navbar-collapse" id="navbar">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a style="color:#FFFFFF">Admin's Page <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</c:if>
<c:if test="${empty sessionScope.admin}">
  	<div class="collapse navbar-collapse" id="navbar">
    	<ul class="navbar-nav mr-auto">
      	  <li class="nav-item active">
        	<a class="nav-link" href="PostServlet">Home <span class="sr-only">(current)</span></a>
      	  </li>
      	  <li class="nav-item active">
        	<a class="nav-link" href="NetworkServlet?action=net">Network</a>
      	  </li>
	      <li class="nav-item active">
	      	<a class="nav-link" href="JobAppServlet?action=net">Ads</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="ChatServlet?chatid=0">Chats</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="NotificationsServlet">Notifications</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="ViewUsersPIServlet?viewuserid=${user.idusers}">Personal Info</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="SettingsServlet?action=user">Settings</a>
	      </li>
	    </ul>
	  </div>
</c:if>
	<ul class="navbar-nav ml-auto">
    	<li class="nav-item active">
      		<a class="nav-link" href="logout.jsp">Log Out</a>
    	</li>
    </ul>
</nav>