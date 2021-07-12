<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Utente"/>
        <jsp:param name="style" value="crm, reset,libraryCustomer"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<div>
    <h3>Inserisci l'email dell'utente che vuoi visualizzare</h3>
    <form action="${pageContext.request.contextPath}/utente/visualizzaUtente" method="get">
        <label>
            <input type="text" name="email" placeholder="Email">
        </label>
        <button class="premi">Invia</button>
    </form>
</div>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
