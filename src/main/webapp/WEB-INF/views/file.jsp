<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contectRoot" value="${pageContext.request.contextPath}" ></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="${contextRoot}/generate/file" method="post" modelAttribute="file">
<form:input path="file" type="file" placeholder="Enter Content" />
<form:input path="name" type="text" placeholder="Enter Filename" />
<button type="Submit">Done</button>
</form:form>
</body>
</html>