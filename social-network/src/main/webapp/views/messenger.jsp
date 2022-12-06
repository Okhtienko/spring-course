<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.14/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/resources/css/messenger.css"/>">
    <title>Messenger</title>
</head>
<body>
    <div class="container p-30">
        <div class="col-md-6 col-lg-7 col-xl-7">
            <div class="row">
                <jsp:include page="header.jsp"/>
            </div>
            <ul class="list-unstyled text-white">
                <li class="d-flex justify-content-between mb-4">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-6.webp" alt="avatar"
                         class="rounded-circle d-flex align-self-start me-3 shadow-1-strong" width="60">
                    <div class="card mask-custom w-100">
                        <div class="card-header d-flex justify-content-between p-3"
                             style="border-bottom: 1px solid rgba(255,255,255,.3);">
                            <p class="fw-bold mb-0"><c:out value="${friendName}"/> </p>
                        </div>
                        <c:forEach items="${messages}" var="message">
                            <c:if test="${message.firstFriendId == friendId}">
                                <div class="card-body">
                                    <p class="mb-0">
                                        <c:out value="${message.text}" />
                                    </p>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </li>
                <li class="d-flex justify-content-between mb-4">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-6.webp" alt="avatar"
                         class="rounded-circle d-flex align-self-start me-3 shadow-1-strong" width="60">
                    <div class="card mask-custom w-100">
                        <div class="card-header d-flex justify-content-between p-3"
                             style="border-bottom: 1px solid rgba(255,255,255,.3);">
                            <p class="fw-bold mb-0"><c:out value="${signedUserName}"/></p>
                        </div>
                        <c:forEach items="${messages}" var="message">
                            <c:if test="${message.secondFriendId == friendId}">
                                <div class="card-body">
                                    <p class="mb-0">
                                        <c:out value="${message.text}" />
                                    </p>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </li>
                <form action="messages" method="post" >
                    <li class="mb-3">
                        <div class="form-outline form-white">
                            <input type="hidden" name="friendId" value="${friendId}">
                            <input type="hidden" name="friendName" value="${friendName}">
                            <label class="form-label" for="textArea"></label>
                            <textarea name="text" class="form-control" id="textArea" rows="4" placeholder="Write a message..."></textarea>
                        </div>
                    </li>
                    <button class="mode mode_on">Send Message</button>
                </form>
                <form action="clear" method="post">
                    <input type="hidden" name="friendId" value="${friendId}">
                    <button class="mode mode_off">Clear message history</button>
                </form>
            </ul>
        </div>
    </div>
</body>
</html>
