<%@ page import="Model.Merce.Merce" %>
<%@ page import="java.util.List" %>
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

<!--Inserisci il codice del prodotto che vuoi modificare

<form action="" method="post">
    <label id="#updatecodice">
        <input type="text" name="IdMerce" placeholder="ID">
    </label>
</form>-->

<%String s =(String)request.getAttribute("IdMerce");%>
<section class="field">
    <form action="${pageContext.request.contextPath}/merce/update" method="post">
        <%if(s==null){%>
        <label id="#codice">
            Inserisci il codice del prodotto che vuoi modificare
            <input type="text" name="Codice" placeholder="Codice">
        </label><%}else{%>
        <label id="#codice">
            <input type="hidden" name="Codice" value="<%=s%>">
        </label>
        <%}%>
        <label id="#nome">
            <input type="text" name="Nome" placeholder="Nome">
        </label>
        <label id="#descrizione">
            <input type="text" name="Descrizione" placeholder="Descrizione">
        </label>
        <label id="#genere">
            <input type="text" name="Genere" placeholder="Genere">
        </label>
        <label id="#prezzo">
            <input type="text" name="Prezzo" placeholder="Prezzo">
        </label>
        <label id="#tipoCategoria">
            <input type="text" name="TipoCategoria" placeholder="Categoria(Completo,Cappotto)">
        </label>
        <label id="#sconto">
            <input type="text" name="Sconto" placeholder="%sconto">
        </label>
        <button class="premi">Applica modifica</button>
    </form>
</section>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
