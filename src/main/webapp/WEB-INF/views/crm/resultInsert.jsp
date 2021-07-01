<%@ page import="java.util.List" %>
<%@ page import="Model.Merce.Merce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Home"/>
        <jsp:param name="style" value="crm,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<div>
    Merce inserita correttamente
    <%@include file="/WEB-INF/views/partials/insertFornitura.jsp"%>
</div>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
