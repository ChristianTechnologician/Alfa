<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Utenti"/>
        <jsp:param name="style" value="crm, reset,libraryCustomer"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<div>
    <%@include file="/WEB-INF/views/partials/TableAmministratori.jsp"%>
</div>
<div>
    <%@include file="/WEB-INF/views/partials/TableUtenti.jsp"%>
</div>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
