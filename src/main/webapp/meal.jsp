<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<html>
<head>
    <title>Meal</title>
</head>
<body>

<form method="POST">
    <input type="hidden" name="mealId" value="${meal.id}"/> <br/>

    DateTime :
    <input type="datetime-local" name="dateTime" required
           value="<c:out value="${meal.dateTime}"/>"/> <br/>
    <hr>
    Description :
    <input type="text" name="description" required
           value="<c:out value="${meal.description}"/>"/> <br/>
    <hr>
    Calories :
    <input type="number" name="calories" required
           value="<c:out value="${meal.calories}"/>"/> <br/>
    <hr>
    <input type="submit" value="Submit"/>
    <input type="button" onclick="window.location.href = 'meals'" value="Cancel">
</form>

</body>
</html>
