<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-DeleteMerce"/>
        <jsp:param name="style" value="crm, reset,libraryCustomer"/>
        <jsp:param name="script" value="crm"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<%boolean operazione1 = (boolean) request.getAttribute("deletem"); %>
<%boolean operazione2 = (boolean) request.getAttribute("deletef"); %>
<%if(operazione1 && operazione2){ %>
<h4>L'eliminazione è stata effettuata</h4>
<%}else{%>
<h4>L'eliminazione non è stata effettuata</h4>
<%}%>
<h4>
<a href="${pageContext.request.contextPath}/crm/home">Ritorna alla home</a>
</h4>
<h4>
<a href="${pageContext.request.contextPath}/merce/deleteMerce">Ritorna alla delete</a>
</h4>
</body>
</html>
