<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Home"/>
        <jsp:param name="style" value="crm, reset,libraryCustomer,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>
<%int merce = (int) request.getAttribute("merce");%>
<%int ordini = (int) request.getAttribute("ordini");%>
<%int utenti = (int) request.getAttribute("utenti");%>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<section>
    <div class="grid-x justify-center ">
        <div class="grid-y cell">
            <h4>Merce in magazzino</h4>
            <h5><%=merce%></h5>
        </div>
        <div class="grid-y cell">
            <h4>Ordini effettuati</h4>
            <h5><%=ordini%></h5>
        </div>
        <div class="grid-y cell">
            <h4>Utenti registrati</h4>
            <h5><%=utenti%></h5>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
