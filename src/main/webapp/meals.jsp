<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h2>Meals</h2>
<button onclick="window.location.href = 'meals?action=create'">Add Meal</button>
<br/>
<br/>
<hr/>
<table border="1">
    <tbody>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr align="center" style="color: ${meal.excess ? "red" : "green"}">
            <td><c:out value="${meal.dateTime.format(DateTimeFormatter.ofPattern('dd.MM.yyyy'))}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=edit&mealId=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&mealId=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="index.html">Home</a></h3>
<hr>
</body>
</html>