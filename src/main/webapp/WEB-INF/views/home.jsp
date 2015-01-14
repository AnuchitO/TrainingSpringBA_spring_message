<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<hr/>
<div id="language">
    <span><a href="?lang=en_US">English</a> : </span>
    <span><a href="?lang=th_TH">Thai</a></span>
</div>
<br/><br/>
<h1 class="alt"><spring:message code="label.name" text="Default Name Label"/><strong><spring:message code="label.text.name" text="Default text"/></strong></h1>
</body>
