<%@ page import="java.util.List" %>
<%@ page import="Model.Ordine.Ordine" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Ordini"/>
        <jsp:param name="style" value="crm,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<div>
    <%@include file="/WEB-INF/views/partials/TableOrdini.jsp"%>
</div>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
