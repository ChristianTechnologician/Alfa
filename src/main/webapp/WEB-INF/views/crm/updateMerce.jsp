<%@ page import="Model.Merce.Merce" %>
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

Inserisci il codice del prodotto che vuoi modificare

<form action="" method="post">
    <label id="#updatecodice">
        <input type="text" name="IdMerce" placeholder="ID">
    </label>
</form>

<%Merce m = (Merce) session.getAttribute("merce"); %>

<section class="field">
    <form action="" method="post">
        <label id="#nome">
            <input type="text" name="NomeMerce" placeholder="Nome">
        </label>
        <label id="#descrizione">
            <input type="text" name="DescrizioneMerce" placeholder="Descrizione">
        </label>
        <label id="#genere">
            <input type="text" name="GenereMerce" placeholder="Genere">
        </label>
        <label id="#prezzo">
            <input type="number" name="PrezzoMerce" placeholder="Prezzo">
        </label>
        <label id="#tipoCategoria">
            <input type="text" name="TipoCategoria" placeholder="Categoria(Completo,Cappotto)">
        </label>
        <label id="#sconto">
            <input type="number" name="Sconto" placeholder="%sconto">
        </label>
        <label id="#colore">
            <input type="text" name="Colore" placeholder="Colore">
        </label>
        <label id="#taglia">
            <input type="text" name="Taglia" placeholder="Taglia">
        </label>
        <label id="#quantita">
            <input type="number" name="Quantita" placeholder="Quantità">
        </label>
    </form>
</section>

<%//boolean operazione = (boolean) session.getAttribute("inserimento"); %>
<% //if(operazione){ %>
Inserimento riuscito
<% //}else{%>
Inserimento fallito
<%//}%>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
