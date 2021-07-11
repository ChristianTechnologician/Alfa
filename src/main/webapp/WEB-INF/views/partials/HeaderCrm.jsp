<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Header</title>
</head>
<body>
<header id="logo" class="grid-x justify-center">
    <div style="text-align: center">
            <img src="../Images/LOGO.jpg" alt="Impossibile caricare l'immagine" style="width: 215px; height: 100px; position: center">
    </div>
</header>
<ul>
    <div class="dropdown">
        <li><button class="dropbtn"><a href="${pageContext.request.contextPath}/merce/merce">Gestione Merce</a></button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/merce/insertMerce">Inserisci Merce</a>
                <a href="${pageContext.request.contextPath}/merce/updateMerce">Modifica Merce</a>
                <a href="${pageContext.request.contextPath}/merce/deleteMerce">Cancella Merce</a>
            </div>
        </li>
    </div>

    <div class="dropdown">
        <li>
            <button class="dropbtn"><a href="${pageContext.request.contextPath}/ordine/mostraOrdini">Gestione Ordini</a></button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/ordine/ordine">Visualizza Ordine</a>
            </div>
        </li>
    </div>

    <div class="dropdown">
        <li>
            <button class="dropbtn"> <a href="${pageContext.request.contextPath}/utente/visualizza">Gestione Utente</a></button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/utente/utente">Visualizza Utente</a>
            </div>
        </li>
    </div>

    <div class="dropdown">
        <li>
            <button class="dropbtn"><a href="${pageContext.request.contextPath}/utente/profileAdmin">Profilo</a></button>
            </li>
    </div>

    <div class=" dropdown">
        <li>
            <button class="dropbtn"><a href="${pageContext.request.contextPath}/utente/logout">Logout</a></button>
        </li>
    </div>
</ul>
</body>
</html>
