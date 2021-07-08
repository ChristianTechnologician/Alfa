<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Preferiti"/>
        <jsp:param name="style" value="reset,libraryCustomer"/>
        <jsp:param name="script" value=""/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCustomer.jsp"%>
<div>
    <%@include file="/WEB-INF/views/partials/TablePreferiti.jsp"%>
</div>
<%@include file="/WEB-INF/views/partials/FooterCustomer.jsp"%>
</body>
</html>
