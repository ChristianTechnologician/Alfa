<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Alfa-Home"/>
        <jsp:param name="style" value="crm,dashboard"/>
        <jsp:param name="script" value="crm,dashboard"/>
    </jsp:include>
</head>
<body>


    <img src="/Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">

<div class="menu grid-x align-center dropdown">
    <a href="">Gestione Merce</a>
    <div class="dropdown-list">
        <a href="">Visualizza Merce</a>
        <a href="">Inserisci Merce</a>
        <a href="">Modifica Merce</a>
        <a href="">Cancella Merce</a>
    </div>
    <a href="">Gestione Ordine </a>
    <div class="dropdown-list">
        <a href="">Visualizza Ordini</a>
        <a href="">Inserisci Ordine</a>
        <a href="">Cancella Merce</a>
    </div>
    <a href="">Gestione Utente</a>
    <div class="dropdown-list">
        <a href="">Visualizza Utenti</a>
        <a href="">Cancella Merce</a>
    </div>
    <a href="">Profilo</a>
    <a href="">Esci</a>
</div>


<section>
    <div class="grid-x justify-center">
        <div class="grid-y cell">
            <h4></h4>
            <h5></h5>
        </div>
        <div class="grid-y cell">
            <h4></h4>
            <h5></h5>
        </div>
        <div class="grid-y cell">
            <h4></h4>
            <h5></h5>
        </div>
        <div class="grid-y cell">
            <h4></h4>
            <h5></h5>
        </div>
    </div>
</section>

<footer class="info">
Copyright 2021.
</footer>

</body>
</html>
