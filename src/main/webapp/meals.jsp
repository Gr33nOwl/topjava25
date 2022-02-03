<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h2>Meals</h2>
<table border="1">
    <tbody>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:choose>
            <c:when test="${meal.excess}"><c:set var="trColor" value="red"/></c:when>
            <c:otherwise><c:set var="trColor" value="green"/></c:otherwise>
        </c:choose>
        <tr align="center" style="color: ${trColor}">
            <td><c:out value="${meal.dateTime.format(DateTimeFormatter.ofPattern('dd.MM.yyyy'))}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
<hr>
</body>
</html>