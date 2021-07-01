<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Ordine"/>
        <jsp:param name="style" value="crm,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>
<%@include file="/WEB-INF/views/partials/HeaderCrm.jsp"%>
<div>
    <h3>Inserisci l'id dell'utente o la sua mail per visualizzare gli ordini</h3>
   <form action="${pageContext.request.contextPath}/ordine/scegliOrdine" method="get">
       <label>
           <input type="text" name="nFattura" placeholder="NUMERO FATTURA">
       </label>
       <button>Invia</button>
   </form>

    <form action="${pageContext.request.contextPath}/ordine/scegliOrdine" method="get">
        <label>
            <input type="email" name="eCliente" placeholder="EMAIL CLIENTE">
        </label>
        <button>Invia</button>
    </form>

    <form action="${pageContext.request.contextPath}/ordine/scegliOrdine" method="get">
        <label>
            <input type="text" name="idCliente" placeholder="ID CLIENTE">
        </label>
        <button>Invia</button>
    </form>
</div>
<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
