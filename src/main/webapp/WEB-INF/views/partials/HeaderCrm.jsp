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
    <div class="dropdown"> <form action="${pageContext.request.contextPath}/merce/merce" method="get">
        <li><button class="dropbtn">Gestione Merce</button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/merce/insertMerce">Inserisci Merce</a>
                <a href="${pageContext.request.contextPath}/merce/updateMerce">Modifica Merce</a>
                <a href="${pageContext.request.contextPath}/merce/deleteMerce">Cancella Merce</a>
            </div></li>
    </form>
    </div>


    <div class="dropdown"> <form action="${pageContext.request.contextPath}/ordine/mostraOrdini" method="get">
        <li><button class="dropbtn">Gestione Ordini</button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/ordine/ordine">Visualizza Ordine</a>
            </div></li>
    </form>
    </div>


    <div class="dropdown"> <form action="${pageContext.request.contextPath}/utente/visualizza" method="get">
        <li><button class="dropbtn">Gestione Utente</button>
            <div class="dropdown-content">
                <a  href="${pageContext.request.contextPath}/utente/utente">Visualizza Utente</a>
            </div></li>
    </form>
    </div>

    <div class="dropdown"> <form action="${pageContext.request.contextPath}/utente/profileAdmin" method="get">
        <li><button class="dropbtn">Profilo</button></li>
    </form>
    </div>

    <div  class="dropdown"> <form action="${pageContext.request.contextPath}/utente/logout" method="get">
        <li><button class="dropbtn">Logout</button></li>
    </form>
    </div>
</ul>
</body>
</html>
