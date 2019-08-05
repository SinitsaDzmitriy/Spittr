<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title><spring:message code="app.name"/></title>
</head>
<body>

<!-- spring:message -->
<!-- Renders text with the specific key from a message source. -->

<button type="button" onclick="location.href='initial?lang=ru'">рус</button>
<button type="button" onclick="location.href='initial?lang=en'">eng</button>

<br>

<h1><spring:message code="initial.welcome"/></h1>

<security:authorize access="isAnonymous()">

    <button type="button" onclick="location.href='login'">
        <spring:message code="button.authorise"/>
    </button>

    <button type="button" onclick="location.href='register'">
        <spring:message code="button.register"/>
    </button>

    <spring:url value="/" var="homepage" />
    <a href=${homepage} >homepage</a>

</security:authorize>

<security:authorize access="hasRole('USER')">
    <button type="button" onclick="location.href='card/list'"><spring:message code="button.cards"/></button>

    <button type="button">
        admin panel
    </button>
</security:authorize>

</body>
</html>
