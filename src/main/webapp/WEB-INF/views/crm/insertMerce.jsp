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
    <form action="${pageContext.request.contextPath}/merce/create" method="post" enctype="multipart/form-data">
        <label for="Codice">
            <input type="text" name="IdMerce" id="Codice" placeholder="ID">
        </label>
        <label for="Nome">
            <input type="text" name="NomeMerce" id="Nome" placeholder="Nome">
        </label>
        <label for="Descrizione">
            <input type="text" name="DescrizioneMerce" id="Descrizione" placeholder="Descrizione">
        </label>
        <label for="Genere">
            <input type="text" name="GenereMerce" id="Genere" placeholder="Genere">
        </label>
        <label for="Prezzo">
            <input type="number" name="PrezzoMerce" id="Prezzo" placeholder="Prezzo">
        </label>
        <label for="TipoCategoria">
            <input type="text" name="TipoCategoria" id="TipoCategoria" placeholder="Categoria(Completo,Cappotto)">
        </label>
        <label for="Sconto">
            <input type="number" name="Sconto" id="Sconto" placeholder="%sconto">
        </label>
        <label for="Colore">
            <input type="text" name="Colore" id="Colore" placeholder="Colore">
        </label>
        <label for="Taglia">
            <input type="text" name="Taglia" id="Taglia" placeholder="Taglia">
        </label>
        <label for="Quantita">
            <input type="number" name="Quantita" id="Quantita" placeholder="Quantita">
        </label>
        <label for="upImg">
            <input type="file" name="upImg" id="upImg">
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
