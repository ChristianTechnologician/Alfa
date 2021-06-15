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

<div class="grid-x justify-center">
    <img src="/Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
</div>

<ul>
    <div class="dropdown grid-x align-center">
        <li><a href="">Gestione Merce</a>
            <div class="dropdown-content">
                <a href="">Visualizza Merce</a>
                <a href="">Inserisci Merce</a>
                <a href="">Modifica Merce</a>
                <a href="">Cancella Merce</a>
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

    <div class="grid-x align-center">
        <a href="">Profilo</a>
    </div>

    <div class="grid-x align-center">
        <a href="">Esci</a>
    </div>

</ul>

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
