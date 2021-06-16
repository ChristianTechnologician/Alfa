<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Header</title>
</head>
<body>
<header class="grid-x justify-center">
    <img src="${pageContext.request.contextPath}/Images/LOGO.jpg" alt="Impossibile caricare l'immagine" width="215" height="100">
</header>
<ul>
    <div class="dropdown grid-x align-center">
        <li><a href="${pageContext.request.contextPath}/crm/merce">Gestione Merce</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/crm/insertMerce">Inserisci Merce</a>
                <a href="${pageContext.request.contextPath}/crm/updateMerce">Modifica Merce</a>
                <a href="${pageContext.request.contextPath}/crm/deleteMerce">Cancella Merce</a>
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
</body>
</html>
