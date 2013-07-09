<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
<title>Person</title>
<meta content="initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport" />
<meta name="viewport" content="width=device-width" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
<link rel="<c:url value="/libs/css/styles.css" />" />

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>
<script src="<c:url value="/libs/js/jqm.autoComplete-1.5.2-min.js" />"></script>

</head>

<body>
	<div data-role="page" id="mainPage">
		<div data-role="header">
			<h1><fmt:message key="person.page.info" /></h1>
			<select id="examples" data-mini="true" data-native-menu="false">
				<option value="search"><fmt:message key="search.action.searchByFirstName" /></option>
				<option value="search"><fmt:message key="search.action.searchByLastName" /></option>
			</select>
		</div>
		<div data-role="content">
			<h3><fmt:message key="person.firstName" /> ${userLdap.firstName}</h3>
			<h3><fmt:message key="person.laststName" /> ${userLdap.lastName}</h3>
			<h3><fmt:message key="person.email" /><a href="${userLdap.email}">${userLdap.email}</a></h3>
		</div>
	</div>
</body>
</html>

