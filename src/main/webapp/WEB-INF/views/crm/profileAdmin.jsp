<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <jsp:include page="/WEB-INF/views/partials/head.jsp">
            <jsp:param name="title" value="Alfa-Utente"/>
            <jsp:param name="style" value="crm"/>
            <jsp:param name="script" value="crm,update"/>
        </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<%@include file="/WEB-INF/views/partials/TableProfile.jsp"%>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
