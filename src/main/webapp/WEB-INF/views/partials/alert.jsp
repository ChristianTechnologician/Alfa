<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class = "notification ${alert.type}">
    <ol class = "cell">
        <c:forEach var = "msg" items="${alert.message}">
            <li>${msg}</li>
        </c:forEach>
    </ol>
    <span id="notification-close" class="close">
        <%@include file="../../../icon/LOGO.jpg"%>
    </span>
</div>

<% //<c:if test ="${not empty alert}"><\%@include file="../partials/alert.jsp"%\></c:if> %>

