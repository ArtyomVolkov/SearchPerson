<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="volkov.search_person.service.SearchPersonService"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	SearchPersonService searchPersonService = new SearchPersonService();
	String arrayOfUsers = JSONValue.toJSONString(searchPersonService.getFullNameUserDS());
%>

<!DOCTYPE html>
<html>
<head>
<title>Search Person</title>

<meta content="initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport" />
<meta name="viewport" content="width=device-width" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />

<link rel="<c:url value="/libs/css/styles.css" />" />

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

<script
	src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>

<script src="<c:url value="/libs/js/jqm.autoComplete-1.5.2-min.js"/>"></script>

</head>
<body>
	<div data-role="page" id="mainPage">
		<div data-role="header">
			<h1><fmt:message key="search.page.info" /></h1>
			<select id="examples" data-mini="true" data-native-menu="false">
				<option value="search"><fmt:message key="search.action.searchByFirstName" /></option>
				<option value="search"><fmt:message key="search.action.searchByLastName" /></option>
			</select>
		</div>
		<div data-role="content">
			<h2><fmt:message key="search.title.message"/></h2>
			<c:set var="placeholder" value="Search by name" />
			<input type="text" id="searchField" placeholder="${placeholder}">
			<ul id="suggestions" data-role="listview" data-inset="true"></ul>
		</div>	
	</div>
	<script>
		$("#mainPage").bind("pageshow", function(e) {
			var data =<%=arrayOfUsers%>;
			$("#searchField").autocomplete({
				target : $('#suggestions'),
				source : data,
				link : 'person?username=',
				minLength : 1
			});
		});
	</script>
</body>
</html>