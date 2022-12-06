<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,800&display=swap">
    <link rel="stylesheet" href="<c:url value="/resources/css/errors.css"/>">
    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Errors</h1>
        <ul class="list-group">
            <c:forEach items="${exceptions}" var="exception">
                <li class="list-group-item">
                    <c:out value="${exception}"/>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
