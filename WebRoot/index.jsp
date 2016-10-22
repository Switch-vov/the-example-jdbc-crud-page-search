<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">
	
	</head>
	<body>
		<jsp:forward page="/FindAllProductServlet"/>
	</body>
</html>