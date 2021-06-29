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
        <li><a href="${pageContext.request.contextPath}/merce/merce">Gestione Merce</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/merce/insertMerce">Inserisci Merce</a>
                <a href="${pageContext.request.contextPath}/merce/updateMerce">Modifica Merce</a>
                <a href="${pageContext.request.contextPath}/merce/deleteMerce">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="${pageContext.request.contextPath}/ordine/mostraOrdini">Gestione Ordini</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/ordine/ordine">Visualizza Ordine</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="${pageContext.request.contextPath}/utente/visualizza">Gestione Utente</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/utente/utente">Visualizza Utente</a>
            </div>
        </li>
    </div>

    <div class="dropdown grid-x align-center">
        <li>
            <a href="${pageContext.request.contextPath}/utente/profileAdmin">Profilo</a>
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
