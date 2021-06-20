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
<header class="grid-x justify-center">
    <img src="Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
</header>

<ul>
    <div class="dropdown grid-x align-center">
        <li><a href="/crm/merce">Gestione Merce</a>
            <div class="dropdown-content">
                <a href="./crm/showMerce">Visualizza Merce</a>
                <a href="./crm/insertMerce">Inserisci Merce</a>
                <a href="./crm/updateMerce">Modifica Merce</a>
                <a href="./crm/deleteMerce">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Gestione Ordine </a>
            <div class="dropdown-content">
                <a href="">Visualizza Ordini</a>
                <a href="">Inserisci Ordine</a>
                <a href="">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Gestione Utente</a>
            <div class="dropdown-content">
                <a href="">Visualizza Utenti</a>
                <a href="">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="">Profilo</a>
        </li>
    </div>

    <div class=" dropdown grid-x align-center">
        <li>
            <a href="">Esci</a>
        </li>
    </div>

</ul>

<section class="field">
<form action="" method="post">
    <label id="#id">
    <input type="number" name="IdMerce" placeholder="ID merce">
    </label>
</form>
</section>

<%//boolean operazione = (boolean) session.getAttribute("delete"); %>
<% //if(operazione){ %>
L'eliminazione è stata effettuata
<%// }else{%>
L'eliminazione non è stata effettuata
<%//}%>

<%@include file="/WEB-INF/views/partials/FooterCrm.jsp"%>
</body>
</html>
