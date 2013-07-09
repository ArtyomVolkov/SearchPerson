<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="/libs/css/style.css" />
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty error}">
		<c:set var="message" value="Incorrect! Please try again." />	
	</c:if>
	<form id="slick-login"
		action="<c:url value='j_spring_security_check' />" method='POST'>
		${message}
		<label for="username"><fmt:message key="app.login.username" /></label>
		<input type="text" name="j_username" class="placeholderError" placeholder="username">
		<label for="password"><fmt:message key="app.login.password" /></label>
		<input type="password" name="j_password" class="placeholderError" placeholder="password">
		<input name="submit" type="submit" value="<fmt:message key="app.login" />">
	</form>
</body>
</html>