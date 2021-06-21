<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-DeleteMerce"/>
        <jsp:param name="style" value="crm"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
 <%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>

<section class="field">
<form action="" method="post">
    <label id="#id">
    <input type="number" name="IdMerce" placeholder="ID merce">
    </label>
</form>
</section>

<%//boolean operazione = (boolean) session.getAttribute("delete"); %>
<% //if(operazione){ %>
L'eliminazione è stata effettuata
<%//else{%>
L'eliminazione non è stata effettuata
<%//}%>

<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
