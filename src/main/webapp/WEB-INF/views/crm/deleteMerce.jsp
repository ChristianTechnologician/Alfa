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
<form action="${pageContext.request.contextPath}/merce/deleteMerce" method="post">
    <label id="#id">
    <input type="text" name="IdMerce" placeholder="ID merce">
    </label>
    <button>Elimina</button>
</form>
</section>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
